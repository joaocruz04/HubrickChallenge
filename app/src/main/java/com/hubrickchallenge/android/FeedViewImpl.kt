package com.hubrickchallenge.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hubrickchallenge.android.model.Author
import com.hubrickchallenge.android.model.Event


class FeedViewImpl : FeedView, AppCompatActivity() {

    var recyclerView = findViewById(R.id.recyclerView) as RecyclerView
    var presenter = FeedPresenterImpl()
    var adapter: FeedAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_view_impl)
        presenter.registerView(this)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FeedAdapter(presenter.events)
        recyclerView.adapter = adapter
    }

    override fun updateData() {
        adapter?.notifyDataSetChanged()
    }

// TODO: Colocar codigo abaixo numa classe Singleton ao nivel da aplicação






/*
        val database = FirebaseDatabase.getInstance().reference.child("feeds")
        var register = database.push()
        var event = Event()
        event.id = "004"
        event.type = "ADD"
        event.author = Author()
        event.author?.name = "joao"
        event.author?.displayName = "Joao Cruz"
        register.setValue(event)
*/




        /*
        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                println("Cancelled")
            }

            override fun onDataChange(p0: DataSnapshot?) {

                var events = ArrayList<Event>()
                p0?.children?.forEach {
                    var event = it.getValue(Event::class.java)
                    if (event!=null) events.add(event)
                }


                println("Data changed")
            }

        })
*/




}
