package bath.springcontroller.event;

import bath.blservice.event.EventBlService;
import bath.publicdatas.account.Role;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.event.EventLoadResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize(value = "hasRole('" + Role.USER_NAME + "')")
@RestController
public class CustomerEventController {
    private final EventBlService eventBlService;

    @Autowired
    public CustomerEventController(EventBlService eventBlService) {
        this.eventBlService = eventBlService;
    }

    @ApiOperation(value = "加载活动", notes = "加载所有现有活动信息")
    @RequestMapping(value = "/customer/event", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> loadEvents() {
        return new ResponseEntity<>(eventBlService.loadEvents(), HttpStatus.OK);
    }
}
