package cn.chengyi.the_back_end.model;

import cn.chengyi.the_back_end.entity.Product;
import cn.chengyi.the_back_end.utils.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@ToString
public class ObjectModel {

	private Object object;
	private Date dateTime;
	private String requestServiceStatus;

	public ObjectModel() {
		this.dateTime = new Date();
		this.requestServiceStatus = "success";
	}

	public ObjectModel(Object object) {
		this();
		this.object = object;
	}
}
