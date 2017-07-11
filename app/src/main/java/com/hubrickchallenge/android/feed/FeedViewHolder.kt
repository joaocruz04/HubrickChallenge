package com.hubrickchallenge.android.feed

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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


    fun bindEvent(event: Event) {
        authorLbl?.text = event.author?.displayName?:"?"
    }




}