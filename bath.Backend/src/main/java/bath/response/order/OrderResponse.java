package bath.response.order;

import bath.entity.order.Order;
import bath.entity.order.OrderItem;
import bath.publicdatas.order.OrderState;
import bath.response.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderResponse extends Response {
    private String id;
    private double total;
    private double integration;
    private double coupon;
    private double actualCost;
    private Date date;
    private List<OrderResponseItem> orderItems;
    private OrderState orderState;
    private String timeStamp;
    private String nonceStr;
    private String signType;
    private String paySign;

    public OrderResponse() {
    }

    public OrderResponse(Order order){
        this.id=order.getId();
        this.total=order.getTotal();
        this.integration=order.getIntegration();
        this.coupon=order.getCoupon();
        this.actualCost=order.getActualCost();
        this.date=order.getDate();
        this.orderState=order.getOrderState();
        this.timeStamp=order.getTimeStamp();
        this.nonceStr=order.getNonceStr();
        this.signType=order.getSignType();
        this.paySign=order.getPaySign();
        List<OrderResponseItem> items=new ArrayList<>();
        for(OrderItem temp:order.getOrderItems()){
            items.add(new OrderResponseItem(temp));
        }
        this.orderItems=items;
    }

    public OrderResponse(String id, double total, double integration, double coupon, double actualCost, Date date, List<OrderResponseItem> orderItems, OrderState orderState, String timeStamp, String nonceStr, String signType, String paySign) {
        this.id = id;
        this.total = total;
        this.integration = integration;
        this.coupon = coupon;
        this.actualCost = actualCost;
        this.date = date;
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.signType = signType;
        this.paySign = paySign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderResponseItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderResponseItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
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
}
