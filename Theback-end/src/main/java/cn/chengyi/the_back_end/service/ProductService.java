package cn.chengyi.the_back_end.service;

import cn.chengyi.the_back_end.entity.Product;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>商品服务类</p>
 *
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface ProductService {

	List<Product> findAllProducts();

	Product findProductById(Integer productId);

	Product findProductByName(String productName);

	List<Product> findProductByType(String productType);

	boolean addProduct(String productName, String productType, Double price, String productImageId, String[] materialListString);

	boolean deleteProduct(Integer productId);

	boolean updateProduct(Integer productId, Product product);

	List<Product> splitPage(Integer pageNum, Integer pageSize);

}
