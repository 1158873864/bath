package bath.exception;

import bath.response.WrongResponse;

public class AlreadyExistException extends Exception {
	private WrongResponse response;

	public AlreadyExistException(String existingItem) {
		super(existingItem+"已经存在，不能重复添加");
		response = new WrongResponse(10005, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
