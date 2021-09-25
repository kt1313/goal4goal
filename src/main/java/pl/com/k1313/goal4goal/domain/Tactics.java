package pl.com.k1313.goal4goal.domain;

public class Tactics {

    private String tacticsName;
    private Integer tacticsLevel;




    public String getTacticsName() {
        return tacticsName;
    }

    public void setTacticsName(String tacticsName) {
        this.tacticsName = tacticsName;
    }

    public Integer getTacticsLevel() {
        return tacticsLevel;
    }

    public void setTacticsLevel(Integer tacticsLevel) {
        this.tacticsLevel = tacticsLevel;
    }

    @Override
    public String toString() {
        return "Tactics{" +
                "tacticsName='" + tacticsName + '\'' +
                ", tacticsLevel=" + tacticsLevel +
                '}';
    }
}
