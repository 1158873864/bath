package bath.entity.purchase;

import javax.persistence.*;

@Entity
@Table(name = "purchaseCourse")
@IdClass(PurchaseCourseKey.class)
public class PurchaseCourse {
	@Id
	@Column(name = "openid", length = 28)
	private String openid; //用户微信ID

	@Id
	@Column(name = "courseId", length = 32)
	private String courseId; //课程ID

	public PurchaseCourse() {
	}

	public PurchaseCourse(String openid, String courseId) {
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
