package pl.com.k1313.goal4goal.domain;

public class PlayerTask {

    private String description;

    public PlayerTask(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
