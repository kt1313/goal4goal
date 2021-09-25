package pl.com.k1313.goal4goal.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Team {

    private String teamName="Odra Malczyce";

    private String managerName="Klmx1313";

    private int assets=1000000;

    //tu bedzie lista uzywanych taktyk i ich poziom
    private Map<String, Integer> tacticsUsed;


    public Map<String, Integer> getTacticsUsed() {
        return tacticsUsed;
    }

    public void setTacticsUsed(Map<String, Integer> tacticsUsed) {
        this.tacticsUsed = tacticsUsed;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getAssets() {
        return assets;
    }

    public void setAssets(int assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Team: " + getTeamName();
    }
}
