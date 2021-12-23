package cn.limitless.the_back_end.model;

import cn.limitless.the_back_end.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>封住管理员登入后的返回的信息</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminLoginModel {
	Admin admin;
	String loginStatus;
	String loginTime;

	public AdminLoginModel(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public AdminLoginModel(String loginStatus, String loginTime) {
		this.loginStatus = loginStatus;
		this.loginTime = loginTime;
	}
}
