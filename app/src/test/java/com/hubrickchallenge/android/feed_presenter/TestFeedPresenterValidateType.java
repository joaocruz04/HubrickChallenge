package com.hubrickchallenge.android.feed_presenter;

import com.hubrickchallenge.android.feed.FeedPresenterImpl;
import com.hubrickchallenge.android.model.Event;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by joaocruz04 on 12/07/2017.
 */

public class TestFeedPresenterValidateType {

    private FeedPresenterImpl presenter;

    @Before
    public void initialSetup() {
        presenter = new FeedPresenterImpl();
    }

    /**
     * Test isValidType function with unknown type
     * @throws Exception
     */

    @Test
    public void validateUnknownType() throws Exception {
        boolean b = presenter.isValidType(getNewEvent("1", "UNKNOWNTYPE"));
        assertEquals(b, false);
    }

    /**
     * Test isValidType function with null event
     * @throws Exception
     */

    @Test
    public void validateNullEvent() throws Exception {
        boolean b = presenter.isValidType(null);
        assertEquals(b, false);
    }

    /**
     * Test isValidType function with valid type
     * @throws Exception
     */

    @Test
    public void validateValidType() throws Exception {
        boolean add = presenter.isValidType(getNewEvent("1", "ADD"));
        boolean update = presenter.isValidType(getNewEvent("1", "UPDATE"));
        boolean delete = presenter.isValidType(getNewEvent("1", "DELETE"));
        assertEquals(add, true);
        assertEquals(update, true);
        assertEquals(delete, true);
    }




    // Helpers

    private Event getNewEvent(String id, String type) {
        Event event = new Event();
        event.setId(id);
        event.setType(type);
        return event;
    }



}
