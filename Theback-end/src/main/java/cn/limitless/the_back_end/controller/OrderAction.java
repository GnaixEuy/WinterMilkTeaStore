package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Order;
import cn.limitless.the_back_end.entity.OrderItem;
import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.OrderService;
import cn.limitless.the_back_end.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/25
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/order"})
@Api(value = "订单相关接口，尚未完成")
public class OrderAction {
	private final ProductService productService;
	private final OrderService orderService;

	@Autowired
	public OrderAction(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}

	@RequestMapping(value = {"/makeOrder.do"}, method = {RequestMethod.GET})
	public ObjectModel makeOrder(User user, HttpServletRequest httpServletRequest) {
		final List orderItems = (List) httpServletRequest.getSession().getAttribute(user.getUserId());
		final ObjectModel objectModel = new ObjectModel();
		if (orderItems == null || orderItems.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			final boolean b = this.orderService.makeOrder(user.getUserId(), orderItems);
			if (!b) {
				objectModel.setRequestServiceStatus("failed");
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/orders.do"}, method = {RequestMethod.GET})
	public ObjectModel queryAllOrders() {
		final List<Order> orders = this.orderService.queryOrders();
		return new ObjectModel(orders);
	}

	@RequestMapping(value = {"/orderByUser.do"}, method = {RequestMethod.GET})
	public ObjectModel userOrders(User user) {
		final List<Order> orders = this.orderService.queryOrderByCustomerId(user.getUserId());
		return new ObjectModel(orders);
	}

	@RequestMapping(value = {"/delete.do"}, method = {RequestMethod.DELETE})
	public ObjectModel deleteOrder(User user, String id) {
		final boolean b = this.orderService.deleteOrderByOrderId(id);
		final ObjectModel objectModel = new ObjectModel();
		if (!b) {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}


	@RequestMapping(value = {"/addProductToOrder.do"}, method = {RequestMethod.GET})
	public ObjectModel addOrderItemToOrder(OrderItem orderItem, User user, HttpServletRequest httpServletRequest) {
		final HttpSession session = httpServletRequest.getSession();
		final String userId = user.getUserId();
		final boolean productAdequate = this.productService.isProductAdequate(orderItem.getProductId());
		final ObjectModel objectModel = new ObjectModel();
		if (productAdequate) {
			List attribute = (List) session.getAttribute(userId);
			if (attribute == null) {
				attribute = new ArrayList<OrderItem>();
			}
			attribute.add(orderItem);
			session.setAttribute(userId, attribute);
		} else {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

}
