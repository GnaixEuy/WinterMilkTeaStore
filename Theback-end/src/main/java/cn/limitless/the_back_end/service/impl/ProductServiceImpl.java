package cn.limitless.the_back_end.service.impl;

import cn.limitless.the_back_end.dao.ProductDao;
import cn.limitless.the_back_end.entity.Product;
import cn.limitless.the_back_end.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> findAllProducts() {
		final List<Product> allProduct = this.productDao.findAllProduct("product_id asc");
		if (allProduct.size() != 0) {
			return allProduct;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Product findProductById(Integer productId) {
		return this.productDao.selectProductById(productId);
	}

	@Override
	public Product findProductByName(String productName) {
		return this.productDao.selectProductByName(productName);
	}

	/**
	 * 商品名字的模糊查询
	 *
	 * @param likeName 模糊名
	 * @return 返回商品对象集合
	 */
	@Override
	public List<Product> fuzzyQueryProduct(String likeName) {
		return this.productDao.vagueSelectProductByName(likeName);
	}

	@Override
	public List<Product> findProductByType(String productType) {
		return this.productDao.selectProductByType(productType);
	}

	/**
	 * 获取所有商品种类
	 *
	 * @return 返回商品种类list
	 */
	@Override
	public List<String> getAllProductType() {
		return this.productDao.selectProductType();
	}

	@Override
	public boolean addProduct(String productName, String productType, Double productPrice, String productImageId, String[] materialListString) {
		final ArrayList<String> strings = new ArrayList<>(Arrays.asList(materialListString));
		final Product addProduct = new Product(null, productName, productType, productPrice, productImageId, strings);
		final int ret = this.productDao.addProduct(addProduct);
		return ret == 1;
	}

	@Override
	public boolean deleteProduct(Integer productId) {
		int i = 0;
		try {
			i = this.productDao.deleteProduct(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i == 1;
	}

	/**
	 * 通过商品名字删除商品
	 *
	 * @param productName 要删除的商品名称
	 * @return 返回是否删除成功
	 */
	@Override
	public boolean deleteProductByName(String productName) {
		final Product product = this.productDao.selectProductByName(productName);
		if (product == null) {
			return false;
		} else {
			return this.productDao.deleteProduct(product.getProductId()) == 1;
		}
	}

	@Override
	public boolean updateProduct(Integer productId, Product product) {
		Product oldProduct;
		try {
			oldProduct = this.productDao.selectProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (product != null) {
			if (product.getProductName() != null && !"".equals(product.getProductName())) {
				oldProduct.setProductName(product.getProductName());
			}
			if (product.getProductPrice() != null) {
				oldProduct.setProductPrice(product.getProductPrice());
			}
			if (product.getProductType() != null && !"".equals(product.getProductType())) {
				oldProduct.setProductType(product.getProductType());
			}
			if (product.getProductImageId() != null && !"".equals(product.getProductImageId())) {
				oldProduct.setProductImageId(product.getProductImageId());
			}
			if (product.getProductMaterialList() != null && product.getProductMaterialList().size() != 0) {
				oldProduct.setProductMaterialList(product.getProductMaterialList());
			}
			return this.productDao.updateProduct(product) == 1;
		} else {
			return false;
		}
	}

	@Override
	public PageInfo<Product> splitPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		final List<Product> productByIdAsc = this.productDao.findAllProduct("product_id ASC");
		return new PageInfo<>(productByIdAsc);
	}

	/**
	 * 获取所有商品的总数量
	 *
	 * @return int 所有商品数量
	 */
	@Override
	public Integer getAllProductNum() {
		return this.productDao.selectProductNum();
	}
}
