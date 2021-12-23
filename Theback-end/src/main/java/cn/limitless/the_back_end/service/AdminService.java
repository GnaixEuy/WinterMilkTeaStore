package cn.limitless.the_back_end.service;

import cn.limitless.the_back_end.entity.Admin;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface AdminService {

	/**
	 * 传入管理员id来验证身份，当管理员身份匹配时，返回一个管理员对象
	 * @param adminId 传入一个字符串类型当管理员Id
	 * @return Admin 返回一个管理员对象
	 */
	Admin verifyAdministratorIdentity(Integer adminId,String adminPassword);

	Admin findAdminById(Integer adminId);

}
