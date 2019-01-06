package bath.data.dao.event;

import bath.entity.event.FirstOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstOrderEventDao extends JpaRepository<FirstOrderEvent, String> {
}
