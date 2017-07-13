package com.hubrickchallenge.android.feed

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.model.Event


/**
 * Created by joaocruz04 on 10/07/2017.
 */
class FeedViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    val titleLbl: TextView? = itemView?.findViewById(R.id.titleLbl) as TextView
    val descriptionLbl: TextView? = itemView?.findViewById(R.id.descriptionLbl) as TextView
    val imageView: ImageView? = itemView?.findViewById(R.id.imageView) as ImageView
    val avatarImageView: ImageView? = itemView?.findViewById(R.id.avatarImageView) as ImageView
    val authorLbl: TextView? = itemView?.findViewById(R.id.authorNameLbl) as TextView
    val dateLbl: TextView? = itemView?.findViewById(R.id.dateLbl) as TextView
    val cardLayout: LinearLayout? = itemView?.findViewById(R.id.card_bg) as LinearLayout
    val likeCountLbl: TextView? = itemView?.findViewById(R.id.likeCountLbl) as TextView
    val shareCountLbl: TextView? = itemView?.findViewById(R.id.shareCountLbl) as TextView
    val commentCountLbl: TextView? = itemView?.findViewById(R.id.commentCountLbl) as TextView

    fun bindEvent(event: Event, context: Context) {
        authorLbl?.text = event.author?.displayName?:"?"

        when(event.type) {
            "ADD" -> {
                cardLayout?.alpha = 1.0F
            }
            "UPDATE" -> {
                cardLayout?.alpha = 1.0F
            }
            "DELETE" -> {
                cardLayout?.alpha = 0.3F
            }
        }

        titleLbl?.text = event.payload?.plainTitle?:""
        descriptionLbl?.text = event.payload?.plainContentPreview?:""
        likeCountLbl?.text = "${event.payload?.stats?.reactionStats?.counts?.LIKE}"
        shareCountLbl?.text = "${event.payload?.stats?.reactionStats?.counts?.SHARE}"
        commentCountLbl?.text = "${event.payload?.stats?.commentStats?.count}"

        Glide.with(context)
                .load(event.author?.avatarImage?.url?:"")
                .apply(RequestOptions.circleCropTransform())
                .into(avatarImageView)

        Glide.with(context)
                .load(event.payload?.headlineImage?.url?:"")
                .into(imageView)
    }






}