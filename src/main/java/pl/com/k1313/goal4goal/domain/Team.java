package pl.com.k1313.goal4goal.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Team {

    private String name="Odra Malczyce";

    public Team(){}
@PostConstruct
    public void create(){
        System.out.println("Powstal klub: "+ name);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Zaraz zniszczymy klub: "+ name);
    }

}
