package cn.limitless.the_back_end.service.impl;

import cn.limitless.the_back_end.dao.AdminDao;
import cn.limitless.the_back_end.entity.Admin;
import cn.limitless.the_back_end.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;

	@Autowired
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	/**
	 * 传入管理员id来验证身份，当管理员身份匹配时，返回一个管理员对象
	 *
	 * @param adminId 传入一个字符串类型当管理员Id
	 * @return Admin 返回一个管理员对象
	 */
	@Override
	public Admin verifyAdministratorIdentity(Integer adminId, String adminPassword) {
		return null;
	}

	@Override
	public Admin findAdminById(Integer adminId) {
		return this.adminDao.findAdminById(adminId);
	}
}
