package bath.data.dao.event;

import bath.entity.event.FullSubtractionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FullSubtractionEventDao extends JpaRepository<FullSubtractionEvent, String> {
}
