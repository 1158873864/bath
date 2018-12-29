package bath.response;

public class BoolResponse extends Response {
	private boolean ok; //是否执行成功
	private String message; //具体信息

	public BoolResponse(boolean ok, String message) {
		this.ok = ok;
		this.message = message;
	}

	public boolean isOk() {
		return ok;
	}

	public String getMessage() {
		return message;
	}
}
