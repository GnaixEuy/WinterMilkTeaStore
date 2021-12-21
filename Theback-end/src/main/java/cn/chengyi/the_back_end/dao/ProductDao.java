package cn.chengyi.the_back_end.dao;

import cn.chengyi.the_back_end.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>商品Dao接口</p>
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface ProductDao {

	Product selectProductById(Integer productId);

	Product selectProductByName();

	/**
	 * 模糊查询
	 * @return product 模糊查询
	 */
	Product vagueSelectProductByName();

	List<Product> findAllProduct();

	int addProduct(Product product);

	int deleteProduct(Product product);

	int updateProduct(Product product);

}
