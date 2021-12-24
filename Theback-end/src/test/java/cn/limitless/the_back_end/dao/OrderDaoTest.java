package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/24
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@SpringBootTest
class OrderDaoTest {

	@Autowired
	private OrderDao orderDao;

	@Test
	void insertOrder() {
		String orderId = "test1211";
		String orderCustomerId = "test";
		Double orderPrice = 13.2;
		Boolean orderIsPay = false;
		Double orderRealPay = 0.0;
		Date orderPayDateTime = new Date();
		Boolean orderIsFinish = false;
		Date orderCreatDateTime = new Date();

		final Order order = new Order(orderId, orderCustomerId, orderPrice, orderIsPay, orderRealPay, orderPayDateTime, orderIsFinish, orderCreatDateTime, new ArrayList<>());
		final int i = this.orderDao.insertOrder(order);
		if (i == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}

	}

	@Test
	void deleteOrder() {
		final int i = this.orderDao.deleteOrder("1");
		if (i == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void updateOrder() {
		String orderId = "test";
		String orderCustomerId = "test";
		Double orderPrice = 13.2;
		Boolean orderIsPay = true;
		Double orderRealPay = 6666.6;
		Date orderPayDateTime = new Date();
		Boolean orderIsFinish = true;
		Date orderCreatDateTime = new Date();
		final Order order = this.orderDao.selectOrderById(orderId);
		System.out.println(order);
		order.setOrderIsFinish(orderIsFinish);
		order.setOrderIsPay(orderIsPay);
		order.setOrderRealPay(orderRealPay);
		final int i = this.orderDao.updateOrder(order);
		if (i == 1) {
			System.out.println("------------------");
			System.out.println(order);
			System.out.println("成功");
		} else {
			System.out.println("失败");
		}
	}

	@Test
	void selectOrderByCustomerId() {
		final List<Order> test = this.orderDao.selectOrderByCustomerId("test");
		test.forEach(System.out::println);
	}

	@Test
	void selectOrders() {
		final List<Order> orders = this.orderDao.selectOrders();
		orders.forEach(System.out::println);
	}
}