package cn.limitless.the_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>用户实体类</p>
 *
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

	private String orderId;
	private String orderCustomerId;
	private Double orderPrice;
	private Boolean orderIsPay;
	private Double orderRealPay;
	private Date orderPayDateTime;
	private Boolean orderIsFinish;
	private Date orderCreateDateTime;

	private List<OrderItem> orderItemList;

}
