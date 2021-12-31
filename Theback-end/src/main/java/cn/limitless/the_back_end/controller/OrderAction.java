package cn.limitless.the_back_end.controller;

import cn.limitless.the_back_end.entity.Order;
import cn.limitless.the_back_end.entity.OrderItem;
import cn.limitless.the_back_end.entity.Product;
import cn.limitless.the_back_end.entity.User;
import cn.limitless.the_back_end.model.ObjectModel;
import cn.limitless.the_back_end.model.OrderItemModel;
import cn.limitless.the_back_end.service.OrderService;
import cn.limitless.the_back_end.service.ProductService;
import cn.limitless.the_back_end.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
@CrossOrigin(value = "*")
@Api(value = "订单相关接口，尚未完成")
public class OrderAction {
	private static final int PAGE_SHOW_SIZE = 8;

	private final ProductService productService;
	private final OrderService orderService;
	private final UserService userService;

	@Autowired
	public OrderAction(ProductService productService, OrderService orderService, UserService userService) {
		this.productService = productService;
		this.orderService = orderService;
		this.userService = userService;
	}

	@RequestMapping(value = {"/makeOrder.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "生成订单", notes = "先使用订单项接口来添加项目，传入当前用户的信息即可，后期若加入token验证，则同时需要传回token")
	public ObjectModel makeOrder(User user, HttpServletRequest httpServletRequest) {
		final ServletContext servletContext = httpServletRequest.getServletContext();
		final List orderItems = (List) servletContext.getAttribute(user.getUserId());
		final ObjectModel objectModel = new ObjectModel();
		if (orderItems == null || orderItems.size() == 0) {
			objectModel.setRequestServiceStatus("failed");
		} else {
			final boolean b = this.orderService.makeOrder(user.getUserId(), orderItems);
			if (b) {
				servletContext.setAttribute(user.getUserId(), null);
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
	public ObjectModel userOrders(@ApiParam(required = true) @RequestParam(name = "userId") String userId) {
		final List<Order> orders = this.orderService.queryOrderByCustomerId(userId);
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
		final ServletContext servletContext = httpServletRequest.getServletContext();
		final String userId = user.getUserId();
		final boolean productAdequate = this.productService.isProductAdequate(productId);
		final ObjectModel objectModel = new ObjectModel();
		if (productAdequate) {
			List<OrderItem> attribute = (List) servletContext.getAttribute(userId);
			if (attribute == null) {
				attribute = new ArrayList<OrderItem>();
			}
			final OrderItem orderItem = new OrderItem();
			orderItem.setCupType(cupType);
			orderItem.setItemNum(itemNum);
			orderItem.setProductId(productId);
			attribute.add(orderItem);
			servletContext.setAttribute(userId, attribute);
		} else {
			objectModel.setRequestServiceStatus("failed");
		}
		return objectModel;
	}

	@RequestMapping(value = {"/dropItem.do"}, method = {RequestMethod.DELETE})
	@ApiOperation(value = "移除当前订单项目内容", notes = "从当前订单列表中移除某一个条目")
	public ObjectModel dropOrderItemFromOrder(String cupType, Integer itemNum, Integer productId, User user, HttpServletRequest httpServletRequest) {
		final ObjectModel objectModel = new ObjectModel();
		final ServletContext servletContext = httpServletRequest.getServletContext();
		final List<OrderItem> orderItems = (List<OrderItem>) servletContext.getAttribute(user.getUserId());
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
	public ObjectModel orderConfirmation(@ApiParam(value = "用户信息，后期改为token如果来得及的话") String userId, HttpServletRequest httpServletRequest) {
		System.out.println(userId);
		final ServletContext servletContext = httpServletRequest.getServletContext();
		final List<OrderItem> orderItems = (List) servletContext.getAttribute(userId);
		final ArrayList<OrderItemModel> orderItemModels = new ArrayList<>();
		if (orderItems != null) {
			for (OrderItem orderItem : orderItems) {
				System.out.println(orderItem);
				final Product product = this.productService.findProductById(orderItem.getProductId());
				final OrderItemModel orderItemModel = new OrderItemModel(product.getProductImageId(), product.getProductName(), orderItem.getItemNum(), product.getProductPrice());
				orderItemModels.add(orderItemModel);
			}
		}
		return new ObjectModel(orderItemModels);
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


	@RequestMapping(value = {"/spiltOrders.do"}, method = {RequestMethod.GET})
	@ApiOperation(value = "订单分页接口", notes = "传入pageNum，不传默认为1")
	public ObjectModel spiltOrders(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {
		final PageInfo<Order> orderPageInfo = this.orderService.spiltOrders(pageNum, OrderAction.PAGE_SHOW_SIZE);
		final List<Order> list = orderPageInfo.getList();
		for (Order order : list) {
			final String orderCustomerId = order.getOrderCustomerId();
			order.setOrderCustomerId(this.userService.findUserById(orderCustomerId).getUserName());
		}
		final ObjectModel objectModel = new ObjectModel();
		if (orderPageInfo == null) {
			objectModel.error();
		} else {
			objectModel.setObject(orderPageInfo);
		}
		return objectModel;
	}

	@RequestMapping(value = {"/updateInfo.do"}, method = {RequestMethod.GET})
	public ObjectModel updateOrderInfo(String orderId, Boolean isFinish, Boolean isPay) {
		final Order order = new Order();
		order.setOrderIsFinish(isFinish);
		order.setOrderIsPay(isPay);
		final boolean b = this.orderService.updateOrder(orderId, order);
		final ObjectModel objectModel = new ObjectModel();
		if (!b) {
			objectModel.error();
		}
		return objectModel;
	}
}
