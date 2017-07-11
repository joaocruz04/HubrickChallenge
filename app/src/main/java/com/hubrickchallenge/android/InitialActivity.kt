package com.hubrickchallenge.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hubrickchallenge.android.feed.FeedViewImpl
import com.hubrickchallenge.android.tester.TesterActivity

/**
 * Created by joaocruz04 on 11/07/2017.
 */
class InitialActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isTesting = false

        if (isTesting) {
            startActivity(Intent(this, TesterActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, FeedViewImpl::class.java))
            finish()
        }

    }

}