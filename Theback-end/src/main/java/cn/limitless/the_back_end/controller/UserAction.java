package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@EnableOpenApi
@Api(value = "用户登入接口，token未完成")
@RestController
@RequestMapping(value = {"/user"}, method = {RequestMethod.POST,RequestMethod.GET})
@CrossOrigin(value = {"*"})
public class UserAction {

	private final UserService userService;
	@Autowired
	public UserAction(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "登入方法", notes = "传入参数为id、phone、name")
	@RequestMapping("/login.do")
	public ObjectModel userLogin(@RequestParam(name = "id", required = false) String id,
	                             @RequestParam(name = "phone") String phone,
	                             @RequestParam(name = "password") String password) {
		System.out.println(phone);
		System.out.println(password);
		final ObjectModel objectModel = new ObjectModel();
		final User user = this.userService.userLogin(id, phone, password);
		if (user == null) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(user);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/add.do"})
	public ObjectModel userRegistration(@RequestParam(name = "phone") String userPhone, @RequestParam(name = "password") String userPassword, String userName, String userBirthday, String userImageId) {
		final User user = new User();
		if (!"".equals(userBirthday)) {
			Date date;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(userBirthday);
				user.setUserBirthday(date);
				System.out.println(user.getUserBirthday());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(userPassword)) {
			user.setUserPassword(userPassword);
		}
		if (!"".equals(userName)) {
			user.setUserName(userName);
		}
		if (!"".equals(userPhone)) {
			user.setUserPhone(userPhone);
		}
		if (!"".equals(userImageId)) {
			user.setUserImageId(userImageId);
		}
		final boolean b = this.userService.addUser(user);
		final ObjectModel objectModel = new ObjectModel();
		if (b) {
			final User user1 = this.userService.userLogin(null, userPhone, userPassword);
			objectModel.setObject(user1);
		} else {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}


}