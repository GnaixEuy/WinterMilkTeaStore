package cn.limitless.the_back_end.service;

import cn.limitless.the_back_end.entity.Order;
import cn.limitless.the_back_end.entity.OrderItem;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/25
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
public interface OrderService {

	/**
	 * 制作订单
	 *
	 * @param customerId 下单用户的id
	 * @param orderItems 详细订单对象
	 * @return 返回是否成功
	 */
	boolean makeOrder(String customerId, List<OrderItem> orderItems);

	/**
	 * 更新订单，一般就是更新订单状态
	 *
	 * @param orderId 订单编号
	 * @param order   订单改动部分信息
	 * @return 返回是否成功
	 */
	boolean updateOrder(String orderId, Order order);

	/**
	 * 通过订单编号删除订单
	 *
	 * @param id 订单id
	 * @return 返回是否成功
	 */
	boolean deleteOrderByOrderId(String id);

	/**
	 * 通过订单编号查询订单信息
	 *
	 * @param id 订单编号
	 * @return 返回订单对象
	 */
	Order queryOrderByOrderId(String id);

	/**
	 * 通过用户编号查询订单信息
	 *
	 * @param id 用户id
	 * @return 返回订单对象集合
	 */
	List<Order> queryOrderByCustomerId(String id);

	/**
	 * 查询所有订单
	 *
	 * @return 返回订单对象集合
	 */
	List<Order> queryOrders();

	/**
	 * 分页查询orders
	 *
	 * @param pageNum  请求的页数
	 * @param pageSize 页面的最大条数
	 * @return 分页后的Order集合
	 */
	PageInfo<Order> pageOrders(Integer pageNum, Integer pageSize);

	/**
	 * 查询所有订单数目
	 *
	 * @return 返回一个long 描述所有订单量总数
	 */
	Integer querOrdersNum();

	/**
	 * 获取所有收入
	 *
	 * @return 返回所有的价格大数据
	 */
	BigDecimal getAllPrice();
}
