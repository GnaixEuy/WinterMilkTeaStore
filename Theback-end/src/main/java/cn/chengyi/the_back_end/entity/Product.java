package cn.chengyi.the_back_end.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.beans.Transient;
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
//	private String productMaterialListString;
	private List<String> productMaterialList;

	public static List<Material> stringToMaterialList(String productMaterialListString){
		final String[] split = productMaterialListString.split("@");
		final ArrayList<Material> materials = new ArrayList<>();
		for (String s : split) {
			System.out.println(s);
			materials.add(new Material(s));
		}
		return materials;
	}

}
