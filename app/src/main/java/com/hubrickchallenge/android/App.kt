package com.hubrickchallenge.android

import android.app.Application
import com.hubrickchallenge.android.managers.FeedConsumer
import com.hubrickchallenge.android.model.Event
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by joaocruz04 on 11/07/2017.
 */
class App : Application(), Observer<ArrayList<Event>> {

    var feedConsumer : FeedConsumer? = null

    override fun onCreate() {
        super.onCreate()
        feedConsumer = FeedConsumer()
        feedConsumer?.subscribe(this)
    }
    

    override fun onNext(t: ArrayList<Event>?) {
        println("")
        println("Heeey!")
    }

    override fun onSubscribe(d: Disposable?) {}
    override fun onError(e: Throwable?) {}
    override fun onComplete() {}

}