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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MatchService {

    int matchMinute;
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
//        System.out.println("MatchService, createDefaultOppTeam, defTeam: " + defaultOppTeam.toString());
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

        return userTeam;
    }

    public Integer getGoalkeeperSkills() {
        Integer goalkeeperSkills = this.playerRepository.findAll()
                .stream().filter(Player::isFirstSquadPlayer)
                .filter(player -> player.getPosition().equals(Position.GK))
                .findFirst().get().getGoalkeeping();
        return goalkeeperSkills;
    }
//cały mecz event po evencie z komentarzami
    public List<String> handleMatchEngine(Match match) throws InterruptedException {
        List<MatchTeam> matchTeamList=this.matchRepository
                .findAll().stream()
                .filter(match1 -> match1.isInProgress())
                .findFirst().get().getMatchTeams();

        List<String> matchCommentaryList= new ArrayList<>();

        MatchTeam hostTeam = matchTeamList.get(0);
        MatchTeam guestTeam = matchTeamList.get(1);
        MatchTeam teamOnOpportunity;
        MatchTeam teamInDefence;
        matchMinute= 0;
        boolean matchInProgress = true;
        while (matchInProgress) {
            matchMinute++;
            Thread.sleep(1000);
            teamOnOpportunity = ballPossesionCheckOut(hostTeam, guestTeam);
            if (teamOnOpportunity.equals(hostTeam)) {
                teamInDefence = guestTeam;
            } else {
                teamInDefence = hostTeam;
            }
            //komentarz o posiadaniu pilki, niech losuje tylko co...75%event
            if(commentaryBallPossesion()>2){
            matchCommentary(teamOnOpportunity, 1, matchCommentaryList);}
            if (opportunitySucceed()) {
                //komentarz o zawiązaniu akcji
                matchCommentary(teamOnOpportunity,2, matchCommentaryList);
                //to poniżej jako jedna metoda, bo zastosowanie też do kontrataku
                opportunityEvent(teamOnOpportunity, teamInDefence, hostTeam, guestTeam, matchCommentaryList);
            } else {
                //jesli uda się kontra to wtedy niech sprawdzi szansę na bramkę(opportunityEvent)
                //wrzucimy sztucznie/tymczasowo  poziom kontrataku
                int teamCA=30;
                if(drawCAChance(teamCA)){
                    //komentarz o przejęciu piłki i kontrze
                    matchCommentary(teamInDefence, 3, matchCommentaryList);
                    //TUTAJ UWAGA: celowo zamiana teamInDefence z teamOnOpportunity, bo teraz
                    //broniący sie atakują
                    opportunityEvent(teamInDefence,teamOnOpportunity,hostTeam,guestTeam, matchCommentaryList);
                }
            }
            if (matchMinute > 90) {
                matchInProgress = false;
            }
        }
        System.out.println("Koniec. Wynik meczu: "+ match.getHostScore()+" : "+match.getGuestScore());
        return matchCommentaryList;

    }
    private int commentaryBallPossesion(){
        Random random=new Random();
         int commentarySent=random.nextInt(4);
         return commentarySent;
    }
//nad tym trzeba popracować, żeby zrzucic na templatkę
    private void matchCommentary(MatchTeam team, int typeOfCommentary, List<String> matchCommmentaryList) {
        switch (typeOfCommentary) {
            case 1:
                String commentaryBallPossesion1=matchMinute+"min. Posiadanie piłki po stronie "
                        +team.getTeamName()+System.lineSeparator();
                System.out.println(commentaryBallPossesion1);
                matchCommmentaryList.add(commentaryBallPossesion1);
                break;
            case 2:
                String commentaryCreationChance1=matchMinute+"min. Zawiązała sie akcja zespołu "
                        +team.getTeamName();
                System.out.println(commentaryCreationChance1);
                matchCommmentaryList.add(commentaryCreationChance1);
                break;
            case 3:
                String commentaryCA1=matchMinute+"min. Przejęcie w obronie i kontra w wykonaniu "
                        +team.getTeamName();
                System.out.println(commentaryCA1);
                matchCommmentaryList.add(commentaryCA1);
                break;
            case 4:
                String commentaryGoal1=matchMinute+"min. Gooooooooooooooooooool!!!! Bramka dla "
                        +team.getTeamName();
                System.out.println(commentaryGoal1);
                matchCommmentaryList.add(commentaryGoal1);
                break;
            default:
                System.out.println("Piękna dziś pogoda, nieprawdaż?");
        }
    }

    //metoda dla calej akcji bramkowej
        private void opportunityEvent(MatchTeam teamOnOpportunity
                , MatchTeam teamInDefence, MatchTeam hostTeam
                , MatchTeam guestTeam, List<String> matchCommentaryList)
        {
            if (attackSucceedOverDefence(teamOnOpportunity, hostTeam, guestTeam)) {
//                System.out.println("MatchServ, opportunityEvent, aatackSucceedOverDef ");
                int forwarderAttack = getForwarderAttack(teamOnOpportunity, hostTeam, guestTeam);
                if (forwardScoresVsGoalkeeper(teamInDefence.getGoalkeeperSkill(), forwarderAttack)) {
                    goalEvent(teamOnOpportunity);
                    matchCommentary(teamOnOpportunity, 4, matchCommentaryList);
                    //i tu odsieżyc wynik na stronie/ po kazdym evencie
                    //i dodac methodMatchCommentary()
                }
            }
        }


        //sprawdza posiadanie, na jego podstawie losuje kto ma akcję
        private MatchTeam ballPossesionCheckOut (MatchTeam hostTeam, MatchTeam guestTeam){
            Random random = new Random();
            int chance;
            double totalMidfieldDouble = hostTeam.getMidfield() + guestTeam.getMidfield();
            int totalMidfield=(int)totalMidfieldDouble;

            double hostTeamMidfield=hostTeam.getMidfield();
            double hostTeamMatchMidPartDouble = (hostTeamMidfield/totalMidfield)*100;
            int hostTeamMatchMid=(int)hostTeamMatchMidPartDouble;
//            System.out.println("matchServ,ballPossesionCheckOut, hostTeam: "+hostTeamMatchMid);

            double guestTeamMidfield=guestTeam.getMidfield();
            double guestTeamMatchMidPartDouble = (guestTeamMidfield / totalMidfield) * 100;
            int guestTeamMatchMid=(int)guestTeamMatchMidPartDouble;
//            System.out.println("matchServ,ballPossesionCheckOut, guestTeam: "+guestTeamMatchMid);

            chance = random.nextInt(100) + 1;
            if (chance >= hostTeamMatchMid) {
                return guestTeam;
            } else {
                return hostTeam;
            }
        }

        //tu można regulować ilość akcji
        private boolean opportunitySucceed () {
            Random random = new Random();
            int succeed = random.nextInt(2);
            if (succeed == 0) {
                return true;
            } else {
                return false;
            }
        }

        //sparawdza czy akcja się udała porównując atak do defensywy
        private boolean attackSucceedOverDefence (MatchTeam teamOnOpportunity, MatchTeam hostTeam, MatchTeam
        guestTeam){
            Random random = new Random();
            boolean attackSucceed = false;
            if (teamOnOpportunity.equals(hostTeam)) {
                double sumAttackDefence = hostTeam.getAttack() + guestTeam.getDefence();
                double attackPartDouble =( (hostTeam.getAttack() / sumAttackDefence) * 100);
                int attackPart=(int)attackPartDouble;
                double defPartDouble = ((guestTeam.getDefence() / sumAttackDefence) * 100);
                int defPart=(int)defPartDouble;
                int succeed = random.nextInt(100) + 1;
//                System.out.println("MatchServ, attackSucceedOverDef, random:"+succeed+" attackPart: "
//                        + attackPart+" defPart: "+defPart);
                if (succeed <= attackPart) {
                    attackSucceed = true;
                } else {
                    attackSucceed = false;
                }
            } else {
                if (teamOnOpportunity.equals(guestTeam)) {
                    double sumAttackDefence = guestTeam.getAttack() + hostTeam.getDefence();
                    double attackPartDouble = guestTeam.getAttack() / sumAttackDefence * 100;
                    int attackPart=(int)attackPartDouble;
                    double defPartDouble = hostTeam.getDefence() / sumAttackDefence * 100;
                    int defPart=(int)defPartDouble;
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
        private Integer getForwarderAttack (MatchTeam attackingTeam, MatchTeam hostTeam, MatchTeam guestTeam){
            int strikerAttack = 0;
            if (attackingTeam.equals(hostTeam)) {
                strikerAttack = this.playerRepository.findAll().stream()
                        .filter(Player::isFirstSquadPlayer)
                        .filter(player -> player.getPosition().equals(Position.LF))
                        .findFirst().get().getAttacking();
            } else if (attackingTeam.equals(guestTeam)) {
                //tu zakladamy sredni atak defaultowego zespołu
                strikerAttack = 60;
            } else {
                System.out.println("Atakujacy to nie host ani guest");
            }
            return strikerAttack;
        }

        private boolean forwardScoresVsGoalkeeper (Integer goalkeeperSkill,int strikerAttack){
            Random random = new Random();
            int goalChance = goalkeeperSkill + strikerAttack;
            int goal = random.nextInt(goalChance);
            if ((goal*0.5) > goalkeeperSkill) {
                return true;
            } else {
                return false;
            }
        }

        public void goalEvent (MatchTeam matchTeam){
            // wez z kontrollera
            MatchScore matchScore = new MatchScore();
            Match match = this.matchRepository.findAll().stream().filter(Match::isInProgress).findFirst().get();
//Long matchId=this.matchRepository.findAll().stream().filter(Match::isInProgress).findFirst().get().getId();
            if (matchTeam.equals(match.getMatchTeams().get(0))) {
//                System.out.println("MatchContr, goalScored: host " + match.getHostScore());
                match.setHostScore(match.getHostScore() + 1);
//                System.out.println("MatchContr, goalScored: host " + match.getHostScore());
                System.out.println("Match score: Gospodarze: "+match.getHostScore()+" Goście: "+match.getGuestScore());
            } else if (matchTeam.equals(match.getMatchTeams().get(1))) {
//                System.out.println("MatchContr, goalScored: guest " + match.getGuestScore());
                match.setGuestScore(match.getGuestScore() + 1);
//                System.out.println("MatchContr, goalScored: guest " + match.getGuestScore());
                System.out.println("Match score: Gospodarze: "+match.getHostScore()+" Goście: "+match.getGuestScore());

            } else {
                throw new IllegalArgumentException("Błędny zespół");
            }
            this.matchRepository.save(match);
        }

        private boolean drawCAChance(int teamCA){
            Random random = new Random();
            // musi byc wyliczony poziom kontrataku drużyny
            //zakładamy na potrzeby testu: 30 na 100 max
            if (random.nextInt(100) < teamCA) {
                return true;
            }else return false;
        }
    }