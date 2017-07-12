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


    var authors: ArrayList<Pair<String, Author>> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tester)

        authors.add(Pair("", Author("chandler", "Chandler Bing", AvatarImage("http://68.media.tumblr.com/avatar_e3a5974531dc_128.png", "png"))))
        authors.add(Pair("", Author("joey", "Joey Tribbiani", AvatarImage("https://cdn.meme.am/images/50x50/8532575.jpg", "png"))))
        authors.add(Pair("", Author("ross", "Ross Geller", AvatarImage("https://cdn.meme.am/cache/images/folder546/50x50/12354546.jpg", "png"))))
        authors.add(Pair("", Author("rachel", "Rachel Green", AvatarImage("https://68.media.tumblr.com/avatar_78d4c7926758_128.png", "png"))))
        authors.add(Pair("", Author("monica", "Monica Geller", AvatarImage("https://68.media.tumblr.com/avatar_faa0951e9505_128.png", "png"))))
        authors.add(Pair("", Author("phoebe", "Phoebe Buffay", AvatarImage("https://68.media.tumblr.com/avatar_2682adaae92a_128.png", "png"))))

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
        val event = Event()
        val id = "" + Calendar.getInstance().timeInMillis
        val author = authors[pos-1].second
        event.type = "ADD"
        event.id = id
        event.author = author
        authors[pos-1] = Pair(id, author)
        pushEvent(event)
    }

    fun onUpdate(pos: Int) {
        val event = Event()
        val id = authors[pos-1].first
        val author = authors[pos-1].second
        event.type = "UPDATE"
        event.id = id
        event.author = author
        authors[pos-1] = Pair(id, author)
        pushEvent(event)
    }

    fun onDelete(pos: Int) {
        val event = Event()
        val id = authors[pos-1].first
        val author = authors[pos-1].second
        event.type = "DELETE"
        event.id = id
        event.author = author
        authors[pos-1] = Pair(id, author)
        pushEvent(event)
    }

    fun pushEvent(event: Event) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("feeds").push()
        myRef.setValue(event)
    }

}
