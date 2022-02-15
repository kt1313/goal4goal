package pl.com.k1313.goal4goal.domain.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;

@Component
public class Match {

    @Autowired
    private MatchTeam matchTeam;

    public Match() {
    }



    @Override
    public String toString() {
        return "W meczu zagrali: ";}
}

