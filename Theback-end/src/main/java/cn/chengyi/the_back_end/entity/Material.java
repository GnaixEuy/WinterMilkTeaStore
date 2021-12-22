package cn.chengyi.the_back_end.entity;

import lombok.*;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>原料实体类</p>
 * @author GnaixEuy
 * @date 2021/12/21
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Material {

	private String materialName;
	private Integer materialStock;
	private Double materialPrice;

	public Material(String materialName) {
		this.materialName = materialName;
	}
}
