package com.hubrickchallenge.android.feed

import com.hubrickchallenge.android.App
import com.hubrickchallenge.android.managers.FeedConsumer
import com.hubrickchallenge.android.model.Event
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.Math.abs
import javax.inject.Inject

/**
 * Created by joaocruz04 on 10/07/2017.
 */
class FeedPresenterImpl : FeedPresenter, Observer<ArrayList<Event>> {

    var view: FeedView?=null
    var events: ArrayList<Event> = ArrayList()

    @Inject
    lateinit var feedConsumer: FeedConsumer

    override fun registerView(view: FeedView) {
        this.view = view
        App.getInstance().appComponent.inject(this)
        events = ArrayList()
    }

    override fun start() {
        feedConsumer.subscribe(this)
    }

    override fun stop() {
        feedConsumer.unsubscribe(this)
    }

    override fun countBubbleClicked() {
        view?.hideCountTooltip()
        view?.showTop()
    }

    fun updateList(firstList: ArrayList<Event>, newList: ArrayList<Event>): Int {
        var tempArray = ArrayList<Event>()

        newList.forEach {
            var newItem = it
            var found = false
            if (firstList.size>0)
                for (i in 0..firstList.size-1) {
                    var oldItem = firstList[i]
                    if (oldItem.id == newItem.id) {
                        firstList[i] = newItem
                        found = true
                        break
                    }
                }
            if (!found)
                tempArray.add(0, newItem)
        }
        firstList.addAll(0, tempArray)
        return tempArray.size
    }

    fun notifyViewOfDataChanges(changedCount: Int) {
        var positionAndOffset = view?.getCurrentPositionAndOffset()
        if (positionAndOffset?.first?:0>0 || abs(positionAndOffset?.second?:0)>10){
            view?.showCountTooltip(changedCount, 3000)
        }
        view?.updateData(changedCount)
    }


    override fun onNext(t: ArrayList<Event>) {
        var count = updateList(events, t)
        notifyViewOfDataChanges(count)
    }

    override fun onComplete() {}
    override fun onSubscribe(d: Disposable?) {}
    override fun onError(e: Throwable?) { println("oops!") }

}