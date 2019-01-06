package bath.data.dao.groupon;

import bath.entity.groupon.Groupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrouponDao extends JpaRepository<Groupon, String> {
}
