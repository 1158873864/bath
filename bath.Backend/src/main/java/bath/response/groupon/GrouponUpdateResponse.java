package bath.response.groupon;

import bath.response.Response;

public class GrouponUpdateResponse extends Response {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GrouponUpdateResponse(String id) {

        this.id = id;
    }

    public GrouponUpdateResponse() {

    }
}
