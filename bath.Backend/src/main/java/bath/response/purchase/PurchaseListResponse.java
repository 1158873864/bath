package bath.response.purchase;

import bath.response.Response;

import java.util.List;

public class PurchaseListResponse extends Response {
	private List<PurchaseItem> purchases;

	public PurchaseListResponse() {
	}

	public PurchaseListResponse(List<PurchaseItem> purchases) {
		this.purchases = purchases;
	}

	public List<PurchaseItem> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseItem> purchases) {
		this.purchases = purchases;
	}
}
