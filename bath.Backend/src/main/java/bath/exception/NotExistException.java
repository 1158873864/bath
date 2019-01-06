package bath.exception;

import bath.response.WrongResponse;

public class NotExistException extends Exception {
	private WrongResponse response;

	public NotExistException(String missingClass, String missingItem) {
		super(missingClass + " '" + missingItem + "' 不存在");
		response = new WrongResponse(10001, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}

	public static void main(String []args) {
		try {
			throw new NotExistException("ID", "123456");
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}
}
