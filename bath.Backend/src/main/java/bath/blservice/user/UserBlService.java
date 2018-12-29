package bath.blservice.user;

import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.exception.CannotGetOpenIdAndSessionKeyException;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import bath.response.InfoResponse;
import bath.response.account.OpenIdAndSessionKeyResponse;
import bath.response.user.*;

import java.util.List;

public interface UserBlService {


	/*------------ 管理员使用的API ------------*/


	/**
	 * 添加用户(Admin)
	 * @param openid 微信openid
	 * @param username 用户名
	 * @param avatarUrl 头像url
	 * @param  phone 电话号码
	 * @param addresses 地址
	 * @return 是否成功
	 */
	InfoResponse addUser(String openid, String username, String avatarUrl, String phone, List<Address> addresses) throws NotExistException;

	/**
	 * 根据微信openid获取用户信息(Admin)
	 * @param openid 微信的openid
	 * @return 用户信息
	 */
	UserResponse getUser(String openid) throws NotExistException;

	/**
	 * 获取用户列表(Admin)
	 * @return 用户列表
	 */
	UserListResponse getUserList();

	/**
	 * 根据微信openid更新用户信息(Admin)
	 * @param openid 微信openid
	 * @param username 用户名
	 * @param role
	 * @param avatarUrl
	 * @param phone
	 * @param levelName
	 * @param integration
	 * @param balance
	 * @param orders
	 * @param carts
	 * @param addresses
	 * @param coupons
	 * @return 是否成功
	 */
	InfoResponse updateUser(String openid,String username,Role role,String avatarUrl,String phone,String levelName,int integration,double balance,List<Order> orders,List<Groupon> carts,List<Address> addresses,List<Coupon> coupons)throws NotExistException;
	/**
	 * 根据微信openid删除用户(Admin)
	 * @param openid 微信openid
	 * @return 是否成功
	 */
	InfoResponse deleteUser(String openid) throws NotExistException;

	/**
	 * 添加会员等级信息(Admin)
	 * @param name 会员等级名
	 * @param discountedRatio 该会员级别的折扣率，价格是原价的discountedRatio倍
	 * @return 是否成功
	 */
	InfoResponse addLevel(String name, double discountedRatio);

	/**
	 * 获取所有会员等级信息(User&Admin)
	 * @return 会员等级信息
	 */
	LevelListResponse getLevelList();

	/**
	 * 修改某会员等级的信息(Admin)
	 * @param name 会员等级名称
	 * @param discountedRatio 该会员级别购买课程时的折扣率，价格是原价的courseDiscountedRatio倍
	 * @return 是否成功
	 */
	InfoResponse updateLevel(String name, double discountedRatio) throws NotExistException;

	/**
	 * 删除某会员等级(Admin)
	 * @param name 要被删除的会员等级名称
	 * @return 是否成功
	 */
	InfoResponse deleteLevel(String name) throws NotExistException;


	/*------------ 用户使用的API ------------*/


	/**
	 * 用户登录小程序，若账号不存在则新建一个（初始用户名与头像都与微信一致），否则不处理(User)
	 * @param openid 用户的微信openid
	 * @param username 初始用户名
	 * @param faceWxUrl 用户微信头像的URL
	 * @return 用户个人信息与token
	 */
	UserLoginResponse loginMyUser(String openid, String username, String faceWxUrl) throws NotExistException;

	/**
	 * 小程序前端获取openid和session
	 * @param jsCode 微信小程序的jsCode
	 * @return openid和session信息
	 */
	OpenIdAndSessionKeyResponse getOpenIdAndSessionKey(String jsCode) throws CannotGetOpenIdAndSessionKeyException;

	/**
	 * 小程序前端获取微信小程序的二维码
	 * @param scene 参数，对应微信API的scene
	 * @param page 跳转的页面，对应微信API的page
	 * @param width 二维码的宽度，对应微信API的width
	 * @param autoColor 是否自动配置线条颜色，对应微信API的auto_color
	 * @param lineColorR 当autoColor为false时有效，对应微信API的line_color中的r
	 * @param lineColorG 当autoColor为false时有效，对应微信API的line_color中的g
	 * @param lineColorB 当autoColor为false时有效，对应微信API的line_color中的b
	 * @param isHyaline 是否需要透明底色，对应微信API的is_hyaline
	 * @return Base64编码的图片
	 */
	QrCodeResponse getWxQrCode(String scene, String page, int width, boolean autoColor, String lineColorR, String lineColorG, String lineColorB, boolean isHyaline);

	/**
	 * 用户获取自己的个人信息(User)
	 * @param openid 用户的微信openid
	 * @return 用户个人信息
	 */
	UserResponse getMyUser(String openid) throws NotExistException;

	/**
	 * 用户修改自己的个人信息，只能改部分属性(User)
	 * @param openid 用户微信openid
	 * @param username 用户名
	 * @param avatarUrl 头像url
	 * @param  phone 电话
	 * @param addresses 地址
	 * @return 是否成功
	 */
	InfoResponse updateMyProfile(String openid, String username, String avatarUrl, String phone, List<Address> addresses) throws NotExistException;





}
