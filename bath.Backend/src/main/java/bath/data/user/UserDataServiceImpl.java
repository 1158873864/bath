package bath.data.user;

import bath.data.dao.user.UserDao;
import bath.dataservice.user.UserDataService;
import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.Level;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService {
	private final UserDao userDao;

	@Autowired
	public UserDataServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public User getUserByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			throw new NotExistException("用户openid", openid);
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public void updateUserByOpenid(String openid, String username, Role role, String avatarUrl, String phone, String level, int integration, double balance, List<Order> orders, List<Groupon> carts, List<Address> addresses, List<Coupon> coupons) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setUsername(username);
			user.setRole(role);
			user.setAvatarUrl(avatarUrl);
			user.setAvatarUrl(phone);
			user.setLevel(level);
			user.setIntegration(integration);
			user.setBalance(balance);
			user.setOrders(orders);
			user.setCarts(carts);
			user.setAddresses(addresses);
			user.setCoupons(coupons);
			userDao.save(user);
		} else {
			throw new NotExistException("User openid", openid);
		}
	}

	@Override
	public void deleteUserByOpenid(String openid) throws NotExistException {
		Optional<User> optionalUser = userDao.findById(openid);
		if (optionalUser.isPresent()) {
			userDao.deleteById(openid);
		} else {
			throw new NotExistException("User openid", openid);
		}
	}


}
