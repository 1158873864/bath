package bath.response.event;

import bath.response.Response;

public class EventAddResponse extends Response {
    private int eventId;

    public EventAddResponse() {
    }

    public EventAddResponse(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
