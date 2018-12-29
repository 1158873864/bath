package bath.data.dao.event;

import bath.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event, Integer> {
}
