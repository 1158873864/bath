package bath.data.dao.admin;

import bath.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin, String> {
	List<Admin> findAdminByUsername(String username);
}
