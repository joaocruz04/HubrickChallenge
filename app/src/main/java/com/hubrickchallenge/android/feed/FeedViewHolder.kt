package com.hubrickchallenge.android.feed

import android.content.Context
import android.graphics.Color
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

    var titleLbl: TextView? = itemView?.findViewById(R.id.titleLbl) as TextView
    var descriptionLbl: TextView? = itemView?.findViewById(R.id.descriptionLbl) as TextView
    var imageView: ImageView? = itemView?.findViewById(R.id.imageView) as ImageView
    var avatarImageView: ImageView? = itemView?.findViewById(R.id.avatarImageView) as ImageView
    var authorLbl: TextView? = itemView?.findViewById(R.id.authorNameLbl) as TextView
    var dateLbl: TextView? = itemView?.findViewById(R.id.dateLbl) as TextView
    var cardLayout: LinearLayout? = itemView?.findViewById(R.id.card_bg) as LinearLayout

    fun bindEvent(event: Event, context: Context) {
        authorLbl?.text = event.author?.displayName?:"?"
        when(event.type) {
            "ADD" -> {
                cardLayout?.setBackgroundColor(Color.WHITE)
                cardLayout?.alpha = 1.0F
            }
            "UPDATE" -> {
                cardLayout?.setBackgroundColor(Color.GREEN)
                cardLayout?.alpha = 1.0F
            }
            "DELETE" -> {
                cardLayout?.alpha = 0.3F
            }
        }

        Glide.with(context)
                .load(event.author?.avatarImage?.url?:"")
                .apply(RequestOptions.circleCropTransform())
                .into(avatarImageView)
    }






}