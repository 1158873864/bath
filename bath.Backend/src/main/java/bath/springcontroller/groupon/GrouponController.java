package bath.springcontroller.groupon;

import bath.blservice.groupon.GrouponBlService;
import bath.exception.NotExistException;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.event.EventLoadResponse;
import bath.util.FormatDateTime;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GrouponController {
    private final GrouponBlService grouponBlService;
    @Autowired
    public GrouponController(GrouponBlService grouponBlService){
        this.grouponBlService=grouponBlService;
    }

    @ApiOperation(value = "管理员新建团购", notes = "管理员新建团购")
    @RequestMapping(value = "addGroupon", method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="团购名称",required = true,dataType = "String"),
            @ApiImplicitParam(name="originalPrice",value="原价",required = true,dataType = "double"),
            @ApiImplicitParam(name="price",value="售价",required = true,dataType = "double"),
            @ApiImplicitParam(name="takeEffectTime",value="生效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="loseEffectTime",value="失效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="putOnShelvesTime",value="上架时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="pullOffShelvesTime",value="下架编码",required = true,dataType = "String"),
            @ApiImplicitParam(name="description",value="描述",required = true,dataType = "String"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "int")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> addGroupon(@RequestParam(name="name")String name,@RequestParam(name="originalPrice")double originalPrice,@RequestParam(name="price")double price,@RequestParam(name="takeEffectTime")String takeEffectTime,@RequestParam(name="loseEffectTime")String loseEffectTime,@RequestParam(name="putOnShelvesTime")String putOnShelvesTime,@RequestParam(name="pullOffShelvesTime")String pullOffShelvesTime,@RequestParam(name="description")String description,@RequestParam(name="amount")int amount) {
        return new ResponseEntity<>(grouponBlService.addGroupon(name,originalPrice,price, FormatDateTime.fromShortDateString(takeEffectTime),FormatDateTime.fromShortDateString(loseEffectTime),FormatDateTime.fromShortDateString(putOnShelvesTime),FormatDateTime.fromShortDateString(pullOffShelvesTime),description,amount), HttpStatus.OK);
    }

    @ApiOperation(value="管理员更改团购",notes="管理员更改团购")
    @RequestMapping(value="updateGroupon",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="团购id",required = true,dataType = "String"),
            @ApiImplicitParam(name="name",value="团购名称",required = true,dataType = "String"),
            @ApiImplicitParam(name="originalPrice",value="原价",required = true,dataType = "double"),
            @ApiImplicitParam(name="price",value="售价",required = true,dataType = "double"),
            @ApiImplicitParam(name="takeEffectTime",value="生效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="loseEffectTime",value="失效时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="putOnShelvesTime",value="上架时间",required = true,dataType = "String"),
            @ApiImplicitParam(name="pullOffShelvesTime",value="下架编码",required = true,dataType = "String"),
            @ApiImplicitParam(name="description",value="描述",required = true,dataType = "String"),
            @ApiImplicitParam(name="amount",value="数量",required = true,dataType = "int")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateGroupon(@RequestParam(name="id")String id,@RequestParam(name="name")String name,@RequestParam(name="originalPrice")double originalPrice,@RequestParam(name="price")double price,@RequestParam(name="takeEffectTime")String takeEffectTime,@RequestParam(name="loseEffectTime")String loseEffectTime,@RequestParam(name="putOnShelvesTime")String putOnShelvesTime,@RequestParam(name="pullOffShelvesTime")String pullOffShelvesTime,@RequestParam(name="description")String description,@RequestParam(name="amount")int amount)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.updateGroupon(id,name,originalPrice,price, FormatDateTime.fromShortDateString(takeEffectTime),FormatDateTime.fromShortDateString(loseEffectTime),FormatDateTime.fromShortDateString(putOnShelvesTime),FormatDateTime.fromShortDateString(pullOffShelvesTime),description,amount), HttpStatus.OK);
    }

    @ApiOperation(value="管理员删除团购",notes="管理员删除团购")
    @RequestMapping(value="deleteGroupon",method = POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deleteGroupon(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.deleteGroupon(id),HttpStatus.OK);
    }

    @ApiOperation(value="获取所有团购",notes="获取所有团购")
    @RequestMapping(value="grouponList",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getGrouponList()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAll(),HttpStatus.OK);
    }

    @ApiOperation(value="通过id获取团购",notes="通过id获取团购")
    @RequestMapping(value="groupon",method = GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "团购id", required = true, dataType = "String")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getGroupon(@RequestParam(name="id")String id)throws NotExistException{
        return new ResponseEntity<>(grouponBlService.findById(id),HttpStatus.OK);
    }

    @ApiOperation(value="获取正在销售的团购",notes="获取正在销售的团购")
    @RequestMapping(value="groupon/available",method = GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 403, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getAvailableGroupon()throws NotExistException{
        return new ResponseEntity<>(grouponBlService.getAvailableGroupon(),HttpStatus.OK);
    }

}
