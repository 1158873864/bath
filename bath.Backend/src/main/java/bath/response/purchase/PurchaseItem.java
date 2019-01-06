package bath.response.purchase;


import bath.entity.purchase.Purchase;

public class PurchaseItem {
	private String id; //订单编号
	private String openid; //用户微信openid
	private String type; //订单类型："course", "level"
	private String detail; //订单详情：课程ID，会员等级
	private int price; //交易金额
	private String date; //交易日期

	public PurchaseItem(Purchase purchase) {
		this.id = purchase.getId();
		this.openid = purchase.getOpenid();
		this.type = purchase.getType();
		this.detail = purchase.getDetail();
		this.price = purchase.getPrice();
		this.date = purchase.getDate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
