package bath.data.dao.purchase;

import bath.entity.purchase.PurchaseCourse;
import bath.entity.purchase.PurchaseCourseKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseCourseDao extends JpaRepository<PurchaseCourse, PurchaseCourseKey> {
	List<PurchaseCourse> findPurchaseCourseByOpenid(String openid);
	List<PurchaseCourse> findPurchaseCourseByCourseId(String courseId);
}
