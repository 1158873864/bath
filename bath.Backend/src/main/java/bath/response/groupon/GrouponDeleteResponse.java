package bath.response.groupon;

import bath.response.Response;

public class GrouponDeleteResponse extends Response {
    private String id;

    public GrouponDeleteResponse() {
    }

    public GrouponDeleteResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
