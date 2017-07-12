package com.hubrickchallenge.android.dagger

import com.google.firebase.database.FirebaseDatabase
import com.hubrickchallenge.android.feed.FeedPresenter
import com.hubrickchallenge.android.feed.FeedPresenterImpl
import com.hubrickchallenge.android.managers.FeedConsumer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by joaocruz04 on 11/07/2017.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideFeedConsumer(): FeedConsumer{
        return FeedConsumer(FirebaseDatabase.getInstance().reference.child("feeds"))
    }

    @Provides
    internal fun provideFeedPresenter(): FeedPresenter {
        return FeedPresenterImpl()
    }


}