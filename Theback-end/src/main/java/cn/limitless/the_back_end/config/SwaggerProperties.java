package cn.limitless.the_back_end.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>SwaggerProperties配置文件实体类</p>
 * @author GnaixEuy
 * @date 2021/12/22
 * @see <a href='https://github.com/GnaixEuy'> GnaixEuy的GitHub </a>
 */
@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

	/**
	 * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
	 */
	private Boolean enable;

	/**
	 * 项目应用名
	 */
	private String applicationName;

	/**
	 * 项目版本信息
	 */
	private String applicationVersion;

	/**
	 * 项目描述信息
	 */
	private String applicationDescription;

	/**
	 * 接口调试地址
	 */
	private String tryHost;

}


