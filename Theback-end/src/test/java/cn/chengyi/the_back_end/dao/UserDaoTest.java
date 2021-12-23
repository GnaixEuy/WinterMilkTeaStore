package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@SpringBootTest
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	void addUser() {
		String userId = "id3";
		String userName = "test3";
		String userPassword = "test";
		Double userIntegral = 663.0;
		Date userBirthday = new Date();
		String userPhone = "test";
		String userImageId = "test";
		final User user = new User(userId, userName, userPassword, userIntegral, userBirthday, userPhone, userImageId);
		final int i = this.userDao.addUser(user);
		if (i == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void updateUser() {
		final User user = this.userDao.selectUserById("id");
		user.setUserName("焯");
		final int i = this.userDao.updateUser(user);
		if (i == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void selectUserById() {
		String userId = "id";
		final User user = this.userDao.selectUserById(userId);
		System.out.println(user.getUserName());
	}

	@Test
	void selectUserByName() {
		String userName = "test";
		final List<User> users = this.userDao.selectUserByName(userName);
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}

	@Test
	void findAllUser() {
		final List<User> allUser = this.userDao.findAllUser();
		for (User user : allUser) {
			System.out.println(user.getUserName());
		}
	}

	@Test
	void deleteUserById() {
		final int deleteUserById = this.userDao.deleteUserById("id1");
		if (deleteUserById == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}
}