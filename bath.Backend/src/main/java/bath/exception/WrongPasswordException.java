package bath.exception;

import bath.response.WrongResponse;

public class WrongPasswordException extends Exception {
	private WrongResponse response;

	public WrongPasswordException(String username) {
		super("用户"+username+"密码错误");
		response = new WrongResponse(10003, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
