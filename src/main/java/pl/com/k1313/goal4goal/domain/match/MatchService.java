package pl.com.k1313.goal4goal.domain.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import java.util.Collection;
import java.util.List;
//import pl.com.k1313.goal4goal.repository.TeamRepository;

@Service
public class MatchService {

    @Autowired
    private MatchTeam matchTeam;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;

    public MatchTeam createDefaultOppTeam() {
        MatchTeam defaultOppTeam = new MatchTeam(30, 40, 50, 60);
        System.out.println("MatchService, createDefaultOppTeam, defTeam: "+defaultOppTeam.toString());
        return defaultOppTeam;
    }

    //w przyszłości będzie podawane jako parametr nickname usera
    public MatchTeam createUserTeam() {

        List<Integer> userTeamValues = teamService.calculateFirst11FormationsValues();
        String userGoalkeeperSkillString=this.playerRepository.findAll()
                .stream().filter(Player::isFirstSquadPlayer)
                .filter(player -> player.getPosition().equals(Position.GK))
                .toString();
        Integer userGoalkeeperSkill=Integer.valueOf(userGoalkeeperSkillString);

        MatchTeam userTeam = new MatchTeam(userTeamValues.get(0)
                , userTeamValues.get(1), userTeamValues.get(2)
                , userGoalkeeperSkill);
        System.out.println("MatchService, create userTeam, TEAM VALUES: "+userTeam);
        return userTeam;
    }

}
