package cn.chengyi.the_back_end.Utils;

import cn.chengyi.the_back_end.utils.MD5Util;
import org.junit.jupiter.api.Test;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>MD5工具类的测试类</p>
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public class MD5UtilTest {

	@Test
	public void testGetMD5(){
		String testStr = "test";
		System.out.println(testStr);
		System.out.println(MD5Util.getMD5("test"));
		System.out.println(MD5Util.getMD5("test"));
	}

}
