package bath.data.purchase;

import bath.data.dao.purchase.PurchaseDao;
import bath.dataservice.purchase.PurchaseDataService;
import bath.entity.purchase.Purchase;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseDataServiceImpl implements PurchaseDataService {
	private final PurchaseDao purchaseDao;

	@Autowired
	public PurchaseDataServiceImpl(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.save(purchase);
	}

	@Override
	public Purchase getPurchaseById(String id) throws NotExistException {
		Optional<Purchase> optionalPurchase = purchaseDao.findById(id);
		if(optionalPurchase.isPresent()) {
			return optionalPurchase.get();
		} else {
			throw new NotExistException("Purchase ID", id);
		}
	}

	@Override
	public List<Purchase> getPurchasesByOpenid(String openid) {
		return purchaseDao.findPurchasesByOpenid(openid);
	}

	@Override
	public List<Purchase> getAllPurchases() {
		return purchaseDao.findAll();
	}

	@Override
	public void deletePurchaseById(String id) throws NotExistException {
		if (purchaseDao.existsById(id)) {
			purchaseDao.deleteById(id);
		} else {
			throw new NotExistException("Purchase ID", id);
		}
	}
}
