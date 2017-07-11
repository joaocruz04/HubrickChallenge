package com.hubrickchallenge.android.feed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hubrickchallenge.android.R


class FeedViewImpl : FeedView, AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var presenter = FeedPresenterImpl()
    var adapter: FeedAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_view_impl)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        presenter.registerView(this)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = FeedAdapter(presenter.events)
        recyclerView?.adapter = adapter
    }

    override fun updateData() {
        adapter?.notifyDataSetChanged()
    }

}
