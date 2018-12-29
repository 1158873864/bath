package bath.response;

public class IntResponse extends BoolResponse {
	private int number;

	public IntResponse(boolean ok, int number, String message) {
		super(ok, message);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
