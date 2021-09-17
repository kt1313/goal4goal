package pl.com.k1313.goal4goal.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class PlayerTask {

    private String description;

    public PlayerTask(String description) {
        this.description="Biegaj jak kon." ;
    }

    @Override
    public String toString() {
        return description;
    }
}
