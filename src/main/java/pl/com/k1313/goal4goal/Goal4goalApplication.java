package pl.com.k1313.goal4goal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@ImportResource("classpath:config/spring-config.xml")
//@PropertySource("classpath:playerRepository.properties")
@EnableScheduling
public class Goal4goalApplication {

    public static void main(String[] args) {

         SpringApplication.run(Goal4goalApplication.class, args);
        System.out.println();


    }

}
