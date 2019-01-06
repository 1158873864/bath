package bath.bl.user;


import bath.blservice.user.UserBlService;
import bath.exception.NotExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBlServiceImplTest {
	@Autowired
	private UserBlService userBlService;

	@Test
	public void addUser() throws NotExistException {

	}

	@Test
	public void getUser() throws NotExistException {
		System.out.println(userBlService.getUser("111111"));
	}

	@Test
	public void getUserList() {
		System.out.println(userBlService.getUserList().getUsers().get(1));
	}

	@Test
	public void updateUser() throws NotExistException {
	}

	@Test
	public void deleteUser() throws NotExistException {
		userBlService.deleteUser("222222");
	}

	@Test
	public void addLevel() {
		userBlService.addLevel("common", 0.98);
		userBlService.addLevel("298", 0.95);
		userBlService.addLevel("998", 1);
	}
}