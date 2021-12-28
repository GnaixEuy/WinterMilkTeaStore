package cn.limitless.the_back_end.Utils;

import cn.limitless.the_back_end.entity.Admin;
import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.utils.TokenUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/28
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
class TokenUtilTest {

	@Test
	void sign() {
		User user = new User("123", "测试", "1111", 0.0, new Date(), "123123123123", "123");
		Admin admin = new Admin(11, "bosstest", "123123");
		final String sign = TokenUtil.sign(user);
		System.out.println(TokenUtil.verify(sign));
		final String adminsign = TokenUtil.sign(admin);
		System.out.println(TokenUtil.verify(adminsign));
	}

}