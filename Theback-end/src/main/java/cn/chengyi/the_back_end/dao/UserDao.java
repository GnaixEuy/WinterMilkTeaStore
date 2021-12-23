package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.User;

import java.awt.*;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface UserDao {

	int addUser(User user);

	int deleteUserById(String userId);

	int updateUser(User user);

	User selectUserById(String userId);

	User selectUserByName(String userName);

	List<User> findAllUser();

}
