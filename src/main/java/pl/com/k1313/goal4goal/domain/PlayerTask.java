package pl.com.k1313.goal4goal.domain;

import org.springframework.stereotype.Component;

//@Component
public class PlayerTask {

    private String description;

    public PlayerTask() {
        this.description="Biegaj jak kon." ;
    }

    @Override
    public String toString() {
        return description;
    }
}
