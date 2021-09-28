package pl.com.k1313.goal4goal.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tactics {

    private int tacticsId;
    private String tacticsName;
    private Integer tacticsLevel;
    private List<String> tacticsNamesList=new ArrayList<>(
            Arrays.asList("Counter-Attack", "Pressing", "Long Ball"));

    public Tactics() {
    }


    public int getTacticsId() {
        return tacticsId;
    }

    public void setTacticsId(int tacticsId) {
        this.tacticsId = tacticsId;
    }

    public List<String> getTacticsNamesList() { return tacticsNamesList;}

    public void setTacticsNamesList(List<String> tacticsNamesList) { this.tacticsNamesList = tacticsNamesList; }

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
