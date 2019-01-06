package bath.data.purchase;

import bath.data.dao.purchase.PurchaseCourseDao;
import bath.dataservice.purchase.PurchaseCourseDataService;
import bath.entity.purchase.PurchaseCourse;
import bath.entity.purchase.PurchaseCourseKey;
import bath.exception.AlreadyExistException;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseCourseDataServiceImpl implements PurchaseCourseDataService {
	private final PurchaseCourseDao purchaseCourseDao;

	@Autowired
	public PurchaseCourseDataServiceImpl(PurchaseCourseDao purchaseCourseDao) {
		this.purchaseCourseDao = purchaseCourseDao;
	}

	@Override
	public boolean isPurchaseCourseExistent(PurchaseCourseKey purchaseCourseKey) {
		return purchaseCourseDao.existsById(purchaseCourseKey);
	}

	@Override
	public void addPurchaseCourse(PurchaseCourse purchaseCourse) throws AlreadyExistException {
		if (purchaseCourseDao.existsById(new PurchaseCourseKey(purchaseCourse.getOpenid(), purchaseCourse.getCourseId()))) {
			throw new AlreadyExistException("PurchaseCourse(openid=" + purchaseCourse.getOpenid() + ",courseId=" + purchaseCourse.getCourseId() + ")");
		} else {
			purchaseCourseDao.save(purchaseCourse);
		}
	}

	@Override
	public PurchaseCourse getPurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException {
		Optional<PurchaseCourse> optionalPurchaseCourse = purchaseCourseDao.findById(purchaseCourseKey);
		if (optionalPurchaseCourse.isPresent()) {
			return optionalPurchaseCourse.get();
		} else {
			throw new NotExistException("PurchaseCourse", "(openid="+purchaseCourseKey.getOpenid()+",courseId="+purchaseCourseKey.getCourseId()+")");
		}
	}

	@Override
	public List<PurchaseCourse> getPurchaseCourseByOpenid(String openid) {
		return purchaseCourseDao.findPurchaseCourseByOpenid(openid);
	}

	@Override
	public List<PurchaseCourse> getPurchaseCourseByCourseId(String courseId) {
		return purchaseCourseDao.findPurchaseCourseByCourseId(courseId);
	}

	@Override
	public List<PurchaseCourse> getAllPurchaseCourses() {
		return purchaseCourseDao.findAll();
	}

	@Override
	public void deletePurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException {
		if (purchaseCourseDao.existsById(purchaseCourseKey)) {
			throw new NotExistException("PurchaseCourse", "(openid="+purchaseCourseKey.getOpenid()+",courseId="+purchaseCourseKey.getCourseId()+")");
		} else {
			purchaseCourseDao.deleteById(purchaseCourseKey);
		}
	}
}
