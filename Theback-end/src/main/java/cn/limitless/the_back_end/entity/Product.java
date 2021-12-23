package cn.limitless.the_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *  <p>产品实体类</p>
 * @version 1.0
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

	private Integer productId;
	private String productName;
	private String productType;
	private Double productPrice;
	private String productImageId;
	private List<String> productMaterialList;

	public Product(String productName, String productType, Double productPrice, String productImageId, List<String> productMaterialList) {
		this.productName = productName;
		this.productType = productType;
		this.productPrice = productPrice;
		this.productImageId = productImageId;
		this.productMaterialList = productMaterialList;
	}
}
