package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Product;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.ProductService;
import cn.limitless.the_back_end.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = {"/product"})
@CrossOrigin(value = "*")
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
	@RequestMapping(value = {"/addProduct.do"}, method = {RequestMethod.POST})
	@ApiOperation(value = "增加商品")
	public ObjectModel addProduct(@NotNull @RequestBody Product product) {
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
	@RequestMapping(value = {"/deleteProduct.do"}, method = {RequestMethod.DELETE})
	public ObjectModel deleteProduct(@ApiParam(value = "商品id") @RequestParam(name = "id", required = false) Integer productId,
	                                 @ApiParam(value = "商品name") @RequestParam(name = "name", required = false) String productName) {
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
	@RequestMapping(value = {"/updateProduct.do",}, method = {RequestMethod.POST})
	@ApiOperation(value = "更新商品接口", notes = "商品名称为空时错误")
	public ObjectModel updateProduct(@RequestParam Product product) {
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

	@RequestMapping(value = {"/getProductById.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "通过id获取商品信息")
	public ObjectModel getProductById(@ApiParam(value = "商品id") @RequestParam(name = "id", required = false) Integer id) {
		return new ObjectModel(this.productService.findProductById(id));
	}

	@RequestMapping(value = {"/getAllProduct.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取所有商品信息", notes = "不需要参数")
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

	@RequestMapping(value = {"/ajaxPage.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "ajax分页请求数据", notes = "不传页数page默认为1")
	public ObjectModel ajaxProductPagination(@ApiParam(value = "请求的页面数据") @RequestParam(name = "page", defaultValue = "1") Integer pageNum) {
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
	@RequestMapping(value = {"/type.do"}, method = {RequestMethod.GET})
	public ObjectModel getProductTypeList() {
		final List<String> allProductType = this.productService.getAllProductType();
		return new ObjectModel(allProductType);
	}

	@ApiOperation(value = "通过类型获取商品信息", notes = "如果什么都不传默认获取到全部商品")
	@RequestMapping(value = {"/productByType.do"}, method = {RequestMethod.GET})
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

	@ApiOperation(value = "模糊查询接口", notes = "搜索框提示可以用")
	@RequestMapping(value = {"/ajaxNamePrompt.do"}, method = {RequestMethod.GET})
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

	@RequestMapping(value = "/productNum.do", method = {RequestMethod.GET})
	@ApiOperation(value = "获取商品总数接口")
	public int getProductNum() {
		return this.productService.getAllProductNum();
	}


}
