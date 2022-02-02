package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.model.UserLoginModel;
import cn.limitless.the_back_end.service.UserService;
import cn.limitless.the_back_end.utils.DateTimeUtil;
import cn.limitless.the_back_end.utils.FileNameUtil;
import cn.limitless.the_back_end.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
@CrossOrigin(value = {"*"})
public class UserAction {

	private final UserService userService;

	@Autowired
	public UserAction(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "登入方法", notes = "传入参数为id、phone、name")
	@RequestMapping(value = "/login.do", method = {RequestMethod.POST})
	public UserLoginModel userLogin(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "phone") String phone, @RequestParam(name = "password") String password) {
		final UserLoginModel userLoginModel = new UserLoginModel("failed", DateTimeUtil.getDateTime());
		final User user = this.userService.userLogin(id, phone, password);
		if (user == null) {
			userLoginModel.setLoginStatus("failed");
		} else {
			userLoginModel.setUser(user);
			userLoginModel.setLoginStatus("success");
			final String sign = TokenUtil.sign(user);
			userLoginModel.setToken(sign);
		}
		return userLoginModel;
	}

	@ApiOperation(value = "注册用户")
	@RequestMapping(value = {"/add.do"}, method = {RequestMethod.POST})
	public ObjectModel userRegistration(@ApiParam(value = "用户手机号", required = true) @RequestParam(name = "phone") String userPhone,
	                                    @ApiParam(value = "用户密码", required = true) @RequestParam(name = "password") String userPassword,
	                                    @ApiParam(value = "用户姓名", required = true) @RequestParam(name = "name") String userName,
	                                    @ApiParam(value = "用户生日，input type 为date", required = true) String userBirthday,
	                                    @ApiParam(value = "用户头像地址，结合ajax图片上传接口的返回值使用") String userImageId) {
		final User user = new User();
		if (userBirthday != null && !"".equals(userBirthday)) {
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

	@RequestMapping(value = {"/userNum.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取用户数量接口")
	public int getUserNum() {
		return this.userService.getAllUserNum();
	}

	@RequestMapping(value = {"/ajaxImgUpload.do"}, method = {RequestMethod.POST})
	@ApiOperation(value = "用户头像上传接口", notes = "务必修改表单的type方式，返回包装好的上传地址拼接到用户头像地址栏中")
	public ObjectModel ajaxImageUpload(MultipartFile userImage,
	                                   HttpServletRequest request) {
		ObjectModel objectModel = new ObjectModel();
		//提取UUID + 上传图片的后缀.jpeg
		/* 返回的图片url路径 */
		String saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(Objects.requireNonNull(userImage.getOriginalFilename()));
		//得到项目中图片存储对路径
		final String outPath = System.getProperty("user.dir") + "/Theback-end/target/classes/static/userImg";
		//转存
		final String imageOutPath = outPath + File.separator + saveFileName;
		try {
			userImage.transferTo(new File(imageOutPath));
			objectModel.setObject(saveFileName);
			return objectModel;
		} catch (IOException e) {
			e.printStackTrace();
			objectModel.setRequestServiceStatus("failed");
			return objectModel;
		}
	}
}