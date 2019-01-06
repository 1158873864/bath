package bath.response.purchase;

import bath.response.Response;

public class PurchaseResponse extends Response {
	private PurchaseItem purchase;

	public PurchaseResponse() {
	}

	public PurchaseResponse(PurchaseItem purchase) {
		this.purchase = purchase;
	}

	public PurchaseItem getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseItem purchase) {
		this.purchase = purchase;
	}
}
