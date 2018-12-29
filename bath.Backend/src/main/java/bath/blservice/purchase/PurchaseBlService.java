package bath.blservice.purchase;

import bath.exception.NotExistException;
import bath.response.BoolResponse;
import bath.response.InfoResponse;
import bath.response.purchase.PurchaseListResponse;
import bath.response.purchase.PurchaseResponse;
import bath.response.purchase.WxBuyCreditResponse;

import javax.servlet.http.HttpServletRequest;

public interface PurchaseBlService {

	/**
	 * 用户通过微信支付购买积分
	 * @param openid 用户的openid
	 * @param credit 用户要购买的积分数
	 * @return 微信支付相关数据
	 */
	WxBuyCreditResponse buyMyCredit(String openid, int credit);

	/**
	 * 此接口用户接收微信支付后台的支付结果通知
	 * @param httpServletRequest 与微信支付的连接
	 * @return 返回给微信支付的参数
	 */
	String getWxPayResult(HttpServletRequest httpServletRequest);

	/**
	 * 用户下订单(User)
	 * @param openid 用户微信openid
	 * @param type 订单类型："course", "level"
	 * @param detail 订单详情：课程ID，会员等级名称
	 * @param price 交易金额
	 * @param date 交易日期
	 * @return 是否成功
	 */
	BoolResponse addPurchase(String openid, String type, String detail, int price, String date);

	/**
	 * 根据订单号获取单个订单信息(User&Admin)
	 * @param id 订单ID
	 * @return 订单信息
	 */
	PurchaseResponse getPurchase(String id) throws NotExistException;

	/**
	 * 用户获取自己的订单列表(User&Admin)
	 * @param openid 用户微信openid
	 * @return 订单列表
	 */
	PurchaseListResponse getMyPurchaseList(String openid);

	/**
	 * 获取所有订单信息(Admin)
	 * @return 订单信息列表
	 */
	PurchaseListResponse getPurchaseList();

	/**
	 * 根据订单ID删除订单信息(Admin)
	 * @param id 订单ID
	 * @return 是否成功
	 */
	InfoResponse deletePurchase(String id) throws NotExistException;
}
