package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Material;
import cn.limitless.the_back_end.entity.Product;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2022/1/11
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/QuickFind"})
@Api(value = "搜索提示接口,快速查询提示,模糊查询")
@CrossOrigin(value = "*")
public class QuickFindAction {

	private CommentService commentService;
	private UserService userService;
	private OrderService orderService;
	private ProductService productService;
	private MaterialService materialService;

	@Autowired
	public QuickFindAction(CommentService commentService, UserService userService, OrderService orderService, ProductService productService, MaterialService materialService) {
		this.commentService = commentService;
		this.userService = userService;
		this.orderService = orderService;
		this.productService = productService;
		this.materialService = materialService;
	}


	@RequestMapping(value = {"/material.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "原料搜索框提示接口")
	public ObjectModel tipMaterialName(String name) {
		final ObjectModel objectModel = new ObjectModel();
		final List<Material> list = this.materialService.quickFindMaterialByName(name);
		if (list == null) {
			objectModel.error();
		} else {
			objectModel.setObject(list);
		}
		return objectModel;
	}

	@ApiOperation(value = "商品提示接口", notes = "搜索框提示可以用")
	@RequestMapping(value = {"/product.do"}, method = {RequestMethod.GET})
	public ObjectModel fastFindProduct(String likeName) {
		final List<Product> products = this.productService.fuzzyQueryProduct(likeName);
		final ObjectModel objectModel = new ObjectModel();
		if (products.size() == 0) {
			objectModel.error();
		} else {
			objectModel.setObject(products);
		}
		return objectModel;
	}

}
