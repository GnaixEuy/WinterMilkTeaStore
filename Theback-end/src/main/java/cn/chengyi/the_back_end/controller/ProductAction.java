package cn.chengyi.the_back_end.controller;

import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.model.ObjectModel;
import cn.chengyi.the_back_end.service.ProductService;
import cn.chengyi.the_back_end.utils.FileNameUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	@ApiOperation(value = "商品删除", notes = "传入productId或productName都可以，当二者都不存在或参数信息错误时返回状态failed，业务成功则返回success状态码")
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

	/**
	 * 商品上传图片请求
	 * @param productImage 图片binary流
	 * @param request httpServletRequest
	 * @return 对象状态包装的文件存储uuid路径
	 */
	@RequestMapping(value = {"/ajaxProductImage.do"},method = RequestMethod.POST)
	@ApiOperation(value = "ajax图片上传接口", notes = "上传图片,form表单请求方式限定POST，enctype必须是multipart/form-data",httpMethod = "POST")
	public ObjectModel ajaxProductImage(MultipartFile productImage, HttpServletRequest request){
		//提取UUID + 上传图片的后缀.jpeg
		/*
		  返回的图片url路径
		 */
		String saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(Objects.requireNonNull(productImage.getOriginalFilename()));
		//得到项目中图片存储对路径
		final String outPath = System.getProperty("user.dir") + "/Theback-end/target/classes/static/product_img";
		//转存
		final String imageOutPath = outPath + File.separator + saveFileName;

		try {
			productImage.transferTo(new File(imageOutPath));
			return new ObjectModel(saveFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final ObjectModel objectModel = new ObjectModel();
		objectModel.setRequestServiceStatus("failed");
		return objectModel;
	}


}
