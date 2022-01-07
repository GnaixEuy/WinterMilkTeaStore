package cn.limitless.the_back_end.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

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

	private Object object; //data
	private Date dateTime; //去掉
	private String requestServiceStatus;    //code
//	private  Integer code;
//	private  String msg;


	public ObjectModel() {
		this.dateTime = new Date();
		this.requestServiceStatus = "success";
	}

	public ObjectModel(Object object) {
		this();
		this.object = object;
	}

	public ObjectModel(String requestServiceStatus) {
		this.requestServiceStatus = requestServiceStatus;
	}

	public void error() {
		this.requestServiceStatus = "failed";
	}
}
