package bath.response.order;


import bath.response.Response;

public class OrderConfirmArriveResponse extends Response {
    private String info;

    public OrderConfirmArriveResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}