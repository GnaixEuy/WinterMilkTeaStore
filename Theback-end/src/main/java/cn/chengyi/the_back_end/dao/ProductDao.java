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

	/**
	 * 通过id查找产品
	 * @param productId 产品id
	 * @return product产品
	 */
	Product selectProductById(Integer productId);

	Product selectProductByName(String productName);

	/**
	 * 模糊查询
	 * @param likeName 模糊查询的字符串
	 * @return product 模糊查询
	 */
	Product vagueSelectProductByName(String likeName);

	/**
	 * 查询所有产品
	 * @return list<product>返回所有的产品集合
	 */
	List<Product> findAllProduct();

	/**
	 * 添加产品
	 * @param product 产品实体类
	 * @return 添加的行数
	 */
	int addProduct(Product product);

	/**
	 * 删除产品
	 * @param productId 要删除的产品id
	 * @return 返回删除的行数
	 */
	int deleteProduct(Integer productId);

	/**
	 * 更新产品
	 * @param product 修改后的产品
	 * @return 返回修改的行数
	 */
	int updateProduct(Product product);

}
