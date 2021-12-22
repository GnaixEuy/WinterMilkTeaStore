package cn.chengyi.the_back_end.service;

import cn.chengyi.the_back_end.entity.Product;
import com.github.pagehelper.PageInfo;

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

	/**
	 * 查找所有商品，默认以id升序
	 *
	 * @return list 返回以一个以id升序的产品list
	 */
	List<Product> findAllProducts();

	/**
	 * 通过id查找商品信息
	 *
	 * @param productId 商品id
	 * @return 商品对象
	 */
	Product findProductById(Integer productId);

	/**
	 * 通过名字查找商品，商品名同为主键不可重复
	 *
	 * @param productName 商品名
	 * @return 商品对象
	 */
	Product findProductByName(String productName);

	/**
	 * 通过种类查找商品
	 *
	 * @param productType 商品种类
	 * @return list 返回以一个同类型的产品list
	 */
	List<Product> findProductByType(String productType);

	/**
	 * 添加商品 未完成
	 *
	 * @param productName
	 * @param productType
	 * @param price
	 * @param productImageId
	 * @param materialListString
	 * @return
	 */
	boolean addProduct(String productName, String productType, Double price, String productImageId, String[] materialListString);

	/**
	 * 通过商品id删除商品
	 *
	 * @param productId 商品id
	 * @return 返回商品信息
	 */
	boolean deleteProduct(Integer productId);

	/**
	 * 更新商品信息，两个信息分离同步，确保商品信息不丢失
	 *
	 * @param productId 旧的商品id
	 * @param product   改动的商品信息
	 * @return 返回是否成功
	 */
	boolean updateProduct(Integer productId, Product product);

	/**
	 * 分页查询商品
	 *
	 * @param pageNum  查询的页数
	 * @param pageSize 每页有多少条商品数据
	 * @return pageInfo 返回一个pageInfo描述的List集合，当前list内容为处理后的商品数据
	 */
	PageInfo<Product> splitPage(Integer pageNum, Integer pageSize);

}
