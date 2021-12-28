package cn.limitless.the_back_end.service.impl;

import cn.limitless.the_back_end.dao.OrderDao;
import cn.limitless.the_back_end.dao.OrderItemDao;
import cn.limitless.the_back_end.dao.ProductDao;
import cn.limitless.the_back_end.entity.Order;
import cn.limitless.the_back_end.entity.OrderItem;
import cn.limitless.the_back_end.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/25
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderDao orderDao;
	private final OrderItemDao orderItemDao;
	private final ProductDao productDao;

	public OrderServiceImpl(OrderDao orderDao, OrderItemDao orderItemDao, ProductDao productDao) {
		this.orderDao = orderDao;
		this.orderItemDao = orderItemDao;
		this.productDao = productDao;
	}


	/**
	 * 制作订单
	 *
	 * @param customerId 下单用户的id
	 * @param orderItems 详细订单对象
	 * @return 返回是否成功
	 */
	@Override
	public boolean makeOrder(String customerId, List<OrderItem> orderItems) {
		final Date orderCreateDateTime = new Date();
		final String orderId = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(orderCreateDateTime) +
				customerId;
		Double orderPrice = 0.0;
		int itemFlag = 0;
		for (OrderItem orderItem : orderItems) {
			orderItem.setItemId(orderId + itemFlag);
			orderItem.setOrderId(orderId);
			orderPrice += this.productDao.selectProductById(orderItem.getProductId()).getProductPrice();
			switch (orderItem.getCupType()) {
				case "超大杯":
					orderPrice += 4.0;
					break;
				case "大杯":
					orderPrice += 2.0;
				default:
					break;
			}
		}
		final Order order = new Order(orderId, customerId, orderPrice, false, -1.0, null, false, orderCreateDateTime, orderItems);
		final int ret = this.orderDao.insertOrder(order);
		for (OrderItem orderItem : orderItems) {
			final int i = this.orderItemDao.insertOrderItem(orderItem);
			if (i == 0) {
				try {
					throw new Exception("植入详情信息错误");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return ret == 1;
	}

	/**
	 * 更新订单，一般就是更新订单状态
	 *
	 * @param orderId 订单编号
	 * @param order   订单改动部分信息
	 * @return 返回是否成功
	 */
	@Override
	public boolean updateOrder(String orderId, Order order) {
		final Order oldOrder = this.orderDao.selectOrderById(orderId);
		if (order.getOrderIsPay() != null) {
			oldOrder.setOrderIsPay(order.getOrderIsPay());
		}
		if (order.getOrderIsFinish() != null) {
			oldOrder.setOrderIsFinish(order.getOrderIsFinish());
		}
		if (order.getOrderCreateDateTime() != null) {
			oldOrder.setOrderCreateDateTime(order.getOrderCreateDateTime());
		}
		if (order.getOrderRealPay() != null) {
			oldOrder.setOrderRealPay(order.getOrderRealPay());
		}
		if (order.getOrderPayDateTime() != null) {
			oldOrder.setOrderPayDateTime(order.getOrderPayDateTime());
		}
		if (order.getOrderPrice() != null) {
			oldOrder.setOrderPrice(order.getOrderPrice());
		}
		if (order.getOrderItemList() != null && order.getOrderItemList().size() > 0) {
			oldOrder.setOrderItemList(order.getOrderItemList());
		}
		return this.orderDao.updateOrder(oldOrder) == 1;
	}

	/**
	 * 通过订单编号删除订单
	 *
	 * @param id 订单id
	 * @return 返回是否成功
	 */
	@Override
	public boolean deleteOrderByOrderId(String id) {
		final int ret = this.orderDao.deleteOrder(id);
		final int ret2 = this.orderItemDao.deleteOrderItemByOrderId(id);
		return ret == 1 && ret2 > 0;
	}

	/**
	 * 通过订单编号查询订单信息
	 *
	 * @param id 订单编号
	 * @return 返回订单对象
	 */
	@Override
	public Order queryOrderByOrderId(String id) {
		final Order order = this.orderDao.selectOrderById(id);
		final List<OrderItem> orderItems = this.orderItemDao.selectOrderItemByOrderId(order.getOrderId());
		order.setOrderItemList(orderItems);
		return order;
	}

	/**
	 * 通过用户编号查询订单信息
	 *
	 * @param id 用户id
	 * @return 返回订单对象集合
	 */
	@Override
	public List<Order> queryOrderByCustomerId(String id) {
		final List<Order> orders = this.orderDao.selectOrderByCustomerId(id);
		for (Order order : orders) {
			order.setOrderItemList(this.orderItemDao.selectOrderItemByOrderId(order.getOrderId()));
		}
		return orders;
	}

	/**
	 * 查询所有订单
	 *
	 * @return 返回订单对象集合
	 */
	@Override
	public List<Order> queryOrders() {
		final List<Order> orders = this.orderDao.selectOrders();
		for (Order order : orders) {
			order.setOrderItemList(this.orderItemDao.selectOrderItemByOrderId(order.getOrderId()));
		}
		return orders;
	}

	/**
	 * 分页查询orders
	 *
	 * @param pageNum  请求的页数
	 * @param pageSize 页面的最大条数
	 * @return 分页后的Order集合
	 */
	@Override
	public PageInfo<Order> pageOrders(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		final List<Order> orders = this.orderDao.selectOrders();
		for (Order order : orders) {
			order.setOrderItemList(this.orderItemDao.selectOrderItemByOrderId(order.getOrderId()));
		}
		return new PageInfo<>(orders);
	}
}
