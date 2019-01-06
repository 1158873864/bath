package bath.dataservice.purchase;

import bath.entity.purchase.Purchase;
import bath.exception.NotExistException;

import java.util.List;

public interface PurchaseDataService {

	void addPurchase(Purchase purchase);

	Purchase getPurchaseById(String id) throws NotExistException;

	List<Purchase> getPurchasesByOpenid(String openid);

	List<Purchase> getAllPurchases();

	void deletePurchaseById(String id) throws NotExistException;
}
