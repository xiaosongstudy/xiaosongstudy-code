package life.hopeurl.redistemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("life.hopeurl.redistemplate.mapper")
public class RedisTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisTemplateApplication.class, args);
	}

}
