package bath.bl.admin;

import bath.blservice.admin.AdminBlService;
import bath.exception.NotExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminBlServiceImplTest {
	@Autowired
	private AdminBlService adminBlService;

	@Test
	@Transactional
	@Rollback(false) // 测试中执行的SQL命令不回滚
	public void functionTest() throws NotExistException {
		System.out.println("TEST BEGIN");
//		adminBlService.deleteAdmin("297e11aa6584789b01658478c6190001");
		System.out.println("TEST END");
	}
}
