package bath.springcontroller.admin;


import bath.blservice.admin.AdminBlService;
import bath.exception.DuplicateUsernameException;
import bath.exception.NotExistException;
import bath.response.BoolResponse;
import bath.response.InfoResponse;
import bath.response.WrongResponse;
import bath.response.admin.AdminListResponse;
import bath.response.admin.AdminResponse;
import bath.response.event.EventLoadResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class adminController {
    private final AdminBlService adminBlService;

    @Autowired
    public adminController(AdminBlService adminBlService) {
        this.adminBlService = adminBlService;
    }

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "image", required = true, dataType = "MultipartFile")
    })
    @RequestMapping(value = "/uploadAdmin", method = RequestMethod.POST)
    @ResponseBody
    public String uploadAdmin(@RequestParam("image")MultipartFile image){
        Map<String,Object> map= new HashMap<String,Object>();
        if(image.isEmpty()){
            map.put( "result", "error");
            map.put( "msg", "上传文件不能为空" );
            return "上传文件不能为空";
        } else {

            // 获取文件名
            String fileName = image.getOriginalFilename();
            // 获取文件后缀

            // 用uuid作为文件名，防止生成的临时文件重复
            // MultipartFile to File
            //你的业务逻辑
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = null;    //读入原文件
            try {
                inStream = image.getInputStream();
                FileOutputStream fs = new FileOutputStream(fileName);
                byte[] buffer = new byte[200000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;            //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            File file = new File(fileName);
            String[] temp=fileName.split("\\.");
            String thePath="record/admin/"+uuid+"."+temp[1];
            String path="record/admin/"+uuid+"."+temp[1];
            File tempfile=new File(path);
            if (tempfile.exists() && tempfile.isFile()) {
                tempfile.delete();
            }
            bytesum = 0;
            byteread = 0;
            try {
                inStream =new FileInputStream(fileName);
                FileOutputStream fs = new FileOutputStream(path);
                byte[] buffer = new byte[20000000];
                while ( (byteread = inStream.read(buffer)) != -1) {
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
            return thePath;
        }
    }

    @ApiOperation(value = "检查用户名username是否已经存在", notes = "检查用户名username是否已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    })
    @RequestMapping(value = "/isAdminUsernameExistent", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public BoolResponse isAdminUsernameExistent(@RequestParam(name="username")String username) {
        BoolResponse info=adminBlService.isAdminUsernameExistent(username);
        return info;
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limits", value = "权限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse addAdmin(@RequestParam(name="username")String username, @RequestParam(name="password")String password, @RequestParam(name="limits")String limits, @RequestParam(name="date")String date, @RequestParam(name="avatarUrl")String avatarUrl) throws DuplicateUsernameException {
        InfoResponse info=adminBlService.addAdmin(username, password, limits, date,avatarUrl);
        return info;
    }

    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表")
    @RequestMapping(value = "/getAdminList", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public AdminListResponse getAdminList(HttpServletRequest httpServletRequest, HttpServletResponse response){
        System.out.println("admin:"+httpServletRequest.getHeader("Authorization"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        AdminListResponse r=adminBlService.getAdminList();
        return r;
    }

    @ApiOperation(value = "获取管理员", notes = "获取管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAdmin", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public AdminResponse getAdmin(@RequestParam(name="id")String id) throws NotExistException {
        AdminResponse r=adminBlService.getAdmin(id);
        return r;
    }

    @ApiOperation(value = "获取管理员", notes = "获取管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "管理员编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/getAdminByUsername", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public AdminResponse getAdminByUsername(@RequestParam(name="username")String username) throws NotExistException {
        AdminResponse r=adminBlService.getAdminByUsername(username);
        return r;
    }

    @ApiOperation(value = "修改管理员", notes = "修改管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limits", value = "权限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public InfoResponse updateAdmin(@RequestParam(name="id")String id,@RequestParam(name="username")String username, @RequestParam(name="password")String password, @RequestParam(name="limits")String limits, @RequestParam(name="date")String date,@RequestParam(name="avatarUrl")String avatarUrl) throws NotExistException {
        InfoResponse info=adminBlService.updateAdmin(id,username, password, limits, date,avatarUrl);
        return info;
    }

    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员编号", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public BoolResponse deleteAdmin(@RequestParam(name="id")String id) throws NotExistException {
        BoolResponse r=adminBlService.deleteAdmin(id);
        return r;
    }

    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "管理员用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "管理员密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = EventLoadResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = WrongResponse.class),
            @ApiResponse(code = 500, message = "Failure", response = WrongResponse.class)})
    @ResponseBody
    public String loginAdmin(@RequestParam(name="username")String username,@RequestParam(name="password")String password) {
          return adminBlService.loginAdmin(username,password);
    }




}

