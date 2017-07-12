package com.hubrickchallenge.android.feed_presenter;

import com.hubrickchallenge.android.feed.FeedPresenterImpl;
import com.hubrickchallenge.android.model.Event;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestFeedPresenterUpdateList {

    private ArrayList<Event> oldList;
    private ArrayList<Event> newList;
    private FeedPresenterImpl presenter;

    @Before
    public void initialSetup() {
        oldList = new ArrayList<>();
        newList = new ArrayList<>();
        presenter = new FeedPresenterImpl();
    }

    /**
     * Adding an ADD Event to a non-null list should add that
     * event to that list
     * @throws Exception
     */

    @Test
    public void addEventToEmptyList() throws Exception {
        newList.add(getNewEvent("1", "ADD"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),1);
        assertEquals(oldList.get(0).getId(), "1");
        assertEquals(count, 1); // 1 item was added
    }

    /**
     * Adding a UPDATE Event to an empty non-null list should not
     * influence the list
     * @throws Exception
     */

    @Test
    public void updateEventToEmptyList() throws Exception {
        newList.add(getNewEvent("1", "UPDATE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),0);
        assertEquals(count, 0); // No items were added
    }

    /**
     * Adding a UPDATE Event to a list already containing the element,
     * should have that element updated
     * @throws Exception
     */

    @Test
    public void updateEventToListContainingElement() throws Exception {
        oldList.add(getNewEvent("1", "ADD"));
        newList.add(getNewEvent("1", "UPDATE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),1);
        assertEquals(oldList.get(0).getId(), "1");
        assertEquals(oldList.get(0).getType(), "UPDATE");
        assertEquals(count, 0); // No items were added
    }

    /**
     * Adding a DELETE Event to a non-null list should not influence the list
     * @throws Exception
     */

    @Test
    public void deleteEventToEmptyList() throws Exception {
        newList.add(getNewEvent("1", "DELETE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),0);
        assertEquals(count, 0); // No items were added
    }


    /**
     * Adding a DELETE Event to a list already containing the element,
     * should have that element updated
     * @throws Exception
     */

    @Test
    public void deleteEventToListContainingElement() throws Exception {
        oldList.add(getNewEvent("1", "ADD"));
        newList.add(getNewEvent("1", "DELETE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),1);
        assertEquals(oldList.get(0).getId(), "1");
        assertEquals(oldList.get(0).getType(), "DELETE");
        assertEquals(count, 0); // No items were added
    }


    /**
     * Adding an unknown Event to an empty list should NOT influence the list
     * @throws Exception
     */

    @Test
    public void unknownEventToEmptyList() throws Exception {
        newList.add(getNewEvent("1", "UNKNOWNTYPE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),0);
        assertEquals(count, 0); // No items were added
    }

    /**
     * Adding an unknown Event to a list already containing the element,
     * should NOT have that element updated
     * @throws Exception
     */

    @Test
    public void unknownEventToListContainingElement() throws Exception {
        oldList.add(getNewEvent("1", "ADD"));
        newList.add(getNewEvent("1", "UNKNOWNTYPE"));
        int count = presenter.updateList(oldList, newList);
        assertEquals(oldList.size(),1);
        assertEquals(oldList.get(0).getId(), "1");
        assertEquals(oldList.get(0).getType(), "ADD");
        assertEquals(count, 0); // No items were added
    }




    // Helpers

    private Event getNewEvent(String id, String type) {
        Event event = new Event();
        event.setId(id);
        event.setType(type);
        return event;
    }



}
