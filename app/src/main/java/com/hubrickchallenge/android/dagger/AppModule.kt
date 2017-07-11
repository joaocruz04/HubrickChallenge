package com.hubrickchallenge.android.dagger

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
        return FeedConsumer()
    }


}