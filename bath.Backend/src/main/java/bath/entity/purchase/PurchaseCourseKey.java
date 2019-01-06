package bath.entity.purchase;

import java.io.Serializable;

public class PurchaseCourseKey implements Serializable {
	private String openid; //用户微信ID
	private String courseId; //课程ID

	public PurchaseCourseKey() {
	}

	public PurchaseCourseKey(String openid, String courseId) {
		this.openid = openid;
		this.courseId = courseId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
