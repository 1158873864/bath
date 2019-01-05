package bath.bl.user;

import bath.blservice.user.UserBlService;
import bath.dataservice.user.LevelDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.Level;
import bath.entity.user.User;
import bath.exception.CannotGetOpenIdAndSessionKeyException;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import bath.response.InfoResponse;
import bath.response.account.OpenIdAndSessionKeyResponse;
import bath.response.user.*;
import bath.security.jwt.JwtService;
import bath.security.jwt.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.*;

@Service
public class UserBlServiceImpl implements UserBlService {
	private final UserDataService userDataService;
	private final LevelDataService levelDataService;
	private final JwtUserDetailsService jwtUserDetailsService;
	private final JwtService jwtService;

	@Autowired
	public UserBlServiceImpl(UserDataService userDataService,LevelDataService levelDataService, JwtUserDetailsService jwtUserDetailsService, JwtService jwtService) {
		this.userDataService = userDataService;
		this.levelDataService = levelDataService;
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtService = jwtService;
	}

	@Override
	public InfoResponse addUser(String openid, String username, String avatarUrl, String phone) throws NotExistException {
		userDataService.addUser(new User(openid, username, avatarUrl, phone));
		return new InfoResponse();
	}

	@Override
	public UserResponse getUser(String openid) throws NotExistException {
		return new UserResponse(new UserItem(userDataService.getUserByOpenid(openid)));
	}

	@Override
	public UserListResponse getUserList() {
		List<User> userList = userDataService.getAllUsers();
		List<UserItem> userItemList = new ArrayList<>();
		for(User user:userList) {
			userItemList.add(new UserItem(user));
		}
		return new UserListResponse(userItemList);
	}

	@Override
	public InfoResponse updateUser(String openid, String username, Role role, String avatarUrl, String phone, String levelname, int integration, double balance, List<Order> orders, List<Groupon> carts, List<Address> addresses, List<Coupon> coupons) throws NotExistException {
		User user = userDataService.getUserByOpenid(openid);
		userDataService.updateUserByOpenid(openid,username,role,avatarUrl,phone,levelname,integration,balance,orders,carts,addresses,coupons);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteUser(String openid) throws NotExistException {
		userDataService.deleteUserByOpenid(openid);
		return new InfoResponse();
	}

	private static final String []colorPool = {"rgba(255, 161, 177, 0.699)",
			"rgba(138, 138, 252, 0.767)",
			"rgba(109, 156, 90, 0.726)",
			"rgba(255, 58, 58, 0.678)"}; //业务标签的颜色值


	@Override
	public InfoResponse addLevel(String name, double discountedRatio) {
		levelDataService.addLevel(new Level(name,discountedRatio));
		return new InfoResponse();
	}

	@Override
	public LevelListResponse getLevelList() {
		List<Level> levels = levelDataService.getAllLevels();
		List<LevelItem> levelItems = new ArrayList<>();
		for(Level level:levels) {
			levelItems.add(new LevelItem(level));
		}
		return new LevelListResponse(levelItems);
	}

	@Override
	public InfoResponse updateLevel(String name, double discountedRatio) throws NotExistException {
		levelDataService.updateLevelByName(name, discountedRatio);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteLevel(String name) throws NotExistException {
		levelDataService.deleteLevelByName(name);
		return new InfoResponse();
	}

	private final static long EXPIRATION = 604800;

	@Override
	public UserLoginResponse loginMyUser(String openid, String username, String faceWxUrl) throws NotExistException {
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException exception) {
			//设置初始头像为微信头像
			String faceLocalUrl = "record/user/head/"+UUID.randomUUID();
			try {
				URL url = new URL(faceWxUrl);
				DataInputStream dataInputStream = new DataInputStream(url.openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(new File(faceLocalUrl));
				ByteArrayOutputStream output = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];
				int length;

				while ((length = dataInputStream.read(buffer)) > 0) {
					output.write(buffer, 0, length);
				}
				fileOutputStream.write(output.toByteArray());
				dataInputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			user = new User(openid,username,faceLocalUrl);
			userDataService.addUser(user);
		}


		return new UserLoginResponse( new UserItem(user));
	}

	@Value(value = "${wechat.url}")
	private String wechatUrl;

	@Value(value = "${wechat.id}")
	private String appId;

	@Value(value = "${wechat.secret}")
	private String appSecret;

	@Override
	public OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException {
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		ResponseEntity<String> response = client.exchange(wechatUrl + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code", HttpMethod.GET, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("jsCode = [" + jsCode + "]");
			System.out.println("hhhhhhh" + (String) JSONObject.fromObject(response.getBody()).get("openid"));
			System.out.println(response);
			String openid=(String) JSONObject.fromObject(response.getBody()).get("openid");
//            User user=null;
//			try {
//				user = userDataService.getUserByOpenid(openid);
//			} catch (NotExistException e) {
//				e.printStackTrace();
//			}

			//JwtUser jwtUser = (JwtUser) jwtUserDetailsService.loadUserByUsername(openid);
			String token="";
			//token = jwtService.generateToken(jwtUser, EXPIRATION);

			return new OpenIdAndSessionKeyResponse(openid, (String) JSONObject.fromObject(response.getBody()).get("session_key"),token);
		} else {
			throw new CannotGetOpenIdAndSessionKeyException();
		}
	}

	@Override
	public QrCodeResponse getWxQrCode(String scene, String page, int width, boolean autoColor, String lineColorR, String lineColorG, String lineColorB, boolean isHyaline) {
		RestTemplate client = new RestTemplate();

		//获取accessToken
		String accessToken = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>("", headers);
		ResponseEntity<String> response = client.exchange(
				"https://api.weixin.qq.com/cgi-bin/token?" + "&grant_type=client_credential&appid="+ appId + "&secret=" + appSecret, HttpMethod.GET, entity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			accessToken = (String)JSONObject.fromObject(response.getBody()).get("access_token");
		} else {
			System.err.println(response);
			return new QrCodeResponse(false, "access_token获取失败("+response+")", "");
		}

		//根据accessToken获取二维码图片
		String wxQrCodeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
		Map<String,Object> wxQrCodeParams = new HashMap<>();
		wxQrCodeParams.put("scene", scene);
		wxQrCodeParams.put("page", page);
		wxQrCodeParams.put("width", width);
		wxQrCodeParams.put("auto_color", autoColor);
		Map<String,Object> lineColor = new HashMap<>();
		lineColor.put("r", lineColorR);
		lineColor.put("g", lineColorG);
		lineColor.put("b", lineColorB);
		wxQrCodeParams.put("line_color", lineColor);
		wxQrCodeParams.put("is_hyaline", isHyaline);
		MultiValueMap<String, String> wxQrCodeHeaders = new LinkedMultiValueMap<>();
		HttpEntity wxQrCodeRequest = new HttpEntity(wxQrCodeParams, wxQrCodeHeaders);
		ResponseEntity<byte[]> wxQrCodeResponse = client.exchange(wxQrCodeUrl, HttpMethod.POST, wxQrCodeRequest, byte[].class);
		if (wxQrCodeResponse.getStatusCode() == HttpStatus.OK) {
			byte[] image = wxQrCodeResponse.getBody();
			final String dirPath = "record/user/qrcode/";
			File dirFile = new File(dirPath);
			if (!dirFile.exists() && !dirFile.mkdirs()) {
				return new QrCodeResponse(false, "二维码存储目录创建失败", "");
			}
			String imagePath = null;
			try {
				imagePath = dirPath+UUID.randomUUID();
				File imageFile = new File(imagePath);
				while (!imageFile.createNewFile()) { //若文件已存在，则换个名字
					imagePath = dirPath+UUID.randomUUID();
					imageFile = new File(imagePath);
				}
				InputStream inputStream = new ByteArrayInputStream(image);
				OutputStream outputStream = new FileOutputStream(imageFile);
				int len = 0;
				byte[] buf = new byte[1024];
				while ((len=inputStream.read(buf, 0, 1024)) != -1) {
					outputStream.write(buf, 0, len);
				}
				outputStream.flush();

				//1分钟后删除此图片
				final File finalImageFile = new File(imagePath);
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						if (!finalImageFile.delete()) {
							System.err.println(finalImageFile.getName()+"文件删除失败");
						}
					}
				}, 60 * 1000);

				return new QrCodeResponse(true, "ok", imagePath);
			} catch (IOException e) {
				System.err.println("二维码图片保存时出现错误！");
				e.printStackTrace();
				return new QrCodeResponse(false, "二维码保存失败", "");
			}
		} else {
			System.err.println(wxQrCodeResponse);
			return new QrCodeResponse(false, "二维码获取失败", "");
		}
	}

	@Override
	public UserResponse getMyUser(String openid) throws NotExistException {
		return new UserResponse(new UserItem(userDataService.getUserByOpenid(openid)));
	}

	@Override
	public InfoResponse updateMyProfile(String openid, String username, String avatarUrl, String phone)throws NotExistException {
		User user = userDataService.getUserByOpenid(openid);
		userDataService.updateUserByOpenid(openid, username,user.getRole(), avatarUrl, phone,user.getLevel(),user.getIntegration(),user.getBalance(),user.getOrders(),user.getCarts(), user.getAddresses(),user.getCoupons());
		return new InfoResponse();
	}


	@Override
	public InfoResponse addAddress(String openid,String receiver, String phone, String zone, String detailAddress, String postcode) throws NotExistException{
		User user=userDataService.getUserByOpenid(openid);
		Address address=new Address(receiver,phone,zone,detailAddress,postcode);
		List<Address> addresses=user.getAddresses();
		if(addresses==null || addresses.size()<1)
			addresses=new ArrayList<>();
		addresses.add(address);
		user.setAddresses(addresses);
		userDataService.saveUser(user);
		//addressDataService.addAddress(address);
		return new InfoResponse();
	}

	@Override
	public InfoResponse deleteAddress(String openid, int addressId) throws NotExistException {
		User user=userDataService.getUserByOpenid(openid);
		List<Address> addresses=user.getAddresses();
		if(addresses==null || addresses.size()<1)
			return new InfoResponse("Fail");
		for(Address temp:addresses){
			if(temp.getId()==addressId){
				addresses.remove(temp);
				break;
			}
		}
		user.setAddresses(addresses);
		userDataService.saveUser(user);
		return new InfoResponse();
	}

	@Override
	public InfoResponse updateAddress(String openid, int addressId, String receiver, String phone, String zone, String detailAddress, String postcode) throws NotExistException {
		User user=userDataService.getUserByOpenid(openid);
		List<Address> addresses=user.getAddresses();
		if(addresses==null || addresses.size()<1)
			throw new NotExistException("地址id",addressId+"");
		boolean isExist=false;
		for(Address temp:addresses){
			if(temp.getId()==addressId){
				isExist=true;
				addresses.remove(temp);
				temp.setReceiver(receiver);
				temp.setDetailAddress(detailAddress);
				temp.setPhone(phone);
				temp.setPostcode(postcode);
				temp.setZone(zone);
				addresses.add(temp);
				break;
			}
		}
		if(isExist==false)
			throw new NotExistException("地址id",addressId+"");
		user.setAddresses(addresses);
		userDataService.saveUser(user);
		return new InfoResponse();
	}


}
