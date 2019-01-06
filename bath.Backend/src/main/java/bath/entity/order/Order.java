package bath.entity.order;

import bath.publicdatas.order.OrderState;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bathOrder")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    //订单总价
    @Column(name = "total")
    private double total;

    //积分优惠
    @Column(name="integration")
    private double integration;

    //抵用券/优惠券 抵用金额
    @Column(name="coupon")
    private double coupon;

    //实际付款
    @Column(name="actualCost")
    private double actualCost;

    @Column(name = "date")
    private Date date;

    @Column(name = "orderItems")
    @ElementCollection(targetClass = OrderItem.class)
    private List<OrderItem> orderItems;

    @Column(name = "orderState")
    private OrderState orderState;

    @Column(name = "timeStamp")
    private String timeStamp;

    @Column(name = "nonceStr")
    private String nonceStr;

    @Column(name = "signType")
    private String signType;

    @Column(name = "paySign")
    private String paySign;

    public Order() {
    }

    public Order(String id,double total,double integration,double coupon,double avctualCost,Date date,List<OrderItem> orderItems, OrderState orderState, String timeStamp, String nonceStr, String signType, String paySign) {
        this.id = id;
        this.total=total;
        this.integration=integration;
        this.coupon=coupon;
        this.actualCost=avctualCost;
        this.date=date;
        this.orderItems=orderItems;
        this.orderState = orderState;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.signType = signType;
        this.paySign = paySign;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getIntegration() {
        return integration;
    }

    public void setIntegration(double integration) {
        this.integration = integration;
    }

    public double getCoupon() {
        return coupon;
    }

    public void setCoupon(double coupon) {
        this.coupon = coupon;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
