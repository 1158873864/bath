package bath.blservice.admin;

import bath.exception.DuplicateUsernameException;
import bath.exception.NotExistException;
import bath.response.BoolResponse;
import bath.response.InfoResponse;
import bath.response.admin.AdminListResponse;
import bath.response.admin.AdminResponse;

public interface AdminBlService {

	/**
	 * 检查用户名username是否已经存在
	 * @param username 用户名
	 * @return 是否存在
	 */
	BoolResponse isAdminUsernameExistent(String username);

	/**
	 * 管理员登录验证
	 * @param username 用户名
	 * @param password 密码
	 * @return 是否验证成功
	 */
	String loginAdmin(String username, String password);

	/**
	 * 添加管理员
	 * @param username 用户名
	 * @param password 密码
	 * @param limits 权限
	 * @param date 创建时间
	 * @param face 管理员头像
	 * @return 是否成功
	 */
	InfoResponse addAdmin(String username, String password, String limits, String date, String face) throws DuplicateUsernameException;

	/**
	 * 获取管理员信息
	 * @param id 管理员id
	 * @return 管理员信息
	 */
	AdminResponse getAdmin(String id) throws NotExistException;

	/**
	 * 根据管理员用户名获取管理员信息
	 * @param username 管理员用户名
	 * @return 管理员信息
	 */
	AdminResponse getAdminByUsername(String username) throws NotExistException;

	/**
	 * 获取管理员列表
	 * @return 管理员列表
	 */
	AdminListResponse getAdminList();

	/**
	 * 根据管理员ID修改管理员信息
	 * @param id 管理员ID
	 * @param username 用户名
	 * @param password 密码
	 * @param limits 权限
	 * @param date 创建日期
	 * @return 是否成功
	 */
	InfoResponse updateAdmin(String id, String username, String password, String limits, String date, String face) throws NotExistException;

	/**
	 * 根据管理员ID删除管理员
	 * @param id 管理员ID
	 * @return 是否成功
	 */
	BoolResponse deleteAdmin(String id) throws NotExistException;
}
