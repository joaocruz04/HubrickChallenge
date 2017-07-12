package com.hubrickchallenge.android.dagger

import com.hubrickchallenge.android.App
import com.hubrickchallenge.android.feed.FeedPresenterImpl
import com.hubrickchallenge.android.feed.FeedViewImpl
import dagger.Component
import javax.inject.Singleton

/**
 * Created by joaocruz04 on 11/07/2017.
 */


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(feedPresenterImpl: FeedPresenterImpl)
    fun inject(feedViewImpl: FeedViewImpl)
}