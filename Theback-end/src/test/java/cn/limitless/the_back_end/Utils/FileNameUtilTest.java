package cn.limitless.the_back_end.Utils;

import cn.limitless.the_back_end.utils.FileNameUtil;
import org.junit.jupiter.api.Test;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>uuid工具类的测试类</p>
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public class FileNameUtilTest {

	@Test
	public void testGetUUIDFileName(){
		String testStr = "测试.jpeg";
		final String s = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(testStr);
		System.out.println(s);
	}
}
