package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface UserDao {

	/**
	 * 添加用户
	 *
	 * @param user 用户对象
	 * @return 返回改变的数据行
	 */
	int addUser(User user);

	/**
	 * 通过id删除用户
	 *
	 * @param userId 用户id
	 * @return 返回改变的数据行
	 */
	int deleteUserById(String userId);

	/**
	 * 更新用户信息
	 *
	 * @param user 传入用户实体对象
	 * @return 返回改变的数据行
	 */
	int updateUser(User user);

	/**
	 * 通过用户id查找用户信息
	 *
	 * @param userId 用户实体对象
	 * @return 放回一个用户实体对象
	 */
	User selectUserById(String userId);

	/**
	 * 通过用户手机号查询用户信息
	 *
	 * @param userPhone 手机号码
	 * @return 返回用户实体对象
	 */
	User selectUserByPhone(String userPhone);

	/**
	 * 通过用户名字查找用户对象
	 *
	 * @param userName 用户名字
	 * @return 返回一个名字为参数的用户对象集合
	 */
	List<User> selectUserByName(String userName);

	/**
	 * 查找所有用户对象
	 *
	 * @return 返回一个用户对象集合
	 */
	List<User> findAllUser();

	/**
	 * 查询用户总数量
	 *
	 * @return 返回用户总数量
	 */
	int selectUserNum();
}
