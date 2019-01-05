package bath.dataservice.user;

import bath.entity.address.Address;
import bath.entity.coupon.Coupon;
import bath.entity.groupon.Groupon;
import bath.entity.order.Order;
import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.publicdatas.account.Role;
import java.util.List;

public interface UserDataService {

	void saveUser(User user);

	void addUser(User user);

	User getUserByOpenid(String openid) throws NotExistException;

	List<User> getAllUsers();

	void updateUserByOpenid(String openid, String username, Role role, String avatarUrl, String phone, String level, int integration, double balance, List<Order> orders, List<Groupon> carts, List<Address> addresses, List<Coupon> coupons) throws NotExistException;

	void deleteUserByOpenid(String openid) throws NotExistException;
}
