package com.hubrickchallenge.android.managers

import com.google.firebase.database.*
import com.hubrickchallenge.android.model.Event
import io.reactivex.Observer

/**
 * Created by joaocruz04 on 11/07/2017.
 */
class FeedConsumer(dbRef: DatabaseReference?) {

    private var subscribers: ArrayList<Observer<ArrayList<Event>>> = ArrayList()
    private var database: DatabaseReference? = dbRef
    private var hasStarted = false

    private var childListener = object: ChildEventListener {
        override fun onCancelled(p0: DatabaseError?) {}
        override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}
        override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}
        override fun onChildRemoved(p0: DataSnapshot?) {}
        override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
            var event = p0?.getValue(Event::class.java)
            if (event!=null) {
                var list :ArrayList<Event> = ArrayList()
                list.add(event)
                subscribers.forEach { it.onNext(list)}
            }
        }
    }


    fun subscribe(observer: Observer<ArrayList<Event>>?) {
        if (observer!=null) {
            if (!subscribers.contains(observer)) {
                subscribers.add(observer)
                if (subscribers.size == 1)
                    start()
            }
        }
    }

    fun unsubscribe(observer: Observer<ArrayList<Event>>?) {
        if (observer!=null) {
            var removed = subscribers.remove(observer)
            if (removed && subscribers.size == 0)
                stop()
        }
    }

    private fun start() {
        hasStarted = true
        database?.addChildEventListener(childListener)
    }

    private fun stop() {
        hasStarted = false
        database?.removeEventListener(childListener)
    }

    fun subscriberCount(): Int {
        return subscribers.size
    }

    fun started(): Boolean {
        return hasStarted
    }

}