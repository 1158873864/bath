package bath.response.user;

import bath.response.Response;

import java.util.List;

public class UserListResponse extends Response {
	private List<UserItem> users;

	public UserListResponse() {
	}

	public UserListResponse(List<UserItem> users) {
		this.users = users;
	}

	public List<UserItem> getUsers() {
		return users;
	}

	public void setUsers(List<UserItem> users) {
		this.users = users;
	}
}
