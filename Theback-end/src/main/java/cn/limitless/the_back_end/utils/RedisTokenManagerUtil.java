package cn.limitless.the_back_end.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/29
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */

@Component
public class RedisTokenManagerUtil {

	// 注入
	private final RedisTemplate redisTemplate;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public RedisTokenManagerUtil(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void saveToRedis(String id, String token) {
		logger.info("**************** RedisTokenManager : saveToRedis ****************");
		redisTemplate.opsForValue().set(id, token, 30, TimeUnit.SECONDS);
		logger.info(token);
	}

	public void getFromReids(String id) {
		logger.info("**************** RedisTokenManager : getFromReids ****************");
		// 设置超时时间为60秒
		String token = (String) redisTemplate.opsForValue().get(id);
		logger.info(token);
	}

}
