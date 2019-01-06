package bath.response.purchase;

import bath.response.Response;

public class WxBuyCreditResponse extends Response {
	private WxBuyCreditItem wxBuyCredit;

	public WxBuyCreditResponse(WxBuyCreditItem wxBuyCredit) {
		this.wxBuyCredit = wxBuyCredit;
	}

	public WxBuyCreditItem getWxBuyCredit() {
		return wxBuyCredit;
	}

	public void setWxBuyCredit(WxBuyCreditItem wxBuyCredit) {
		this.wxBuyCredit = wxBuyCredit;
	}
}
