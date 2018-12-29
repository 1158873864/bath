package bath.springcontroller.purchase;

import bath.blservice.purchase.PurchaseBlService;
import bath.exception.NotExistException;
import bath.response.BoolResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.event.EventLoadResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class purchaseController {
    private final PurchaseBlService purchaseBlService;
    @Autowired
    public purchaseController(PurchaseBlService purchaseBlService) {
        this.purchaseBlService = purchaseBlService;
    }

    @ApiOperation(value = "用户通过微信支付购买积分", notes = "用户通过微信支付购买积分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户自己的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "credit", value = "用户要购买的积分数", required = true, dataType = "int")
    })
    @RequestMapping(value = "/buyMyCredit", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> buyMyCredit(@RequestParam(name="openid")String openid, @RequestParam(name="credit")int credit) {
        return new ResponseEntity<>(purchaseBlService.buyMyCredit(openid,credit), HttpStatus.OK);
    }

    @ApiOperation(value = "此接口用户接收微信支付后台的支付结果通知", notes = "此接口用户接收微信支付后台的支付结果通知")
    @RequestMapping(value = "/getWxPayResult", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public String getWxPayResult(HttpServletRequest httpServletRequest) {
        return purchaseBlService.getWxPayResult(httpServletRequest);
    }

    @ApiOperation(value = "用户下订单", notes = "用户下订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户自己的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "要查看的用户的openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detail", value = "用户自己的微信openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "要查看的用户的openid", required = true, dataType = "int"),
            @ApiImplicitParam(name = "date", value = "用户自己的微信openid", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addPurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public BoolResponse addPurchase(@RequestParam(name="openid")String openid, @RequestParam(name="type")String type, @RequestParam(name="detail")String detail, @RequestParam(name="price")int price, @RequestParam(name="date")String date) {
        return purchaseBlService.addPurchase(openid,type,detail,price,date);
    }

    @ApiOperation(value = "根据订单号获取单个订单信息", notes = "根据订单号获取单个订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getPurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPurchase(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(purchaseBlService.getPurchase(id), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己的订单列表", notes = "用户获取自己的订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户微信openid", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/getMyPurchaseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyPurchaseList(@RequestParam(name="openid")String openid) {
        return new ResponseEntity<>(purchaseBlService.getMyPurchaseList(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有订单信息", notes = "获取所有订单信息")
    @RequestMapping(value = "/getPurchaseList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getPurchaseList() {
        return new ResponseEntity<>(purchaseBlService.getPurchaseList(), HttpStatus.OK);
    }

    @ApiOperation(value = "根据订单ID删除订单信息", notes = "根据订单ID删除订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户微信openid", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/deletePurchase", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> deletePurchase(@RequestParam(name="id")String id) throws NotExistException {
        return new ResponseEntity<>(purchaseBlService.deletePurchase(id), HttpStatus.OK);
    }






}
