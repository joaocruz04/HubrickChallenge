package com.hubrickchallenge.android.feed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hubrickchallenge.android.App
import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.managers.FeedConsumer
import com.hubrickchallenge.android.model.Event
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


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

    override fun updateData(newItemsCount: Int) {
        var position = (recyclerView?.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        var offset = ((recyclerView?.layoutManager as LinearLayoutManager).findViewByPosition(position))?.top?:0
        adapter?.update(position, newItemsCount, offset)

    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }




}
