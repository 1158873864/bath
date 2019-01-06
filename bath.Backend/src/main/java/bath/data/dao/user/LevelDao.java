package bath.data.dao.user;

import bath.entity.user.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelDao extends JpaRepository<Level, String> {
}
