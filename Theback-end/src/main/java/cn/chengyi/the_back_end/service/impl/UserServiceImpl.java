package cn.chengyi.the_back_end.service.impl;

import cn.chengyi.the_back_end.dao.UserDao;
import cn.chengyi.the_back_end.entity.User;
import cn.chengyi.the_back_end.service.UserService;
import cn.chengyi.the_back_end.utils.MD5Util;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>userService实现类</p>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 添加用户
	 *
	 * @param user 传入用户实体
	 * @return 返回是否添加成功
	 */
	@Override
	public boolean addUser(User user) {
		if (user.getUserId() == null || "".equals(user.getUserId())) {
			return false;
		} else {
			if (user.getUserPassword() == null || "".equals(user.getUserPassword())) {
				return false;
			}
			final String unencryptedUserPassword = user.getUserPassword();
			final String md5Password = MD5Util.getMD5(unencryptedUserPassword);
			user.setUserPassword(md5Password);
			final int addUser = this.userDao.addUser(user);
			return addUser == 1;
		}
	}

	/**
	 * 更新用户信息
	 *
	 * @param userId 传入要更新的用户
	 * @param user   可传入部分参数的对象，方法保证如果原本有信息不会丢失
	 * @return 返回是否更新成功
	 */
	@Override
	public boolean updateUserInfo(String userId, User user) {
		if (userId == null || "".equals(userId)) {
			final String userName = user.getUserName();
			if (userName == null || "".equals(userName)) {
				return false;
			} else {
				final List<User> oldUsers = this.userDao.selectUserByName(userName);
				for (User oldUser : oldUsers) {
					if (oldUser.getUserName().equals(userName)) {
						return updateUserAttribute(user, oldUser);
					}
				}
				return false;
			}
		} else {
			final User oldUser = this.userDao.selectUserById(userId);
			return updateUserAttribute(user, oldUser);
		}
	}


	/**
	 * 删除用户
	 *
	 * @param userId 删除用户的id
	 * @return 返回是否删除成功
	 */
	@Override
	public boolean deleteUserInfo(String userId) {
		return this.userDao.deleteUserById(userId) == 1;
	}

	/**
	 * 验证用户登入，成功则返回用户对象
	 *
	 * @param userId   用户id
	 * @param password 用户密码
	 * @return 成功则返回用户对象，失败返回nil
	 */
	@Override
	@Deprecated
	public User userLogin(String userId, String password) {
		final String encryptionPassword = MD5Util.getMD5(password);
		final User user = this.userDao.selectUserById(userId);
		if (user.getUserPassword().equals(encryptionPassword)) {
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 获取所有用户信息
	 *
	 * @return 返回装有所有用户信息的集合
	 */
	@Override
	public List<User> findAllUser() {
		return this.userDao.findAllUser();
	}

	/**
	 * 用户信息分页查询
	 *
	 * @param pageNum  当前分页数
	 * @param pageSize 当前页最大数量
	 * @return 返回pageInfo 描述的装有当前页的集合
	 */
	@Override
	public PageInfo<User> splitAllUser(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		final List<User> splitUser = this.userDao.findAllUser();
		return new PageInfo<>(splitUser);
	}

	/**
	 * 提取出来的重复代码快，用于保证更新用户数据时，竟可能地保存非空数据
	 *
	 * @param user    新数据
	 * @param oldUser 旧数据
	 * @return 更新是否成功
	 */
	private boolean updateUserAttribute(User user, User oldUser) {
		final Date userBirthday = user.getUserBirthday();
		final String userImageId = user.getUserImageId();
		final Double userIntegral = user.getUserIntegral();
		final String userPhone = user.getUserPhone();
		final String userPassword = user.getUserPassword();
		if (userBirthday != null) {
			oldUser.setUserBirthday(userBirthday);
		}
		if (userImageId != null && !"".equals(userImageId)) {
			oldUser.setUserImageId(userImageId);
		}
		if (userIntegral != null) {
			oldUser.setUserIntegral(userIntegral);
		}
		if (userPassword != null && !"".equals(userPassword)) {
			oldUser.setUserPassword(userPassword);
		}
		if (userPhone != null && !"".equals(userPhone)) {
			oldUser.setUserPhone(userPhone);
		}
		final int i = this.userDao.updateUser(oldUser);
		return i == 1;
	}
}
