package bath.springcontroller.event;

import bath.blservice.event.EventBlService;
import bath.exception.EventDoesNotExistException;
import bath.parameters.event.EventAddParameters;
import bath.publicdatas.account.Role;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.event.EventAddResponse;
import bath.response.event.EventLoadResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@PreAuthorize(value = "hasRole('" + Role.RESTAURANT_NAME + "')")
@RestController
public class SupplierEventController {
    private final EventBlService eventBlService;

    @Autowired
    public SupplierEventController(EventBlService eventBlService) {
        this.eventBlService = eventBlService;
    }

    @ApiOperation(value = "加载带id活动", notes = "加载所有现有活动信息（包括ID）")
    @RequestMapping(value = "supplier/event", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> loadEvents() {
        return new ResponseEntity<>(eventBlService.loadEventsWithId(), HttpStatus.OK);
    }

    @ApiOperation(value = "删除活动", notes = "删除指定id的活动）")
    @RequestMapping(value = "supplier/event/{eventId}", method = RequestMethod.DELETE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteEvents(@PathVariable(name = "eventId") int eventId) throws EventDoesNotExistException {
        return new ResponseEntity<>(eventBlService.deleteEvent(eventId), HttpStatus.OK);
    }

    @ApiOperation(value = "添加获得", notes = "添加一个新活动")
    @RequestMapping(value = "supplier/event", method = RequestMethod.PUT)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventAddResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addEvent(@RequestBody EventAddParameters eventAddParameters) {
        return new ResponseEntity<>(eventBlService.addEvent(eventAddParameters), HttpStatus.OK);
    }

}
