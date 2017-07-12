package com.hubrickchallenge.android.feed

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.model.Event

/**
 * Created by joaocruz04 on 10/07/2017.
 */
class FeedAdapter(eventList: List<Event>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var events = eventList
    var recyclerView: RecyclerView?=null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as FeedViewHolder).bindEvent(events!![position], recyclerView!!.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var inflatedView = LayoutInflater.from(parent?.context).inflate(R.layout.feed_item, parent, false)
        return FeedViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return events?.size?:0
    }

    fun update(position: Int, newItemsCount: Int, offset: Int) {
        notifyDataSetChanged()
        if (newItemsCount>0)
            (recyclerView?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position+newItemsCount, offset)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }
}
