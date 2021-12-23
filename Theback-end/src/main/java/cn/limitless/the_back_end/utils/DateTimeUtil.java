package cn.limitless.the_back_end.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>时间工具类</p>
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public class DateTimeUtil {

	/**
	 * 返回yyyy-MM-dd HH:mm:ss格式的时间戳
	 * @return 返回format后的时间戳
	 */
	public static String getDateTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
