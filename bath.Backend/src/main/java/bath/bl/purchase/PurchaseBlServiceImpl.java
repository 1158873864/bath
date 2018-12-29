package bath.bl.purchase;

import bath.blservice.purchase.PurchaseBlService;
import bath.dataservice.purchase.BuyCreditDataService;
import bath.dataservice.purchase.PurchaseCourseDataService;
import bath.dataservice.purchase.PurchaseDataService;
import bath.dataservice.user.LevelDataService;
import bath.dataservice.user.UserDataService;
import bath.entity.purchase.BuyCredit;
import bath.entity.purchase.Purchase;
import bath.entity.purchase.PurchaseCourse;
import bath.entity.user.User;
import bath.exception.AlreadyExistException;
import bath.exception.NotExistException;
import bath.exception.SystemException;
import bath.response.BoolResponse;
import bath.response.InfoResponse;
import bath.response.purchase.*;
import bath.util.HttpUtil;
import bath.util.PayCommonUtil;
import bath.util.RandomUtil;
import bath.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class PurchaseBlServiceImpl implements PurchaseBlService {
	private final PurchaseDataService purchaseDataService;
	private final UserDataService userDataService;
	private final LevelDataService levelDataService;
	private final PurchaseCourseDataService purchaseCourseDataService;
	private final BuyCreditDataService buyCreditDataService;

	@Autowired
	public PurchaseBlServiceImpl(PurchaseDataService purchaseDataService, UserDataService userDataService, LevelDataService levelDataService, PurchaseCourseDataService purchaseCourseDataService, BuyCreditDataService buyCreditDataService) {
		this.purchaseDataService = purchaseDataService;
		this.userDataService = userDataService;
		this.levelDataService = levelDataService;
		this.purchaseCourseDataService = purchaseCourseDataService;
		this.buyCreditDataService = buyCreditDataService;
	}

	@Value(value = "${wechat.order_url}")
	private String ORDER_URL;
	@Value(value = "${wechat.id}")
	private String APP_ID;
	@Value(value = "${wechat.mch_id}")
	private String MCH_ID;
	@Value(value = "${wechat.body}")
	private String BODY;
	@Value(value = "${wechat.device_info}")
	private String DEVICE_INFO;
	@Value(value = "${wechat.notify_url}")
	private String NOTIFY_URL;
	@Value(value = "${wechat.trade_type}")
	private String TRADE_TYPE;
	@Value(value = "${wechat.api_key}")
	private String API_KEY;
	@Value(value = "${wechat.sign_type}")
	private String SIGN_TYPE;

	@Override
	public WxBuyCreditResponse buyMyCredit(String openid, int credit) {
		BuyCredit buyCredit = new BuyCredit(openid, credit, credit*100); //1个积分为1元（100分）
		buyCreditDataService.addBuyCredit(buyCredit);

		SortedMap<String, String> packageParams = new TreeMap<>();
		packageParams.put("appid", APP_ID);
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("nonce_str", RandomUtil.generateNonceStr());//时间戳
		packageParams.put("body", BODY);//支付主体
		packageParams.put("out_trade_no", buyCredit.getId() + "");//BuyCredit表编号
		packageParams.put("total_fee", buyCredit.getTotalFee() + "");//人民币价格
		packageParams.put("notify_url", NOTIFY_URL);//支付返回地址，服务器收到之后将订单状态从"waiting"改为"finished"或"failed"
		packageParams.put("trade_type", TRADE_TYPE);//这个api有，固定的
		packageParams.put("openid", openid);//openid
		//获取sign
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);//最后这个是自己设置的32位密钥
		packageParams.put("sign", sign);

		//发送请求，得到含有prepay_id的XML
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		String resXml = null;
		try {
			resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
		} catch (SystemException e) {
			buyCredit.setStatus("stopped");
			buyCreditDataService.saveBuyCredit(buyCredit);
			e.printStackTrace();
		}

		//根据微信回复填写给小程序的回复
		String waitingTimeStamp = String.valueOf(System.currentTimeMillis()); //回复给微信小程序的时间戳
		String nonceStr = null;
		try {
			nonceStr = XMLUtil.parserXmlToGetNonceStr(resXml);
		} catch (SystemException e) {
			buyCredit.setStatus("stopped");
			buyCreditDataService.saveBuyCredit(buyCredit);
			e.printStackTrace();
		}
		String packageContent = null;
		try {
			packageContent = "prepay_id=" + XMLUtil.parserXmlToGetPrepayId(resXml);
		} catch (SystemException e) {
			buyCredit.setStatus("stopped");
			buyCreditDataService.saveBuyCredit(buyCredit);
			e.printStackTrace();
		}
		String signType = SIGN_TYPE;
		String apiKey = API_KEY;
		SortedMap<String, String> sortedMap = new TreeMap<>();
		sortedMap.put("appId", APP_ID);
		sortedMap.put("timeStamp", waitingTimeStamp);
		sortedMap.put("nonceStr", nonceStr);
		sortedMap.put("package", packageContent);
		sortedMap.put("signType", signType);
		String paySign = PayCommonUtil.createSign("UTF-8", sortedMap, apiKey);

		//根据数据库中buyCredit的记录
		buyCredit.setStatus("waiting");
		buyCredit.setWaitingTimeStamp(waitingTimeStamp);
		buyCreditDataService.saveBuyCredit(buyCredit);

		//自动取消超时订单
		System.out.println("start waiting...");
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("scheduled to checking");
				if (buyCredit.getStatus().equals("init") || buyCredit.getStatus().equals("waiting")) {
					buyCredit.setStatus("cancelled");
					buyCredit.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
					buyCreditDataService.saveBuyCredit(buyCredit);
				}
			}
		}, 15*60*1000); //在15分钟后订单若仍为init或waiting则自动取消

		return new WxBuyCreditResponse(new WxBuyCreditItem(buyCredit.getId(), waitingTimeStamp, nonceStr, packageContent, signType, paySign));
	}

	@Override
	public String getWxPayResult(HttpServletRequest request) {
		System.out.println("Wx notification arrived");
		try {
			InputStream inStream = request.getInputStream();
			int _buffer_size = 1024;
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[_buffer_size];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();

				String resultXML = new String(outStream.toByteArray(), StandardCharsets.UTF_8); //将流转换成字符串
				SortedMap<Object, Object> sortedMap = XMLUtil.getSortedMapFromXML(resultXML);
				if (PayCommonUtil.isTenpaySign("UTF-8", sortedMap, API_KEY)) {
					BuyCredit buyCredit = buyCreditDataService.getBuyCreditById((String)sortedMap.get("out_trade_no"));
					if (sortedMap.get("return_code").equals("SUCCESS")) {
						if (sortedMap.get("result_code").equals("SUCCESS")) {
							if (buyCredit.getStatus().equals("waiting")) {
								buyCredit.setStatus("finished");
								buyCredit.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
								buyCreditDataService.saveBuyCredit(buyCredit);
								User user = userDataService.getUserByOpenid(buyCredit.getOpenid());
								//user.setCredit(user.getCredit()+buyCredit.getCredit());
								userDataService.saveUser(user);
							} else {
								System.err.println("订单状态已更新为finished！");
							}
						} else {
							if (buyCredit.getStatus().equals("waiting")) {
								buyCredit.setStatus("failed");
								buyCredit.setFinalTimeStamp(String.valueOf(System.currentTimeMillis()));
								buyCreditDataService.saveBuyCredit(buyCredit);
							} else {
								System.err.println("订单状态已更新为failed！");
							}
						}
					} else {
						throw new Exception("微信支付后台通信标识为FAIL！");
					}
				} else {
					throw new Exception("微信支付后台通信签名校验失败！");
				}
			}
			//通知微信支付系统接收到信息
			return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//如果失败返回错误，微信会再次发送支付信息
			return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>";
		}
	}

	@Override
	public BoolResponse addPurchase(String openid, String type, String detail, int price, String date) {
		User user = null;
		try {
			user = userDataService.getUserByOpenid(openid);
		} catch (NotExistException e) {
			return new BoolResponse(false, e.getMessage());
		}
		if (true){//(user.getCredit()>=price) {
			switch (type) {
				case "level":
					userDataService.saveUser(user);
					break;
				case "course":
					try {
						purchaseCourseDataService.addPurchaseCourse(new PurchaseCourse(openid, detail));
					} catch (AlreadyExistException e) {
						return new BoolResponse(false, e.getMessage());
					}
					break;
				default:
					return new BoolResponse(false, "购买类型不存在");
			}
			//user.setCredit(user.getCredit()-price); //付款
			purchaseDataService.addPurchase(new Purchase(openid, type, detail, price, date)); //记录订单
			return new BoolResponse(true, "购买成功");
		} else {
			return new BoolResponse(false, "账户余额不足");
		}
	}

	@Override
	public PurchaseResponse getPurchase(String id) throws NotExistException {
		return new PurchaseResponse(new PurchaseItem(purchaseDataService.getPurchaseById(id)));
	}

	@Override
	public PurchaseListResponse getMyPurchaseList(String openid) {
		List<Purchase> purchases = purchaseDataService.getPurchasesByOpenid(openid);
		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (Purchase purchase:purchases) {
			purchaseItems.add(new PurchaseItem(purchase));
		}
		return new PurchaseListResponse(purchaseItems);
	}

	@Override
	public PurchaseListResponse getPurchaseList() {
		List<Purchase> purchases = purchaseDataService.getAllPurchases();
		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (Purchase purchase:purchases) {
			purchaseItems.add(new PurchaseItem(purchase));
		}
		return new PurchaseListResponse(purchaseItems);
	}

	@Override
	public InfoResponse deletePurchase(String id) throws NotExistException {
		purchaseDataService.deletePurchaseById(id);
		return new InfoResponse();
	}
}
