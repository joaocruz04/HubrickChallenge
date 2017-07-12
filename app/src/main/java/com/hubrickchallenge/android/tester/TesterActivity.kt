package com.hubrickchallenge.android.tester

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

import com.hubrickchallenge.android.R
import com.hubrickchallenge.android.model.*
import java.util.*
import kotlin.collections.ArrayList

class TesterActivity : AppCompatActivity() {


    var authors: ArrayList<Triple<String, Author, Stats>> = ArrayList()
    var payloadNormal: Payload = Payload()
    var payloadUpdate: Payload = Payload()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tester)

        //images.add("")

        payloadNormal.headlineImage = ImageResource("http://www.konbini.com/wp-content/blogs.dir/9/files/2016/01/friends-banner-photo-810x557.jpg", "image/png")
        payloadNormal.plainContentPreview = "If you missed it or if you want to see it again, check it out on Netflix!"
        payloadNormal.plainTitle = "Hey, remember Friends?"
        payloadNormal.path = "/tvshow/friends"

        payloadUpdate.headlineImage = ImageResource("http://d1qhuz9ahqnrhh.cloudfront.net/wp-content/uploads/2014/08/Netflix-Logo-banner2.jpg", "image/png")
        payloadUpdate.plainContentPreview = "All seasons are on Netflix! What are you waiting for?"
        payloadUpdate.plainTitle = "[UPDATED] Hey, remember Friends?"
        payloadUpdate.path = "/tvshow/friends"

        var stat1 = Stats(ReactionStats(ReactionCount(95, 3)), CommentStats(24))
        var stat2 = Stats(ReactionStats(ReactionCount(126, 45)), CommentStats(63))
        var stat3 = Stats(ReactionStats(ReactionCount(20, 3)), CommentStats(11))
        var stat4 = Stats(ReactionStats(ReactionCount(583, 94)), CommentStats(23))
        var stat5 = Stats(ReactionStats(ReactionCount(2, 0)), CommentStats(1))
        var stat6 = Stats(ReactionStats(ReactionCount(837, 540)), CommentStats(201))

        authors.add(Triple("", Author("chandler", "Chandler Bing", ImageResource("http://68.media.tumblr.com/avatar_e3a5974531dc_128.png", "png")), stat1))
        authors.add(Triple("", Author("joey", "Joey Tribbiani", ImageResource("https://cdn.meme.am/images/50x50/8532575.jpg", "png")), stat2))
        authors.add(Triple("", Author("ross", "Ross Geller", ImageResource("https://cdn.meme.am/cache/images/folder546/50x50/12354546.jpg", "png")), stat3))
        authors.add(Triple("", Author("rachel", "Rachel Green", ImageResource("https://68.media.tumblr.com/avatar_78d4c7926758_128.png", "png")), stat4))
        authors.add(Triple("", Author("monica", "Monica Geller", ImageResource("https://68.media.tumblr.com/avatar_faa0951e9505_128.png", "png")), stat5))
        authors.add(Triple("", Author("phoebe", "Phoebe Buffay", ImageResource("https://68.media.tumblr.com/avatar_2682adaae92a_128.png", "png")), stat6))

        findViewById(R.id.clearBt).setOnClickListener { clear() }

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
        val stat = authors[pos-1].third
        event.type = "ADD"
        event.id = id
        event.author = author
        event.payload = payloadNormal
        event.payload?.stats = stat
        authors[pos-1] = Triple(id, author, stat)
        pushEvent(event)
    }

    fun onUpdate(pos: Int) {
        val event = Event()
        val id = authors[pos-1].first
        val author = authors[pos-1].second
        val stat = authors[pos-1].third
        event.type = "UPDATE"
        event.id = id
        event.author = author
        event.payload = payloadUpdate
        event.payload?.stats = stat
        authors[pos-1] = Triple(id, author, stat)
        pushEvent(event)
    }

    fun onDelete(pos: Int) {
        val event = Event()
        val id = authors[pos-1].first
        val author = authors[pos-1].second
        val stat = authors[pos-1].third
        event.type = "DELETE"
        event.id = id
        event.author = author
        event.payload?.stats = stat
        authors[pos-1] = Triple(id, author, stat)
        pushEvent(event)
    }

    fun pushEvent(event: Event) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("feeds").push()
        myRef.setValue(event)
    }

    fun clear() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("feeds")
        myRef.removeValue()
    }

}
