package bath.response.user;

import bath.response.Response;

public class QrCodeResponse extends Response {
	private boolean ok; //是否成功
	private String message; //相关信息
	private String imagePath; //二维码图片路径

	public QrCodeResponse() {
	}

	public QrCodeResponse(boolean ok, String message, String imagePath) {
		this.ok = ok;
		this.message = message;
		this.imagePath = imagePath;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String isMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
