package cn.chengyi.the_back_end.controller;

import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	public Product addProduct(Product product) {
		System.out.println(product.getProductName());
		this.productService.addProduct(product.getProductName(), product.getProductType(), product.getProductPrice(), product.getProductImageId(), new String[]{});
		return null;
	}

	@RequestMapping(value = {"/getProductById.do"})
	public Product getProductById(Integer id) {
		return this.productService.findProductById(id);
	}

	public List<Product> getAllProduct() {
		return this.productService.findAllProducts();
	}
}
