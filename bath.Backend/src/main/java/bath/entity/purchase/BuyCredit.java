package bath.entity.purchase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class BuyCredit {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id; //编号

	@Column
	private String openid; //用户微信openid

	@Column
	private int credit; //要充值的积分数

	@Column
	private int totalFee; //要支付的金额，单位为分，对应微信统一下单API的total_fee

	/** 支付状态
	 * "init": 收到前端小程序支付请求，已生成订单
	 * "stopped"：由于内部错误导致下单过程终止（在waiting前）
	 * "waiting"：已回复给前端，前端将弹出微信窗口让用户支付确认，等待后台到账（尚未收到微信后台的支付确认）
	 * "finished"：用户已经支付成功（已经收到微信后台的支付确认）
	 * "failed"：用户支付失败（微信后台消息为支付失败）
	 * "cancelled"：init和waiting状态超时，视为用户取消
	*/
	@Column
	private String status;

	@Column
	private String createTimeStamp; //创建时间戳

	@Column
	private String waitingTimeStamp; //回复给微信小程序的时间戳

	@Column
	private String finalTimeStamp; //最终时间戳，收到微信后台支付结果时的时间戳

	public BuyCredit() {
	}

	public BuyCredit(String openid, int credit, int totalFee) {
		this.openid = openid;
		this.credit = credit;
		this.totalFee = totalFee;
		this.status = "init";
		this.createTimeStamp = String.valueOf(System.currentTimeMillis());
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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(String createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public String getFinalTimeStamp() {
		return finalTimeStamp;
	}

	public void setFinalTimeStamp(String finalTimeStamp) {
		this.finalTimeStamp = finalTimeStamp;
	}

	public String getWaitingTimeStamp() {
		return waitingTimeStamp;
	}

	public void setWaitingTimeStamp(String waitingTimeStamp) {
		this.waitingTimeStamp = waitingTimeStamp;
	}
}
