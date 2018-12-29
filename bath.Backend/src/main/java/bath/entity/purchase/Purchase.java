package bath.entity.purchase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Purchase {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //订单编号

	@Column
	private String openid; //用户微信openid

	@Column
	private String type; //订单类型："course", "level", "enterprise"

	@Column
	private String detail; //订单详情：课程ID，会员等级，企业AdminId

	@Column
	private int price; //交易金额

	@Column
	private String date; //交易日期

	public Purchase() {
	}

	public Purchase(String openid, String type, String detail, int price, String date) {
		this.openid = openid;
		this.type = type;
		this.detail = detail;
		this.price = price;
		this.date = date;
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
