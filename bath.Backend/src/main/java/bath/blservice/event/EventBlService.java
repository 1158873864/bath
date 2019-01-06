package bath.blservice.event;

import bath.exception.EventDoesNotExistException;
import bath.parameters.event.EventAddParameters;
import bath.response.event.EventAddResponse;
import bath.response.event.EventDeleteResponse;
import bath.response.event.EventLoadResponse;
import bath.response.event.EventWithIdLoadResponse;

public interface EventBlService {
    /**
     * load all events
     *
     * @return
     */
    EventLoadResponse loadEvents();

    /**
     * load all events with their ids
     *
     * @return
     */
    EventWithIdLoadResponse loadEventsWithId();

    /**
     * delete the event by id
     *
     * @param eventId
     * @return
     */
    EventDeleteResponse deleteEvent(int eventId) throws EventDoesNotExistException;

    /**
     * add event
     *
     * @param eventAddParameters
     * @return
     */
    EventAddResponse addEvent(EventAddParameters eventAddParameters);
}
