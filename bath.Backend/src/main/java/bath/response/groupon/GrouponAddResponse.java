package bath.response.groupon;

import bath.response.Response;

public class GrouponAddResponse extends Response {
    private String id;

    public GrouponAddResponse() {
    }

    public GrouponAddResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
