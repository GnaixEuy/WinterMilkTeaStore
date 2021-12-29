package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Order;
import cn.limitless.the_back_end.entity.OrderItem;
import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.service.OrderService;
import cn.limitless.the_back_end.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@CrossOrigin(value = "*")
public class OrderAction {
	private final ProductService productService;
	private final OrderService orderService;

	@Autowired
	public OrderAction(ProductService productService, OrderService orderService) {
		this.productService = productService;
		this.orderService = orderService;
	}

	@RequestMapping(value = {"/makeOrder.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "生成订单", notes = "先使用订单项接口来添加项目，传入当前用户的信息即可，后期若加入token验证，则同时需要传回token")
	public ObjectModel makeOrder(User user, HttpServletRequest httpServletRequest) {
		final HttpSession session = httpServletRequest.getSession();
		final List orderItems = (List) session.getAttribute(user.getUserId());
		final ObjectModel objectModel = new ObjectModel();
		if (orderItems == null || orderItems.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			final boolean b = this.orderService.makeOrder(user.getUserId(), orderItems);
			if (b) {
				session.setAttribute(user.getUserId(), null);
			} else {
				objectModel.setRequestServiceStatus("failed");
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/orders.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "查询所有订单信息", notes = "无需任何参数")
	public ObjectModel queryAllOrders() {
		final List<Order> orders = this.orderService.queryOrders();
		return new ObjectModel(orders);
	}

	@RequestMapping(value = {"/orderByUser.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "用户订单接口", notes = "传入用户对象，返回对象所有订单信息")
	public ObjectModel userOrders(User user) {
		final List<Order> orders = this.orderService.queryOrderByCustomerId(user.getUserId());
		return new ObjectModel(orders);
	}

	@RequestMapping(value = {"/delete.do"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "删除订单", notes = "传入用户对象和要删除的id，后期需要token")
	@Deprecated
	public ObjectModel deleteOrder(User user, String id) {
		final boolean b = this.orderService.deleteOrderByOrderId(id);
		final ObjectModel objectModel = new ObjectModel();
		if (!b) {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	@RequestMapping(value = {"/addProductToOrder.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "增加订单项接口", notes = "增加订单项后调用生成订单接口")
	public ObjectModel addOrderItemToOrder(String cupType, Integer itemNum, Integer productId, User user, HttpServletRequest httpServletRequest) {
		final HttpSession session = httpServletRequest.getSession();
		final String userId = user.getUserId();
		final boolean productAdequate = this.productService.isProductAdequate(productId);
		final ObjectModel objectModel = new ObjectModel();
		if (productAdequate) {
			List attribute = (List) session.getAttribute(userId);
			if (attribute == null) {
				attribute = new ArrayList<OrderItem>();
			}
			final OrderItem orderItem = new OrderItem();
			orderItem.setCupType(cupType);
			orderItem.setItemNum(itemNum);
			orderItem.setProductId(productId);
			attribute.add(orderItem);
			session.setAttribute(userId, attribute);
		} else {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	@RequestMapping(value = {"/dropItem.do"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "移除当前订单项目内容", notes = "从当前订单列表中移除某一个条目")
	public ObjectModel dropOrderItemFromOrder(String cupType, Integer itemNum, Integer productId, User user, HttpServletRequest httpServletRequest) {
		final ObjectModel objectModel = new ObjectModel();
		final List<OrderItem> orderItems = (List) httpServletRequest.getSession().getAttribute(user.getUserId());
		objectModel.setRequestServiceStatus("failed");
		for (OrderItem orderItem : orderItems) {
			if (Objects.equals(productId, orderItem.getProductId()) && cupType.equals(orderItem.getCupType()) && Objects.equals(orderItem.getItemNum(), itemNum)) {
				final boolean remove = orderItems.remove(orderItem);
				if (!remove) {
					objectModel.setRequestServiceStatus("failed");
				} else {
					objectModel.setRequestServiceStatus("success");
					break;
				}
			}
		}
		return objectModel;
	}

	@RequestMapping(value = {"/orderConfirmation.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "查询当前缓存在服务器的订单条目", notes = "已经加入订单的信息")
	public ObjectModel orderConfirmation(@ApiParam(value = "用户信息，后期改为token如果来得及的话") User user, HttpServletRequest httpServletRequest) {
		final List orderItems = (List) httpServletRequest.getSession().getAttribute(user.getUserId());
		return new ObjectModel(orderItems);
	}

	public ObjectModel updateOrderItemToOrder(String cupType, Integer itemNum, Integer productId, User user, HttpServletRequest httpServletRequest) {
//		final List<OrderItem> orderItems = (List<OrderItem>) httpServletRequest.getSession().getAttribute(user.getUserId());
//		for (OrderItem orderItem : orderItems) {
//			if (orderItem.getCupType().equals(cupType))
//		}
		return null;
	}

	@RequestMapping(value = {"/nums.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "提供查询当前订单总数目的接口", notes = "无需任何参数")
	public ObjectModel getOrdersNum() {
		final ObjectModel objectModel = new ObjectModel();
		final Integer integer = this.orderService.querOrdersNum();
		if (integer < 0) {
			objectModel.error();
		} else {
			objectModel.setObject(integer);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/allPrice.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "获取所有价格")
	public ObjectModel turnover() {
		final ObjectModel objectModel = new ObjectModel();
		final BigDecimal allPrice = this.orderService.getAllPrice();
		if (allPrice == null) {
			objectModel.error();
		} else {
			objectModel.setObject(allPrice.toString());
		}
		return objectModel;
	}


}
