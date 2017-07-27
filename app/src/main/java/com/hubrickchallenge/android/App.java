package com.hubrickchallenge.android;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.hubrickchallenge.android.dagger.AppComponent;
import com.hubrickchallenge.android.dagger.AppModule;
import com.hubrickchallenge.android.dagger.DaggerAppComponent;
import com.hubrickchallenge.android.feed.FeedViewImpl;
import com.hubrickchallenge.android.managers.FeedConsumer;
import com.hubrickchallenge.android.model.Event;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by joaocruz04 on 11/07/2017.
 */

public class App extends Application implements Observer<ArrayList<Event>> {

    private AppComponent appComponent;
    private static App instance;

    @Inject
    FeedConsumer feedConsumer;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
        appComponent.inject(this);
        feedConsumer.appSubscribe(this);
    }

    public static App getInstance() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }



    @Override
    public void onNext(ArrayList<Event> events) {
        if (feedConsumer.subscriberCount()==0) {
            String title = events.size()>1 ? "New activity from friends" : events.get(0).getAuthor().getDisplayName();
            String message = events.size()>1 ? "Some of your friends posted something. Check that now!" : events.get(0).getPayload().getPlainContentPreview();
            sendNotification(title, message);
        }
    }

    private void sendNotification(String title, String message) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_input_add)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(message);
        Intent resultIntent = new Intent(this, FeedViewImpl.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(FeedViewImpl.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1234, mBuilder.build());
    }




    @Override
    public void onError(Throwable e) {}
    @Override
    public void onComplete() {}
    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("APP SUBSCRIBED TO FEED CONSUMER");
    }
}
