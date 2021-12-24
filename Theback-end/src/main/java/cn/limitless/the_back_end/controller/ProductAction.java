package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Product;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.ProductService;
import cn.limitless.the_back_end.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>商品controller</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/product"}, method = {RequestMethod.POST, RequestMethod.GET})
@CrossOrigin(value = {"*"})
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
	 * 完成,待测试
	 * 我艹springmvc太屌了，list直接封装进去了
	 *
	 * @param product 商品对象
	 * @return 返回添加的商品对象服务状态
	 */
	@RequestMapping(value = {"/addProduct.do"}, method = {RequestMethod.POST, RequestMethod.GET})
	@Deprecated
	public ObjectModel addProduct(@NotNull Product product) {
		System.out.println(product.getProductName());
		final ObjectModel objectModel = new ObjectModel();
		final boolean addProductBoolean = this.productService.addProduct(product.getProductName(), product.getProductType(), product.getProductPrice(), product.getProductImageId(), product.getProductMaterialList().toArray(new String[0]));
		if (addProductBoolean) {
			final Product productByName = this.productService.findProductByName(product.getProductName());
			objectModel.setObject(productByName);
		} else {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	/**
	 * 传入productId或productName都可以，当二者都不存在或参数信息错误时返回状态failed
	 *
	 * @param productId   商品的id
	 * @param productName 商品的名字
	 * @return 返回服务处理状态
	 */
	@ApiOperation(value = "商品删除", notes = "传入productId或productName都可以，当二者都不存在或参数信息错误时返回状态failed，业务成功则返回success状态码")
	@RequestMapping(value = {"/deleteProduct.do"})
	public ObjectModel deleteProduct(@RequestParam(name = "id", required = false) Integer productId,
	                                 @RequestParam(name = "name", required = false) String productName) {
		final ObjectModel objectModel = new ObjectModel();
		if (productId != null) {
			final boolean deleteSuccess = this.productService.deleteProduct(productId);
			if (!deleteSuccess) {
				objectModel.setRequestServiceStatus("failed");
			}
		} else if (productName != null && !"".equals(productName)) {
			final boolean deleteProductByName = this.productService.deleteProductByName(productName);
			if (!deleteProductByName) {
				objectModel.setRequestServiceStatus("failed");
			}
		}
		return objectModel;
	}

	/**
	 * 更新product接口
	 *
	 * @param product mvc自动封装product，前端保证name正确即可
	 * @return 返回包装好的更新信息后的对象
	 */
	@RequestMapping(value = {"/updateProduct.do",})
	public ObjectModel updateProduct(Product product) {
		final ObjectModel objectModel = new ObjectModel();
		if (product.getProductId() == null) {
			final String productName = product.getProductName();
			if (productName != null && !"".equals(productName)) {
				final Product oldProduct = this.productService.findProductByName(productName);
				final boolean b = this.productService.updateProduct(oldProduct.getProductId(), product);
				if (b) {
					objectModel.setRequestServiceStatus("success");
					objectModel.setObject(this.productService.findProductById(oldProduct.getProductId()));
				}
			}
		} else {
			final boolean b = this.productService.updateProduct(product.getProductId(), product);
			if (b) {
				objectModel.setRequestServiceStatus("success");
				objectModel.setObject(this.productService.findProductById(product.getProductId()));
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/getProductById.do"})
	public ObjectModel getProductById(@RequestParam(name = "id", required = false) Integer id) {
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
	public ObjectModel ajaxProductPagination(@RequestParam(name = "page", defaultValue = "1") Integer pageNum) {
		final PageInfo<Product> productPageInfo = this.productService.splitPage(pageNum, PAGE_SHOW_SIZE);
		return new ObjectModel(productPageInfo);
	}

	/**
	 * 商品上传图片请求
	 *
	 * @param productImage 图片binary流
	 * @param request      httpServletRequest
	 * @return 对象状态包装的文件存储uuid路径
	 */
	@RequestMapping(value = {"/ajaxProductImage.do"}, method = RequestMethod.POST)
	@ApiOperation(value = "ajax图片上传接口", notes = "上传图片,form表单请求方式限定POST，enctype必须是multipart/form-data", httpMethod = "POST")
	public ObjectModel ajaxProductImage(MultipartFile productImage,
	                                    HttpServletRequest request) {
		ObjectModel objectModel = new ObjectModel();
		//提取UUID + 上传图片的后缀.jpeg
		/* 返回的图片url路径 */
		String saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(Objects.requireNonNull(productImage.getOriginalFilename()));
		//得到项目中图片存储对路径
		final String outPath = System.getProperty("user.dir") + "/Theback-end/target/classes/static/productImg";
		//转存
		final String imageOutPath = outPath + File.separator + saveFileName;
		try {
			productImage.transferTo(new File(imageOutPath));
			objectModel.setObject(saveFileName);
			return objectModel;
		} catch (IOException e) {
			e.printStackTrace();
			objectModel.setRequestServiceStatus("failed");
			return objectModel;
		}
	}

	@ApiOperation(value = "获取所有商品的种类列表")
	@RequestMapping(value = {"/type.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ObjectModel getProductTypeList() {
		final List<String> allProductType = this.productService.getAllProductType();
		return new ObjectModel(allProductType);
	}

	@ApiOperation(value = "通过类型获取商品信息", notes = "如果什么都不传默认获取到全部商品")
	@RequestMapping(value = {"/productByType.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ObjectModel getProductByType(@RequestParam(name = "type", defaultValue = "") String type) {
		List<Product> products;
		if ("".equals(type)) {
			products = this.productService.findAllProducts();
		} else {
			products = this.productService.findProductByType(type);
		}
		final ObjectModel objectModel = new ObjectModel();
		if (products.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(products);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/ajaxNamePrompt.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ObjectModel fastFindProduct(String likeName) {
		final List<Product> products = this.productService.fuzzyQueryProduct(likeName);
		final ObjectModel objectModel = new ObjectModel();
		if (products.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			objectModel.setObject(products);
		}
		return objectModel;
	}


}
