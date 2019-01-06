package bath.response.order;

import bath.response.Response;

import java.util.List;

public class OrderLoadResponse extends Response {
    private List<OrderResponseItem> orders;

    public OrderLoadResponse() {
    }

    public OrderLoadResponse(List<OrderResponseItem> orders) {
        this.orders = orders;
    }

    public List<OrderResponseItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseItem> orders) {
        this.orders = orders;
    }
}
