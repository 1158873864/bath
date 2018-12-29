package bath.springcontroller.user;

import bath.blservice.user.UserBlService;
import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.exception.CannotGetOpenIdAndSessionKeyException;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import bath.response.InfoResponse;
import bath.response.Response;
import bath.response.WrongResponse;
import bath.response.event.EventLoadResponse;
import bath.response.user.UserListResponse;
import bath.response.user.UserResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
public class userController {
    private final UserBlService userBlService;

    @Autowired
    public userController(UserBlService userBlService) {
        this.userBlService = userBlService;
    }

    private static String headPath = "";

    @ApiOperation(value = "获取用户头像", notes = "获取用户头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "face", value = "用户头像", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadHead", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public void uploadHead(@RequestParam("face") MultipartFile face) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (face.isEmpty()) {
            map.put("result", "error");
            map.put("msg", "上传文件不能为空");
        } else {

            // 获取文件名
            String fileName = face.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = face.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                headPath = fileName;
                byte[] buffer = new byte[20000000];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    @ApiOperation(value = "增加用户", notes = "增加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "用户手机", required = true, dataType = "String"),
            @ApiImplicitParam(name="addresses",value="地址",required = true,dataType = "ArrayList")
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse addUser(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username, @RequestParam(name="phone")String phone, @RequestParam(name="addresses")ArrayList<Address> addresses) throws NotExistException {
        boolean is = true;
        File file = new File(headPath);
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String[] temp = headPath.split("\\.");
        String path = "record/user/head/" + uuid + "." + temp[1];
        File tempfile = new File(path);
        if (tempfile.exists() && tempfile.isFile()) {
            tempfile.delete();
        }
        int bytesum = 0;
        int byteread = 0;

        try {
            InputStream inStream = new FileInputStream(headPath);
            FileOutputStream fs = new FileOutputStream(path);
            byte[] buffer = new byte[20000000];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;            //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        InfoResponse r = userBlService.addUser(openid, username,path,phone,addresses);
        headPath = "";
        return r;
    }

    //todo 地址传不过来QAQ
    @ApiOperation(value = "增加用户", notes = "增加用户")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "phone", value = "用户手机", required = true, dataType = "String"),
//            @ApiImplicitParam(name="addresses",value="默认地址",required = true,dataType = "List")
//
//    })
    @RequestMapping(path = "/addUserWithoutAvatar", method = RequestMethod.POST,produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse addUserWithoutFace(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username, @RequestParam(name = "phone") String phone, @RequestParam(name="addresses")List<Address> addresses) throws NotExistException {
        InfoResponse r = userBlService.addUser(openid, username, "", phone, addresses);
        return r;
    }
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public UserListResponse getUserList() {
        UserListResponse r = userBlService.getUserList();
        return r;
    }

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public UserResponse getUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        UserResponse r = userBlService.getUser(openid);
        return r;
    }

    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name="role",value="权限",required = true,dataType = "String"),
            @ApiImplicitParam(name="avatarUrl",value="用户头像",required =true,dataType="String"),
            @ApiImplicitParam(name = "phone", value = "用户手机", required = true, dataType = "String"),
            @ApiImplicitParam(name="levelName",value="用户等级",required = true,dataType = "String"),
            @ApiImplicitParam(name="integration",value="用户积分",required = true,dataType = "integer"),
            @ApiImplicitParam(name="balance",value="用户余额",required = true,dataType = "double"),
            @ApiImplicitParam(name="orders",value="订单",required = true,dataType = "ArrayList"),
            @ApiImplicitParam(name="carts",value="购物车",required = true,dataType = "ArrayList"),
            @ApiImplicitParam(name="addresses",value="地址",required = true,dataType = "ArrayList"),
            @ApiImplicitParam(name="coupons",value="优惠券",required = true,dataType = "ArrayList")

    })
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse updateUser(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username,@RequestParam("role")String role,  @RequestParam(name = "avatarUrl") String avatarUrl,@RequestParam("phone")String phone, @RequestParam(name = "levelName") String levelName, @RequestParam(name = "integration") int integration, @RequestParam(name = "balance") double balance, @RequestParam(name = "orders") ArrayList<Order> orders, @RequestParam(name = "carts")ArrayList<Groupon> carts, @RequestParam(name = "addresses") ArrayList<Address> addresses, @RequestParam(name = "coupons") ArrayList<Coupon> coupons) throws NotExistException {
        boolean is = true;
        File file = new File(headPath);
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String[] temp = headPath.split("\\.");
        String path = "record/user/head/" + uuid + "." + temp[1];
        File tempfile = new File(path);
        if (tempfile.exists() && tempfile.isFile()) {
            tempfile.delete();
        }
        int bytesum = 0;
        int byteread = 0;

        try {
            InputStream inStream = new FileInputStream(headPath);
            FileOutputStream fs = new FileOutputStream(path);
            byte[] buffer = new byte[20000000];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;            //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        InfoResponse r = userBlService.updateUser(openid,username,new Role(role),avatarUrl,phone,levelName,integration,balance,orders,carts,addresses,coupons);
        headPath = "";
        return r;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse deleteUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        InfoResponse r = userBlService.deleteUser(openid);
        return r;
    }

    @ApiOperation(value = "添加会员等级信息", notes = "添加会员等级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "discountedRatio", value = "该会员级别的折扣率，价格是原价的discountedRatio倍", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/addLevel", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse addLevel(@RequestParam(name = "name") String name, @RequestParam(name = "discountedRatio") String discountedRatio) throws NotExistException {
        return userBlService.addLevel(name, Double.parseDouble(discountedRatio));
    }

    @ApiOperation(value = "获取所有会员等级信息", notes = "获取所有会员等级信息")
    @RequestMapping(value = "/getLevelList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getLevelList() {
        return new ResponseEntity<>(userBlService.getLevelList(), HttpStatus.OK);
    }

    @ApiOperation(value = "更新会员等级信息", notes = "更新会员等级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "discountedRatio", value = "该会员级别的折扣率，价格是原价的discountedRatio倍", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/updateLevel", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse updateLevel(@RequestParam(name = "name") String name,@RequestParam(name = "discountedRatio") String discountedRatio) throws NotExistException {
        return userBlService.updateLevel(name,Double.parseDouble(discountedRatio));
    }

    @ApiOperation(value = "更新会员等级信息", notes = "更新会员等级信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteLevel", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse deleteLevel(@RequestParam(name = "name") String name) throws NotExistException {
        return userBlService.deleteLevel(name);
    }


    @ApiOperation(value = "小程序前端获取openid和session", notes = "小程序前端获取openid和session")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", value = "微信小程序的jsCode", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getOpenIdAndSessionKey", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getOpenIdAndSessionKey(@RequestParam(name = "jsCode") String jsCode) throws CannotGetOpenIdAndSessionKeyException {
        return new ResponseEntity<>(userBlService.getOpenIdAndSessionKey(jsCode), HttpStatus.OK);
    }


    @ApiOperation(value = "用户登录小程序", notes = "用户登录小程序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "faceWxUrl", value = "用户微信头像的URL", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loginMyUser", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> loginMyUser(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username, @RequestParam(name = "faceWxUrl") String faceWxUrl) throws NotExistException {
        return new ResponseEntity<>(userBlService.loginMyUser(openid, username, faceWxUrl), HttpStatus.OK);
    }

    @ApiOperation(value = "小程序前端获取微信小程序的二维码", notes = "小程序前端获取微信小程序的二维码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scene", value = "参数，对应微信API的scene", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "跳转的页面，对应微信API的page", required = true, dataType = "String"),
            @ApiImplicitParam(name = "width", value = "维码的宽度，对应微信API的width", required = true, dataType = "int"),
            @ApiImplicitParam(name = "autoColor", value = "是否自动配置线条颜色，对应微信API的auto_color", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "lineColorR", value = "当autoColor为false时有效，对应微信API的line_color中的r", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lineColorG", value = "当autoColor为false时有效，对应微信API的line_color中的g", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lineColorB", value = "当autoColor为false时有效，对应微信API的line_color中的b", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isHyaline", value = "是否需要透明底色，对应微信API的is_hyaline", required = true, dataType = "boolean")
    })
    @RequestMapping(value = "/getWxQrCode", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getWxQrCode(@RequestParam(name = "scene") String scene, @RequestParam(name = "page") String page, @RequestParam(name = "width") int width, @RequestParam(name = "autoColor") boolean autoColor, @RequestParam(name = "lineColorR") String lineColorR, @RequestParam(name = "lineColorG") String lineColorG, @RequestParam(name = "lineColorB") String lineColorB, @RequestParam(name = "isHyaline") boolean isHyaline) throws NotExistException {
        return new ResponseEntity<>(userBlService.getWxQrCode(scene, page, width, autoColor, lineColorR, lineColorG, lineColorB, isHyaline), HttpStatus.OK);
    }

    @ApiOperation(value = "用户获取自己的个人信息", notes = "用户获取自己的个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getMyUser", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> getMyUser(@RequestParam(name = "openid") String openid) throws NotExistException {
        return new ResponseEntity<>(userBlService.getMyUser(openid), HttpStatus.OK);
    }

    @ApiOperation(value = "用户修改自己的个人信息", notes = "用户修改自己的个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name="avatarUrl",value="用户头像",required = true,dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "用户手机", required = true, dataType = "String"),
            @ApiImplicitParam(name="addresses",value="地址",required = true,dataType = "ArrayList")
    })
    @RequestMapping(value = "/updateMyProfile", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateMyProfile(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username, @RequestParam("avatarUrl")String avatarUrl,@RequestParam("phone")String phone,@RequestParam("addresses")ArrayList<Address> addresses) throws NotExistException {
        File file = new File(headPath);
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String[] temp = headPath.split("\\.");
        String path = "";
        if (temp.length > 2) {
            path = "record/user/head/" + uuid + "." + temp[3];
        } else {
            path = "record/user/head/" + uuid + "." + temp[1];
        }
        File tempfile = new File(path);
        if (tempfile.exists() && tempfile.isFile()) {
            tempfile.delete();
        }
        int bytesum = 0;
        int byteread = 0;
        try {
            InputStream inStream = new FileInputStream(headPath);
            FileOutputStream fs = new FileOutputStream(path);
            byte[] buffer = new byte[20000000];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;            //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        headPath = "";
        return new ResponseEntity<>(userBlService.updateMyProfile(openid, username, path, phone,addresses), HttpStatus.OK);
    }

    @ApiOperation(value = "用户修改自己的个人信息", notes = "用户修改自己的个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "avatarUrl", value = "用户头像", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "用户手机", required = true, dataType = "String"),
            @ApiImplicitParam(name="addresses",value="地址",required = true,dataType="ArrayList")
    })
    @RequestMapping(value = "/updateMyProfileWithoutFile", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public ResponseEntity<Response> updateMyProfileWithoutFile(@RequestParam(name = "openid") String openid, @RequestParam(name = "username") String username, @RequestParam(name = "avatarUrl") String avatarUrl, @RequestParam(name = "phone") String phone,@RequestParam("addresses")ArrayList<Address> addresses)throws NotExistException {
        return new ResponseEntity<>(userBlService.updateMyProfile(openid, username, avatarUrl, phone, addresses), HttpStatus.OK);
    }
}
