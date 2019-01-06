package bath.bl.event;
import bath.blservice.event.EventBlService;
import bath.dataservice.event.EventDataService;
import bath.entity.event.FirstOrderEvent;
import bath.entity.event.FullSubtractionEvent;
import bath.entity.event.ItemSubtractionEvent;
import bath.entity.event.ItemSubtractionOnceEvent;
import bath.exception.EventDoesNotExistException;
import bath.parameters.event.*;
import bath.publicdatas.event.EventState;
import bath.publicdatas.event.EventType;
import bath.response.event.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventBlServiceImpl implements EventBlService {
    private final EventDataService eventDataService;

    @Autowired
    public EventBlServiceImpl(EventDataService eventDataService) {
        this.eventDataService = eventDataService;
    }

    /**
     * load all events
     *
     * @return
     */
    @Override
    public EventLoadResponse loadEvents() {
        List<String> eventContentList = eventDataService.getAllEvents().stream().filter((event -> event.getEventState() == EventState.ACTIVE)).collect(ArrayList::new, (list, event) -> list.add(event.getContent()), ArrayList::addAll);
        return new EventLoadResponse(eventContentList);
    }

    /**
     * load all events with their ids
     *
     * @return
     */
    @Override
    public EventWithIdLoadResponse loadEventsWithId() {
        List<EventItem> eventContentList = eventDataService.getAllEvents().stream().filter((event -> event.getEventState() == EventState.ACTIVE)).collect(ArrayList::new, (list, event) -> list.add(new EventItem(event.getId(), event.getContent())), ArrayList::addAll);
        return new EventWithIdLoadResponse(eventContentList);
    }

    /**
     * delete the event by id
     *
     * @param eventId
     * @return
     */
    @Override
    public EventDeleteResponse deleteEvent(int eventId) throws EventDoesNotExistException {
        eventDataService.deleteEvent(eventId);
        return new EventDeleteResponse(eventId);
    }

    /**
     * add event
     *
     * @param eventAddParameters
     * @return
     */
    @Override
    public EventAddResponse addEvent(EventAddParameters eventAddParameters) {
        switch (eventAddParameters.getEventType()) {
            case ItemSubtraction:
                ItemSubtractionEventParameters itemSubtractionEventParameters = (ItemSubtractionEventParameters) eventAddParameters;
                ItemSubtractionEvent itemSubtractionEvent = new ItemSubtractionEvent(itemSubtractionEventParameters.getDescription(), EventState.ACTIVE, itemSubtractionEventParameters.getItemList(), ((ItemSubtractionEventParameters) eventAddParameters).getMinusPrice());
                return new EventAddResponse(eventDataService.addEvent(itemSubtractionEvent).getId());
            case FirstOrder:
                FirstOrderEventParameters firstOrderEventParameters = (FirstOrderEventParameters) eventAddParameters;
                FirstOrderEvent firstOrderEvent = new FirstOrderEvent(firstOrderEventParameters.getDescription(), EventState.ACTIVE, firstOrderEventParameters.getMinusPrice());
                return new EventAddResponse(eventDataService.addEvent(firstOrderEvent).getId());
            case FullSubtraction:
                FullSubtractionEventParameters fullSubtractionEventParameters = (FullSubtractionEventParameters) eventAddParameters;
                FullSubtractionEvent fullSubtractionEvent = new FullSubtractionEvent(fullSubtractionEventParameters.getDescription(), EventState.ACTIVE, fullSubtractionEventParameters.getFullPrice(), fullSubtractionEventParameters.getMinusPrice());
                return new EventAddResponse(eventDataService.addEvent(fullSubtractionEvent).getId());
            case ItemSubtractionOnce:
                ItemSubtractionOnceEventParameters itemSubtractionOnceEventParameters = (ItemSubtractionOnceEventParameters) eventAddParameters;
                ItemSubtractionOnceEvent itemSubtractionOnceEvent = new ItemSubtractionOnceEvent(itemSubtractionOnceEventParameters.getDescription(), EventState.ACTIVE, itemSubtractionOnceEventParameters.getItemId(), itemSubtractionOnceEventParameters.getMinusPrice());
                return new EventAddResponse(eventDataService.addEvent(itemSubtractionOnceEvent).getId());
        }
        return new EventAddResponse();
    }
}
