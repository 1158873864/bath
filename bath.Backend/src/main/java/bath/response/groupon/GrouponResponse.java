package bath.response.groupon;

import bath.entity.groupon.Groupon;

public class GrouponResponse {
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
