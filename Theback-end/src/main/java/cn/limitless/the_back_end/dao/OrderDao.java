package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Mapper
public interface OrderDao {

	/**
	 * 创建订单
	 *
	 * @param order 订单实体
	 * @return 返回订单对象
	 */
	int insertOrder(Order order);

	/**
	 * 删除订单
	 *
	 * @param orderId 订单id*
	 * @return 改变的行数
	 */
	int deleteOrder(String orderId);

	/**
	 * 更新订单
	 *
	 * @param order 更新订单实体
	 * @return 改变的函数
	 */
	int updateOrder(Order order);

	/**
	 * 通过id查找订单信息
	 *
	 * @param id 订单id
	 * @return 返回order实体对象
	 */
	Order selectOrderById(String id);

	/**
	 * 查询用户的订单
	 *
	 * @param id 用户id
	 * @return 订单集合
	 */
	List<Order> selectOrderByCustomerId(String id);

	/**
	 * 所有订单
	 *
	 * @return 订单集合
	 */
	List<Order> selectOrders();
}
