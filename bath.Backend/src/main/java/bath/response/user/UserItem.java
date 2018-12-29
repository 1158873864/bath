package bath.response.user;

import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.user.User;
import bath.publicdatas.account.Role;
import java.util.List;

public class UserItem {
	private String openid;//用户微信编号
	private String username; //用户名
	private String avatarUrl;//头像
	private Role role;
	private String phone;
	private String levelName;
	private int integration;
	private double balance;
	private List<Groupon> orders;
	private List<Groupon> carts;
	private List<Address> addresses;
	private List<Coupon> coupons;

	public UserItem(User user){
		this.openid=user.getOpenid();
		this.username=user.getUsername();
		this.avatarUrl=user.getAvatarUrl();
		this.role=user.getRole();
		this.phone=user.getPhone();
		this.levelName=user.getLevelName();
		this.integration=user.getIntegration();
		this.balance=user.getBalance();
		this.orders=user.getCarts();
		this.carts=user.getCarts();
		this.addresses=user.getAddresses();
		this.coupons=user.getCoupons();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getIntegration() {
		return integration;
	}

	public void setIntegration(int integration) {
		this.integration = integration;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Groupon> getOrders() {
		return orders;
	}

	public void setOrders(List<Groupon> orders) {
		this.orders = orders;
	}

	public List<Groupon> getCarts() {
		return carts;
	}

	public void setCarts(List<Groupon> carts) {
		this.carts = carts;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
}
