package cn.limitless.the_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>会员实体类</p>
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	private String userId;
	private String userName;
	private String userPassword;
	private Double userIntegral;
	private Date userBirthday;
	private String userPhone;
	private String userImageId;

}
