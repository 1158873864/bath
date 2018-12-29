package bath.data.purchase;

import bath.data.dao.purchase.BuyCreditDao;
import bath.dataservice.purchase.BuyCreditDataService;
import bath.entity.purchase.BuyCredit;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyCreditDataServiceImpl implements BuyCreditDataService {
	private final BuyCreditDao buyCreditDao;

	@Autowired
	public BuyCreditDataServiceImpl(BuyCreditDao buyCreditDao) {
		this.buyCreditDao = buyCreditDao;
	}

	@Override
	public void saveBuyCredit(BuyCredit buyCredit) {
		buyCreditDao.save(buyCredit);
	}

	@Override
	public void addBuyCredit(BuyCredit buyCredit) {
		buyCreditDao.save(buyCredit);
	}

	@Override
	public BuyCredit getBuyCreditById(String id) throws NotExistException {
		Optional<BuyCredit> optionalBuyCredit = buyCreditDao.findById(id);
		if(optionalBuyCredit.isPresent()) {
			return optionalBuyCredit.get();
		} else {
			throw new NotExistException("BuyCredit ID", id);
		}
	}

	@Override
	public List<BuyCredit> getBuyCreditByOpenid(String openid) {
		return buyCreditDao.findBuyCreditsByOpenid(openid);
	}

	@Override
	public List<BuyCredit> getAllBuyCredits() {
		return buyCreditDao.findAll();
	}

	@Override
	public void deleteBuyCreditById(String id) throws NotExistException {
		if (buyCreditDao.existsById(id)) {
			buyCreditDao.deleteById(id);
		} else {
			throw new NotExistException("BuyCredit ID", id);
		}
	}
}
