package cn.chengyi.the_back_end;

import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author cy实训项目小组
 */
@SpringBootApplication
public class ThebackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThebackEndApplication.class, args);
	}

	@Bean(name = "pageHelper")
	public PageInterceptor pageHelper() {
		return new PageInterceptor();
	}

}
