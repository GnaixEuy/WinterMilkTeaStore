package cn.limitless.the_back_end.dao;

import cn.limitless.the_back_end.entity.OrderItem;
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
public interface OrderItemDao {

	/**
	 * 查询一条订单下的所有订单详细内容
	 *
	 * @return 返回订单内商品的集合
	 */
	List<OrderItem> selectOrderItemByOrderId(String id);

	/**
	 * 插入一条订单项
	 *
	 * @param orderItem 订单项实体
	 * @return 返回影响的行数
	 */
	int insertOrderItem(OrderItem orderItem);

	/**
	 * 删除订单项通过id
	 *
	 * @param id 要删除的订单项的id
	 * @return 返回影响的行数
	 */
	int deleteOrderItemByid(String id);

	/**
	 * 删除该订单内的信息
	 *
	 * @param id 订单id
	 * @return 返回改变的行数
	 */
	int deleteOrderItemByOrderId(String id);

	/**
	 * 更新订单项目实体
	 *
	 * @param orderItem 要更新的订单项实体
	 * @return 返回影响的行数
	 */
	int updateOrderItem(OrderItem orderItem);
}
