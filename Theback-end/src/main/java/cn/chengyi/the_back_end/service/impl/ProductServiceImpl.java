package cn.chengyi.the_back_end.service.impl;

import cn.chengyi.the_back_end.dao.ProductDao;
import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.service.ProductService;
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
		final List<Product> allProduct = this.productDao.findAllProduct();
		if (allProduct.size() != 0){
			return allProduct;
		}else{
			return new ArrayList<>();
		}
	}

	@Override
	public Product findProductById(Integer productId) {
		final Product product = this.productDao.selectProductById(productId);
		System.out.println(product.getProductName());
		return product;
	}

	@Override
	public Product findProductByName(String productName) {
		return this.productDao.selectProductByName(productName);
	}

	@Override
	public List<Product> findProductByType() {
return null;
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
		return false;
	}

	@Override
	public boolean updateProduct(String productId, Product product) {
		return false;
	}
}
