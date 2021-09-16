package pl.com.k1313.goal4goal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import pl.com.k1313.goal4goal.domain.Team;

@SpringBootApplication
@ImportResource("classpath:config/spring-config.xml")
public class Goal4goalApplication {

    public static void main(String[] args) {

         SpringApplication.run(Goal4goalApplication.class, args);


    }

}
