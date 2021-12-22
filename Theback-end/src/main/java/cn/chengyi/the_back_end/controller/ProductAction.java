package cn.chengyi.the_back_end.controller;

import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.model.ObjectModel;
import cn.chengyi.the_back_end.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>商品controller</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/product"})
public class ProductAction {

	/**
	 * 每个商品页面默认展示的商品条数
	 */
	private static final int PAGE_SHOW_SIZE = 5;

	private final ProductService productService;

	@Autowired
	public ProductAction(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 添加商品
	 * 未完成，留有原料未正常构建
	 *
	 * @param product
	 * @return
	 */
	@RequestMapping(value = {"/addProduct.do"})
	@Deprecated
	public Product addProduct(@NotNull Product product, @RequestParam(value = "productMaterialList") String[] productMaterialList) {
		System.out.println(product.getProductName());
		try {
			this.productService.addProduct(product.getProductName(), product.getProductType(), product.getProductPrice(), product.getProductImageId(), productMaterialList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 传入productId或productName都可以，当二者都不存在或参数信息错误时返回状态failed
	 *
	 * @param productId   商品的id
	 * @param productName 商品的名字
	 * @return 返回服务处理状态
	 */
	@ApiOperation(value = "商品删除", notes = "传入productId或productName都可以，当二者都不存在或参数信息错误时返回状态failed，业务成功则返回success状态码", httpMethod = "测试期间全开，前端匹配后关闭get或post")
	@RequestMapping(value = {"/deleteProduct.do"})
	public ObjectModel deleteProduct(Integer productId, String productName) {
		if (productId != null) {
			final boolean deleteSuccess = this.productService.deleteProduct(productId);
			if (deleteSuccess) {
				return new ObjectModel();
			}
		} else if (productName != null && !"".equals(productName)) {
			this.productService.deleteProductByName(productName);
			return new ObjectModel();
		}
		final ObjectModel objectModel = new ObjectModel();
		objectModel.setRequestServiceStatus("failed");
		return objectModel;
	}

	@RequestMapping(value = {"/getProductById.do"})
	public ObjectModel getProductById(@NotNull Integer id) {
		return new ObjectModel(this.productService.findProductById(id));
	}

	@RequestMapping(value = {"/getAllProduct.do"})
	public ObjectModel getAllProduct() {
		List<Product> allProducts = this.productService.findAllProducts();
		if (allProducts != null) {
			return new ObjectModel(allProducts);
		} else {
			final ObjectModel objectModel = new ObjectModel(null);
			objectModel.setRequestServiceStatus("false");
			return objectModel;
		}
	}

	@RequestMapping(value = {"/ajaxPage.do"})
	public ObjectModel ajaxProductPagination(Integer pageNum) {
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		final PageInfo<Product> productPageInfo = this.productService.splitPage(pageNum, PAGE_SHOW_SIZE);
		return new ObjectModel(productPageInfo);
	}

}
