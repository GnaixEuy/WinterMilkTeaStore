package cn.chengyi.the_back_end.controller;

import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.model.ObjectModel;
import cn.chengyi.the_back_end.service.ProductService;
import cn.chengyi.the_back_end.utils.DateTimeUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>商品controller</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Controller
@RequestMapping(value = {"/product"})
@ResponseBody
public class ProductAction {

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
			this.productService.addProduct(product.getProductName(), product.getProductType(), product.getProductPrice(), product.getProductImageId(),productMaterialList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			return new ObjectModel(null);
		}
	}

//	public ObjectModel ajaxProductPagination(Integer integer){
//
//
//	}


}
