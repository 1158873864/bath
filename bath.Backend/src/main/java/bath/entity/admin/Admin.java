package bath.entity.admin;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Admin {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;//编号

	@Column(name = "username")
	private String username;//用户名

	@Column(name = "password")
	private String password; //密码

	@Column(name = "limits")
	private String limits; //权限

	@Column(name = "date")
	private String date; //创建时间

	@Column(name = "face")
	private String face; //管理员头像

	public Admin() {
	}

	public Admin(String username, String password, String limits, String date, String face) {
		this.username = username;
		this.password = password;
		this.limits = limits;
		this.date = date;
		this.face = face;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLimits() {
		return limits;
	}

	public void setLimits(String limits) {
		this.limits = limits;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}
}
