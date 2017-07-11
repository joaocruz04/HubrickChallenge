package com.hubrickchallenge.android.tester

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.model.Author
import com.hubrickchallenge.android.model.AvatarImage
import com.hubrickchallenge.android.model.Event
import java.util.*

class TesterActivity : AppCompatActivity() {


    var events: ArrayList<Event> = ArrayList()
    var authors: ArrayList<Author> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tester)

        authors.add(Author("chandler", "Chandler Bing", AvatarImage("", "png")))
        authors.add(Author("joey", "Joey Tribbiani", AvatarImage("", "png")))
        authors.add(Author("ross", "Ross Geller", AvatarImage("", "png")))
        authors.add(Author("rachel", "Rachel Green", AvatarImage("", "png")))
        authors.add(Author("monica", "Monica Geller", AvatarImage("", "png")))
        authors.add(Author("phoebe", "Phoebe Buffay", AvatarImage("", "png")))

        findViewById(R.id.p1_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p2_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p3_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p4_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p5_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p6_add).setOnClickListener { onAdd(Integer.parseInt(it.getTag() as String)) }

        findViewById(R.id.p1_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p2_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p3_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p4_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p5_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p6_update).setOnClickListener { onUpdate(Integer.parseInt(it.getTag() as String)) }

        findViewById(R.id.p1_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p2_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p3_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p4_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p5_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }
        findViewById(R.id.p6_delete).setOnClickListener { onDelete(Integer.parseInt(it.getTag() as String)) }

    }

    fun onAdd(pos: Int) {
        var event = Event()
        event.type = "ADD"
        event.id = "" + Calendar.getInstance().timeInMillis
        event.author = authors[pos-1]
        pushEvent(event)
    }

    fun onUpdate(pos: Int) {
        var event = Event()
        event.type = "UPDATE"
        event.id = "" + Calendar.getInstance().timeInMillis
        event.author = authors[pos-1]
        pushEvent(event)
    }

    fun onDelete(pos: Int) {
        var event = Event()
        event.type = "DELETE"
        event.id = "" + Calendar.getInstance().timeInMillis
        event.author = authors[pos-1]
        pushEvent(event)
    }

    fun pushEvent(event: Event) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("feeds").push()
        myRef.setValue(event)
    }

}
