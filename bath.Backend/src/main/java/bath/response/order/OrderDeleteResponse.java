package bath.response.order;

import bath.response.Response;

public class OrderDeleteResponse extends Response {
    private String info;

    public OrderDeleteResponse() {
        this.info = "success";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
