package bath.dataservice.purchase;

import bath.entity.purchase.PurchaseCourse;
import bath.entity.purchase.PurchaseCourseKey;
import bath.exception.AlreadyExistException;
import bath.exception.NotExistException;

import java.util.List;

public interface PurchaseCourseDataService {

	boolean isPurchaseCourseExistent(PurchaseCourseKey purchaseCourseKey);

	void addPurchaseCourse(PurchaseCourse purchaseCourse) throws AlreadyExistException;

	PurchaseCourse getPurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException;

	List<PurchaseCourse> getPurchaseCourseByOpenid(String openid);

	List<PurchaseCourse> getPurchaseCourseByCourseId(String courseId);

	List<PurchaseCourse> getAllPurchaseCourses();

	void deletePurchaseCourseByKey(PurchaseCourseKey purchaseCourseKey) throws NotExistException;
}
