package bath.response.order;


import bath.response.Response;

public class OrderConfirmResponse extends Response {
    private String info;

    public OrderConfirmResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
