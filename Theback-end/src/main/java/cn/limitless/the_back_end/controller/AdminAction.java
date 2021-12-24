package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Admin;
import cn.limitless.the_back_end.model.AdminLoginModel;
import cn.limitless.the_back_end.service.AdminService;
import cn.limitless.the_back_end.utils.DateTimeUtil;
import cn.limitless.the_back_end.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.servlet.http.HttpServletResponse;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>管理员模块的业务控制器</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/admin",})
@CrossOrigin(value = {"*"})
public class AdminAction {

	private final AdminService adminService;

	@Autowired
	public AdminAction(AdminService adminService) {
		this.adminService = adminService;
	}

	@ApiOperation(value = "登入方法",notes = "开发期间开放POST/GET")
	@RequestMapping(value = {"/login.do",}, method = {RequestMethod.POST,RequestMethod.GET})
	public AdminLoginModel adminLogin(@RequestParam("id") Integer id, @RequestParam("password") String password) {
		if (id == null || password == null) {
			return new AdminLoginModel("failed");
		}else {
			String encryptionPassword = MD5Util.getMD5(password);
			final Admin admin = this.adminService.findAdminById(id);
			if (admin == null){
				return new AdminLoginModel("id error",DateTimeUtil.getDateTime());
			}else if (admin.getAdminPassword().equals(encryptionPassword)){
				return new AdminLoginModel(admin,"success", DateTimeUtil.getDateTime());
			}else {
				return new AdminLoginModel("password error",DateTimeUtil.getDateTime());
			}
		}
	}


}
