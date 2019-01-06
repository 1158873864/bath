package bath.response.order;

import bath.response.Response;

public class OrderPrintResponse extends Response {
    private String info;

    public OrderPrintResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
