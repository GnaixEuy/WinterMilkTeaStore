package cn.limitless.the_back_end.model;

import cn.limitless.the_back_end.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/28
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginModel {

	User user;
	String loginStatus;
	String loginTime;
	String token;

	public UserLoginModel(String loginStatus, String loginTime) {
		this.loginStatus = loginStatus;
		this.loginTime = loginTime;
	}
}
