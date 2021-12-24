package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

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
@RequestMapping(value = {"/user"})
public class UserAction {

	private final UserService userService;

	@Autowired
	public UserAction(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "登入方法", notes = "传入参数为id、phone、name")
	@RequestMapping("/login.do")
	public ObjectModel userLogin(String id, String phone, String password) {
		final ObjectModel objectModel = new ObjectModel();
		final User user = this.userService.userLogin(id, phone, password);
		if (user == null) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(user);
		}
		return objectModel;
	}

	public ObjectModel userRegistration() {
		return null;
	}


}