package com.hubrickchallenge.android.feed

/**
 * Created by joaocruz04 on 10/07/2017.
 */
interface FeedPresenter {

    fun registerView(view: FeedView)
    fun start()
    fun stop()
}