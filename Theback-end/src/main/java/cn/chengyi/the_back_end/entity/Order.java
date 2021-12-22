package cn.chengyi.the_back_end.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>用户实体类</p>
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
	private List<String> orderProductList;
	private Boolean orderIsPay;
	private Double orderRealPay;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date orderPayDateTime;
	private Boolean orderIsFinish;

}
