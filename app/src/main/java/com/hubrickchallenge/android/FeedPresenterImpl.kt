package com.hubrickchallenge.android

import com.hubrickchallenge.android.model.Event

/**
 * Created by joaocruz04 on 10/07/2017.
 */
class FeedPresenterImpl : FeedPresenter {

    var view: FeedView?=null
    var events: ArrayList<Event>?=null


    override fun registerView(view: FeedView) {
        this.view = view
    }
}