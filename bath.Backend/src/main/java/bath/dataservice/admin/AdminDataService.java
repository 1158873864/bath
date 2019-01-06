package bath.dataservice.admin;
import bath.entity.admin.Admin;
import bath.exception.NotExistException;

import java.util.List;
import java.util.Optional;

public interface AdminDataService {

	boolean isAdminExistent(String username);

	void addAdmin(Admin admin);

	Admin getAdminById(String id) throws NotExistException;

	Admin getAdminByUsername(String username) throws NotExistException;

	List<Admin> getAllAdmins();

	void updateAdminById(String id, String username, String password, String limits, String date, String face) throws NotExistException;

	//注意：删除Admin时统一使用deleteAdminById（封装了相关数据连锁删除）
	void deleteAdminById(String id) throws NotExistException;
}
