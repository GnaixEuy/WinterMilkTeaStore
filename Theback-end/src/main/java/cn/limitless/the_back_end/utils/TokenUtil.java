package cn.limitless.the_back_end.utils;

import cn.limitless.the_back_end.entity.Admin;
import cn.limitless.the_back_end.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/23
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public class TokenUtil {

	//token到期时间10小时
	private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;
	//密钥盐
	private static final String TOKEN_SECRET = "ljdyaishijin**3nkjnj??";

	/**
	 * 生成token
	 *
	 * @param user
	 * @return
	 */
	public static String sign(User user) {
		String token = null;
		try {
			Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			token = JWT.create()
					//发行人
					.withIssuer("auth0")
					//存放数据
					.withClaim("username", user.getUserName())
					//过期时间
					.withExpiresAt(expireAt)
					.sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException | JWTCreationException je) {

		}
		return token;
	}

	public static String sign(Admin user) {
		String token = null;
		try {
			Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			token = JWT.create()
					//发行人
					.withIssuer("auth0")
					//存放数据
					.withClaim("username", user.getAdminName())
					//过期时间
					.withExpiresAt(expireAt)
					.sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (IllegalArgumentException | JWTCreationException je) {

		}
		return token;
	}


	/**
	 * token验证
	 *
	 * @param token
	 * @return
	 */
	public static Boolean verify(String token) {

		try {
			//创建token验证器
			JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
			DecodedJWT decodedJWT = jwtVerifier.verify(token);
			System.out.println("认证通过：");
			System.out.println("username: " + decodedJWT.getClaim("username").asString());
			System.out.println("过期时间：      " + decodedJWT.getExpiresAt());
		} catch (IllegalArgumentException | JWTVerificationException e) {
			//抛出错误即为验证不通过
			return false;
		}
		return true;
	}

}


