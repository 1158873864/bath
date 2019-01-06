package bath.response.groupon;

import bath.entity.groupon.Groupon;
import bath.response.Response;

public class GrouponResponse extends Response {
    private Groupon groupon;

    public GrouponResponse() {
    }

    public GrouponResponse(Groupon groupon) {
        this.groupon = groupon;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }
}
