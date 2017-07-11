package com.hubrickchallenge.android.managers

import com.google.firebase.database.*
import com.hubrickchallenge.android.model.Event
import io.reactivex.Observer

/**
 * Created by joaocruz04 on 11/07/2017.
 */
class FeedConsumer {


    var subscribers: ArrayList<Observer<ArrayList<Event>>> = ArrayList()

    var database: DatabaseReference?=null
    var eventListener = object: ValueEventListener {
        override fun onCancelled(p0: DatabaseError?) {
            println("Cancelled")
        }

        override fun onDataChange(p0: DataSnapshot?) {
            val events = ArrayList<Event>()
            p0?.children?.forEach {
                var event = it.getValue(Event::class.java)
                if (event!=null) events.add(event)
            }
            subscribers.forEach { it.onNext(events)}
        }

    }

    fun subscribe(observer: Observer<ArrayList<Event>>) {
        if (!subscribers.contains(observer))
            subscribers.add(observer)
        if (subscribers.size == 1)
            start()
    }

    fun unsubscribe(observer: Observer<ArrayList<Event>>) {
        subscribers.remove(observer)
        if (subscribers.size==0)
            stop()
    }

    fun start() {
        database = FirebaseDatabase.getInstance().reference.child("feeds")
        database?.addValueEventListener(eventListener)
    }

    fun stop() {
        database?.removeEventListener(eventListener)
    }

}