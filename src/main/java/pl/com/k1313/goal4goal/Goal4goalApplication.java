package pl.com.k1313.goal4goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.com.k1313.goal4goal.domain.match.Match;
import pl.com.k1313.goal4goal.domain.match.MatchRepository;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@ImportResource("classpath:config/spring-config.xml")
//@PropertySource("classpath:playerRepository.properties")
@EnableScheduling
public class Goal4goalApplication {

    public static void main(String[] args) {


//        MatchTeam matchTeam1 = new MatchTeam("Tres Tigres", 10, 20, 30, 50);
//        MatchTeam matchTeam2 = new MatchTeam("Cream Team FC", 20, 30, 40, 60);
//        List<MatchTeam> matchTeamList = new ArrayList<MatchTeam>(Arrays.asList(matchTeam1, matchTeam2));
//        Match testMatch = new Match(matchTeamList, true);
//        matchRepository.save(testMatch);
        SpringApplication.run(Goal4goalApplication.class, args);
        System.out.println();

    }

}
