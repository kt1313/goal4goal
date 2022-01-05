package pl.com.k1313.goal4goal.domain.team;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Team {

    private String teamName="Odra Malczyce";
    private String managerName="Klmx1313";
    private int assets=1000000;
    //tu bedzie lista uzywanych taktyk i ich poziom
    String[][] first11=new String[5][4];

    public Team() {
   }



    @Override
    public String toString() {
        return "Team: ";
    }
}
