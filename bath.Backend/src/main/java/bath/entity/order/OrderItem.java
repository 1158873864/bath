package bath.entity.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {
    private String grouponId;
    private int amount;
    private double price;

    public OrderItem() {
    }
    public OrderItem(String grouponId,int amount,double price){
        this.grouponId=grouponId;
        this.amount=amount;
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(String grouponId) {
        this.grouponId = grouponId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
