package cn.chengyi.the_back_end.controller;

import cn.chengyi.the_back_end.entity.Admin;
import cn.chengyi.the_back_end.model.AdminLoginModel;
import cn.chengyi.the_back_end.service.AdminService;
import cn.chengyi.the_back_end.utils.DateTimeUtil;
import cn.chengyi.the_back_end.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>管理员模块的业务控制器</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */

@EnableOpenApi
@Api(value = "管理员登入接口，token未完成")
@RestController
@RequestMapping(value = {"/admin",})
public class AdminAction {

	private final AdminService adminService;

	@Autowired
	public AdminAction(AdminService adminService) {
		this.adminService = adminService;
	}

	@ApiOperation(value = "登入方法",notes = "文档等会写")
	@RequestMapping(value = {"/login.do",})
	public AdminLoginModel adminLogin(Integer id, String password) {
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
