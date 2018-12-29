package bath.exception;

import bath.response.WrongResponse;

public class DuplicateUsernameException extends Exception {
	private WrongResponse response;

	public DuplicateUsernameException(String username) {
		super("用户名"+username+"已存在，不能重复添加");
		response = new WrongResponse(10004, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
