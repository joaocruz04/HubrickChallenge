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
    var mEvents: ArrayList<Event> = ArrayList()

    @Inject
    lateinit var feedConsumer: FeedConsumer

    override fun registerView(view: FeedView) {
        this.view = view
        App.getInstance().appComponent.inject(this)
        mEvents = ArrayList()
    }

    override fun start() {
        feedConsumer.subscribe(this)
    }

    override fun stop() {
        feedConsumer.unsubscribe(this)
    }

    override fun getEvents(): ArrayList<Event> {
        return mEvents
    }

    override fun countBubbleClicked() {
        view?.hideCountTooltip()
        view?.showTop()
    }

    fun updateList(firstList: ArrayList<Event>, newList: ArrayList<Event>): Int {
        var tempArray = ArrayList<Event>()
        newList.forEach {
            var newItem = it
            if (isValidType(newItem)) {
                val obj = firstList.firstOrNull { it.id == newItem.id }
                if (obj != null)
                    updateEventFromNewEvent(obj, newItem)
                else if (newItem.type == "ADD")
                    tempArray.add(0, newItem)
            }
        }
        firstList.addAll(0, tempArray)
        return tempArray.size
    }

    fun updateEventFromNewEvent(originalEvent: Event, newEvent: Event) {
        originalEvent.payload = newEvent.payload
        originalEvent.type = newEvent.type
        originalEvent.author = newEvent.author
    }

    fun notifyViewOfDataChanges(changedCount: Int) {
        var positionAndOffset = view?.getCurrentPositionAndOffset()
        if (changedCount>0 && (positionAndOffset?.first?:0>0 || abs(positionAndOffset?.second?:0)>10)){
            view?.showCountTooltip(changedCount, 3000)
        }
        view?.updateData(changedCount)
    }


    override fun onNext(t: ArrayList<Event>) {
        var count = updateList(mEvents, t)
        notifyViewOfDataChanges(count)
    }

    override fun onComplete() {}
    override fun onSubscribe(d: Disposable?) {}
    override fun onError(e: Throwable?) {}


    fun isValidType(event: Event?): Boolean {
        val type = event?.type
        return (type == "ADD" ||
                type == "DELETE" ||
                type == "UPDATE")
    }
}