package bath.response.order;

import bath.response.Response;

public class OrderWaitingForPayingResponse extends Response {
    private String info;

    public OrderWaitingForPayingResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
