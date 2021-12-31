package cn.limitless.the_back_end.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/30
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {
	private String itemImageUrl;
	private String itemName;
	private Integer itemNum;
	private Double itemPrice;
}
