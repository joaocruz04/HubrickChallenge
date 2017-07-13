package com.hubrickchallenge.android.feed

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.model.Event
import java.lang.Math.abs

/**
 * Created by joaocruz04 on 10/07/2017.
 */
class FeedAdapter(eventList: List<Event>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var events = eventList
    var recyclerView: RecyclerView?=null
    var lm: LinearLayoutManager?=null

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

    override fun getItemId(position: Int): Long {
        return events?.get(position)?.hashCode()?.toLong()?:0L
    }

    fun update(position: Int, newItemsCount: Int, offset: Int) {
        notifyDataSetChanged()
        if ((newItemsCount>0) && !((position==0 && abs(offset)<10))) {
            lm?.scrollToPositionWithOffset(position + newItemsCount, offset)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        lm = recyclerView?.layoutManager as LinearLayoutManager
    }

    fun scrollToTop() {
        val position = lm?.findFirstVisibleItemPosition()?:0
        if (position>5)
            lm?.scrollToPositionWithOffset(5, 0)
        lm?.smoothScrollToPosition(recyclerView, null, 0)
    }
}
