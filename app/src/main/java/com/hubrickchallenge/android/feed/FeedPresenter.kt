package com.hubrickchallenge.android.feed

import com.hubrickchallenge.android.model.Event

/**
 * Created by joaocruz04 on 10/07/2017.
 */
interface FeedPresenter {

    fun registerView(view: FeedView)
    fun start()
    fun stop()
    fun countBubbleClicked()
    fun getEvents() : ArrayList<Event>
}