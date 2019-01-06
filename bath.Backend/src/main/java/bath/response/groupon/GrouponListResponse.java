package bath.response.groupon;

import bath.entity.groupon.Groupon;
import bath.response.Response;

import java.util.List;

public class GrouponListResponse extends Response {
    private List<Groupon> groupons;

    public List<Groupon> getGroupons() {
        return groupons;
    }

    public void setGroupons(List<Groupon> groupons) {
        this.groupons = groupons;
    }

    public GrouponListResponse(List<Groupon> groupons) {

        this.groupons = groupons;
    }

    public GrouponListResponse() {

    }
}
