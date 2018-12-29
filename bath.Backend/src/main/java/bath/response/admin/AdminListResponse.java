package bath.response.admin;

import bath.response.Response;

import java.util.List;

public class AdminListResponse extends Response {
	private List<AdminItem> admins;

	public AdminListResponse() {
	}

	public AdminListResponse(List<AdminItem> admins) {
		this.admins = admins;
	}

	public List<AdminItem> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminItem> admins) {
		this.admins = admins;
	}
}
