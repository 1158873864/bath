package bath.data.admin;

import bath.data.dao.admin.AdminDao;
import bath.dataservice.admin.AdminDataService;
import bath.entity.admin.Admin;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminDataServiceImpl implements AdminDataService {
	private final AdminDao adminDao;

	@Autowired
	public AdminDataServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean isAdminExistent(String username) {
		return !adminDao.findAdminByUsername(username).isEmpty();
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	public Admin getAdminById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if(optionalAdmin.isPresent()) {
			return optionalAdmin.get();
		}else {
			throw new NotExistException("Admin ID", id);
		}
	}

	@Override
	public Admin getAdminByUsername(String username) throws NotExistException {
		if (!adminDao.findAdminByUsername(username).isEmpty()) {
			return adminDao.findAdminByUsername(username).get(0);
		} else {
			throw new NotExistException("Admin username", username);
		}
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.findAll();
	}

	@Override
	public void updateAdminById(String id, String username, String password, String limits, String date, String face) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if(optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setLimits(limits);
			admin.setDate(date);
			admin.setFace(face);
			adminDao.save(admin);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}

	@Override
	public void deleteAdminById(String id) throws NotExistException {
		Optional<Admin> optionalAdmin = adminDao.findById(id);
		if (optionalAdmin.isPresent()) {
			Admin admin = optionalAdmin.get();
			adminDao.deleteById(id);
		} else {
			throw new NotExistException("Admin ID", id);
		}
	}
}
