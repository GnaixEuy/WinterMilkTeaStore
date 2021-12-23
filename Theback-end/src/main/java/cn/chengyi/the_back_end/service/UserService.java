package cn.chengyi.the_back_end.service;

import cn.chengyi.the_back_end.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param user 传入用户实体
	 * @return 返回是否添加成功
	 */
	boolean addUser(User user);

	/**
	 * 更新用户信息
	 * @param userId 传入要更新的用户
	 * @param user 可传入部分参数的对象，方法保证如果原本有信息不会丢失
	 * @return 返回是否更新成功
	 */
	boolean updateUserInfo(String userId, User user);

	/**
	 * 删除用户
	 * @param userId 删除用户的id
	 * @return 返回是否删除成功
	 */
	boolean deleteUserInfo(String userId);

	/**
	 * 验证用户登入，成功则返回用户对象
	 * @param userId 用户id
	 * @param password 用户密码
	 * @return 成功则返回用户对象，失败返回nil
	 */
	User userLogin(String userId, String password);

	/**
	 * 获取所有用户信息
	 * @return 返回装有所有用户信息的集合
	 */
	List<User> findAllUser();

	/**
	 * 用户信息分页查询
	 * @param pageNum 当前分页数
	 * @param pageSize 当前页最大数量
	 * @return 返回pageInfo 描述的装有当前页的集合
	 */
	PageInfo<User> splitAllUser(Integer pageNum, Integer pageSize);

}
