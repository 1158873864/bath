package bath.entity.user;

import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.publicdatas.account.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @Column
    private String openid;//用户微信编号
    @Column(name="username")
    private String username; //用户名
    @Column(name = "role")
    private Role role;//角色
    @Column(name="avatarUrl")
    private String avatarUrl; //用户头像
    @Column(name="phone")
    private String phone; //电话号码
    @Column(name="level")
    private String level; //用户所属会员等级
    @Column(name="integration")
    private int integration;//积分
    @Column(name="balance")
    private double balance;//储值金余额
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Order> orders;//订单
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Groupon> carts;//购物车
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses;//地址
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Coupon> coupons;//优惠券

    public User() {
    }

    public User(String openid, String username, String avatarUrl) {
        this.openid = openid;
        this.username = username;
        this.role = new Role("ROLE_USER");
        this.avatarUrl = avatarUrl;
        this.phone = "";
        this.level = "common";
        this.integration = 0;
        this.balance = 0;
        this.orders = new ArrayList<>();
        this.carts = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.coupons = new ArrayList<>();

    }

    public User(String openid, String username, String avatarUrl, String phone) {
        this.openid = openid;
        this.username = username;
        this.role = new Role("ROLE_USER");
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.level = "common";
        this.integration = 0;
        this.balance = 0;
        this.orders = new ArrayList<>();
        this.carts = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.coupons = new ArrayList<>();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() { return level; }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
