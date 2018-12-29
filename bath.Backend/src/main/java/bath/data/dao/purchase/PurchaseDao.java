package bath.data.dao.purchase;

import bath.entity.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseDao extends JpaRepository<Purchase, String> {
	List<Purchase> findPurchasesByOpenid(String openid);
}
