package com.hubrickchallenge.android.feed_consumer;

import com.hubrickchallenge.android.feed.FeedPresenterImpl;
import com.hubrickchallenge.android.managers.FeedConsumer;
import com.hubrickchallenge.android.model.Event;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.assertEquals;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestFeedConsumerSubscription {

    private FeedConsumer consumer;

    @Before
    public void initialSetup() {
        consumer = new FeedConsumer(null); // No database.
    }

    /**
     * Test adding a valid subscriber
     * @throws Exception
     */

    @Test
    public void validSubscriber() throws Exception {
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
        consumer.subscribe(newEventsObserver());
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);
    }

    /**
     * Test adding a duplicated subscriber
     * @throws Exception
     */

    @Test
    public void duplicatedSubscriber() throws Exception {
        Observer<ArrayList<Event>> observer = newEventsObserver();
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
        consumer.subscribe(observer);
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);
        consumer.subscribe(observer);
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);
    }

    /**
     * Test adding a null subscriber
     * @throws Exception
     */

    @Test
    public void nullSubscriber() throws Exception {
        assertEquals(consumer.subscriberCount(), 0);
        consumer.subscribe(null);
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
    }

    /**
     * Test unsubscribing a valid subscriber
     * @throws Exception
     */

    @Test
    public void validUnsubscriber() throws Exception {
        Observer<ArrayList<Event>> observer = newEventsObserver();

        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
        consumer.subscribe(observer);
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);

        consumer.unsubscribe(observer);
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
    }

    /**
     * Test unsubscribing an invalid subscriber
     * @throws Exception
     */

    @Test
    public void invaliddUnsubscriber() throws Exception {
        Observer<ArrayList<Event>> observer1 = newEventsObserver();
        Observer<ArrayList<Event>> observer2 = newEventsObserver();

        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
        consumer.subscribe(observer1);
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);

        consumer.unsubscribe(observer2);
        assertEquals(consumer.subscriberCount(), 1);
        assertEquals(consumer.started(), true);
    }

    /**
     * Test unsubscribing a null subscriber
     * @throws Exception
     */

    @Test
    public void nullUnsubscriber() throws Exception {
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
        consumer.unsubscribe(null);
        assertEquals(consumer.subscriberCount(), 0);
        assertEquals(consumer.started(), false);
    }









    // Helper methods

    private Observer<ArrayList<Event>> newEventsObserver() {
        return new Observer<ArrayList<Event>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<Event> events) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


}
