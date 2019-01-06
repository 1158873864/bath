package bath.exception;

import bath.response.WrongResponse;

public class CardLimitUseUpException extends Exception {
	private WrongResponse response;

	public CardLimitUseUpException() {
		super("今日查看次数已经用完");
		response = new WrongResponse(10002, this.getMessage());
	}

	public WrongResponse getResponse() {
		return response;
	}
}
