package pl.com.k1313.goal4goal.domain.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerRepository;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;
import pl.com.k1313.goal4goal.domain.team.MatchTeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamRepository;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import java.util.List;
import java.util.Random;

@Service
public class MatchService {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private MatchTeamRepository matchTeamRepository;
    @Autowired
    private MatchRepository matchRepository;


    public MatchTeam createDefaultOppTeam() {
        MatchTeam defaultOppTeam = new MatchTeam("Cream Team FC", 30, 40, 50, 60);
        System.out.println("MatchService, createDefaultOppTeam, defTeam: " + defaultOppTeam.toString());
        this.matchTeamRepository.save(defaultOppTeam);
        return defaultOppTeam;
    }

    //w przyszłości będzie podawane jako parametr nickname usera
    public MatchTeam createUserTeam() {

        List<Integer> userTeamValues = this.teamService.calculateFirst11FormationsValues();
        Integer userGoalkeeperSkillInt = getGoalkeeperSkills();

        MatchTeam userTeam = new MatchTeam(
                //tu bedzie z repository UserRepo wziete teamName
                "Tres Tigres"
                , userTeamValues.get(0)
                , userTeamValues.get(1)
                , userTeamValues.get(2)
                , userGoalkeeperSkillInt);
        this.matchTeamRepository.save(userTeam);

        System.out.println("MatchServ, creatUserTeam, userTeam: " + userTeam);
        return userTeam;
    }

    public Integer getGoalkeeperSkills() {
        Integer goalkeeperSkills = this.playerRepository.findAll()
                .stream().filter(Player::isFirstSquadPlayer)
                .filter(player -> player.getPosition().equals(Position.GK))
                .findFirst().get().getGoalkeeping();

        return goalkeeperSkills;
    }

    public void handleMatchEngine(Match match) {
        List<MatchTeam> matchTeamList = match.getMatchTeams();
        MatchTeam hostTeam = matchTeamList.get(0);
        MatchTeam guestTeam = matchTeamList.get(1);
        MatchTeam teamOnOpportunity;
        MatchTeam teamInDefence;
        int matchTime = 0;
        boolean matchInProgress = true;
        while (matchInProgress) {
            matchTime++;
            teamOnOpportunity = ballPossesionCheckOut(hostTeam, guestTeam);
            if (teamOnOpportunity.equals(hostTeam)) {
                teamInDefence = guestTeam;
            } else {
                teamInDefence = hostTeam
            }
            if (opportunitySucceed()) {
                if (compareAttackDefence(teamOnOpportunity, hostTeam, guestTeam)) {
                    int forwarderAttack = getForwarderAttack(teamOnOpportunity, hostTeam, guestTeam);
                    if (forwardScoresVsGoalkeeper(teamInDefence.getGoalkeeperSkill(), forwarderAttack)) {
                        methodScore();
                    }
                }
                plus methodMatchCommentary ();
                if (matchTime > 90) {
                    matchInProgress = false;
                }
            } else drawCAttackChance();

        }
    }

    //sprawdza posiadanie, na jego podstawie losuje kto ma akcję
    private MatchTeam ballPossesionCheckOut(MatchTeam hostTeam, MatchTeam guestTeam) {
        Random random = new Random();
        int chance;
        int totalMidfield = hostTeam.getMidfield() + guestTeam.getMidfield();
        int hostTeamMatchMid = hostTeam.getMidfield() / totalMidfield * 100;
        int guestTeamMatchMid = guestTeam.getMidfield() / totalMidfield * 100;
        chance = random.nextInt(100) + 1;
        if (chance >= hostTeamMatchMid) {
            return hostTeam;
        } else {
            return guestTeam;
        }
    }

    //tu można regulować ilość akcji
    private boolean opportunitySucceed() {
        Random random = new Random();
        int succeed = random.nextInt(2);
        if (succeed == 0) {
            return true;
        } else {
            return false;
        }
    }

    //sparawdza czy akcja się udała porównując atak do defensywy
    private boolean compareAttackDefence(MatchTeam teamOnOpportunity, MatchTeam hostTeam, MatchTeam guestTeam) {
        Random random = new Random();
        boolean attackSucceed = false;
        if (teamOnOpportunity.equals(hostTeam)) {
            int sumAttackDefence = hostTeam.getAttack() + guestTeam.getDefence();
            int attackPart = hostTeam.getAttack() / sumAttackDefence * 100;
            int defPart = guestTeam.getDefence() / sumAttackDefence * 100;
            int succeed = random.nextInt(100) + 1;
            if (succeed <= attackPart) {
                attackSucceed = true;
            } else {
                attackSucceed = false;
            }
        } else {
            if (teamOnOpportunity.equals(guestTeam)) {
                int sumAttackDefence = guestTeam.getAttack() + hostTeam.getDefence();
                int attackPart = guestTeam.getAttack() / sumAttackDefence * 100;
                int defPart = hostTeam.getDefence() / sumAttackDefence * 100;
                int succeed = random.nextInt(100) + 1;
                if (succeed <= attackPart) {
                    attackSucceed = true;
                } else {
                    attackSucceed = false;
                }
            } else {
                System.out.println("Atakujacy to nie host ani guest");
            }
        }
        return attackSucceed;
    }

    //musi zebrac liste zawodnikow, ktorzy sa napastnikami (poki co tylko napastnicy)
    private Integer getForwarderAttack(MatchTeam attackingTeam, MatchTeam hostTeam, MatchTeam guestTeam) {
        int strikerAttack = 0;
        if (attackingTeam.equals(hostTeam)) {
            strikerAttack = this.playerRepository.findAll().stream()
                    .filter(Player::isFirstSquadPlayer)
                    .filter(player -> player.getPosition().equals(Position.RF))
                    .findFirst().get().getAttacking();
        } else if (attackingTeam.equals(guestTeam)) {
            //tu zakladamy sredni atak defaultowego zespołu
            strikerAttack = 60;
        } else {
            System.out.println("Atakujacy to nie host ani guest");
        }
        return strikerAttack;
    }

    private boolean forwardScoresVsGoalkeeper(Integer goalkeeperSkill, int strikerAttack) {
        Random random = new Random();
        int goalChance = goalkeeperSkill + strikerAttack;
        int goal = random.nextInt(goalChance);
        if (goal > goalkeeperSkill) {
            return true;
        } else {
            return false;
        }
    }
}