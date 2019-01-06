package bath.response.user;

import bath.response.LoginResponse;

public class UserLoginResponse extends LoginResponse {
	private UserItem user;

	public UserLoginResponse() {
	}

	public UserLoginResponse( UserItem user) {
		this.user = user;
	}

	public UserItem getUser() {
		return user;
	}

	public void setUser(UserItem user) {
		this.user = user;
	}
}
