package study.supercoding_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
public class SuperCoding1Application {

    public static void main(String[] args) {
        SpringApplication.run(SuperCoding1Application.class, args);
    }

}
