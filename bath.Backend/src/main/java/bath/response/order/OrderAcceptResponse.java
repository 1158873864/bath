package bath.response.order;


import bath.response.Response;

public class OrderAcceptResponse extends Response {
    private String info;

    public OrderAcceptResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
