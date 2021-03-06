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

import java.util.*;

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
    @Autowired
    private MatchService matchService;


    public MatchTeam createDefaultOppTeam() {
        MatchTeam defaultOppTeam = new MatchTeam("Cream Team FC", 30, 40, 50, 60);
//        System.out.println("MatchService, createDefaultOppTeam, defTeam: " + defaultOppTeam.toString());
        this.matchTeamRepository.save(defaultOppTeam);
        return defaultOppTeam;
    }

    //tworzenie autoOppTeamu z zawodnikami
    public MatchTeam createAutoOpponent() {

        //TUTAJ zle - najpierw utworz AutoTeam, a potem tylko odczytuj, a nie tworz za kazdym razem
        MatchTeam autoOppTeam=this.teamService.autoCreateOpponentTeam();
        String opTeamName = autoOppTeam.getTeamName();
        int opTeamAttack=autoOppTeam.getAttack();
        int opTeamMidfield=autoOppTeam.getMidfield();
        int opTeamDefense=autoOppTeam.getDefence();
        int opTeamGoalkeeperSkills=autoOppTeam.getGoalkeeperSkill();
//        String opTeamName = this.teamService.autoCreateOpponentTeam().getTeamName();
//        int opTeamAttack = this.teamService.autoCreateOpponentTeam().getAttack();
//        int opTeamMidfield = this.teamService.autoCreateOpponentTeam().getMidfield();
//        int opTeamDefense = this.teamService.autoCreateOpponentTeam().getDefence();
//        int opTeamGoalkeeperSkills = this.teamService.autoCreateOpponentTeam().getGoalkeeperSkill();
//        MatchTeam autoOppTeam = new MatchTeam(opTeamName, opTeamAttack, opTeamMidfield, opTeamDefense, opTeamGoalkeeperSkills);

        System.out.println(autoOppTeam);
        return autoOppTeam;
    }

    //w przysz??o??ci b??dzie podawane jako parametr nickname usera
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

    //ca??y mecz event po evencie z komentarzami
    public HashMap<Integer, String> handleMatchEngine(Match match) throws InterruptedException {
        List<MatchTeam> matchTeamsList = this.matchRepository
                .findAll().stream()
                .filter(Match::isInProgress)
                .findFirst().get().getMatchTeams();
//TUTAJ ZROBIC HashMap  (minutaMeczu, komentarz)
        HashMap<Integer, String> matchCommentaryList = new HashMap<>();

        MatchTeam hostTeam = matchTeamsList.get(0);
        MatchTeam guestTeam = matchTeamsList.get(1);
        MatchTeam teamOnOpportunity;
        MatchTeam teamInDefence;
        matchMinute = 0;
        boolean matchInProgress = true;
        while (matchInProgress) {
            matchMinute++;
            Thread.sleep(100);
            teamOnOpportunity = ballPossesionCheckOut(hostTeam, guestTeam);
            if (teamOnOpportunity.equals(hostTeam)) {
                teamInDefence = guestTeam;
            } else {
                teamInDefence = hostTeam;
            }
            //komentarz o posiadaniu pilki, niech losuje tylko co...75%event
            if (commentaryBallPossesion() > 2) {
                matchCommentary(teamOnOpportunity, 1, matchCommentaryList, matchMinute);
            }
            if (opportunitySucceed()) {
                //komentarz o zawi??zaniu akcji
                matchCommentary(teamOnOpportunity, 2, matchCommentaryList, matchMinute);
                //to poni??ej jako jedna metoda, bo zastosowanie te?? do kontrataku
                opportunityEvent(teamOnOpportunity, teamInDefence, hostTeam, guestTeam, matchCommentaryList);
            } else {
                //jesli uda si?? kontra to wtedy niech sprawdzi szans?? na bramk??(opportunityEvent)
                //wrzucimy sztucznie/tymczasowo  poziom kontrataku
                int teamCA = 30;
                if (randomCAChance(teamCA)) {
                    //komentarz o przej??ciu pi??ki i kontrze
                    matchCommentary(teamInDefence, 3, matchCommentaryList, matchMinute);
                    //TUTAJ UWAGA: celowo zamiana teamInDefence z teamOnOpportunity, bo teraz
                    //broni??cy sie atakuj??
                    opportunityEvent(teamInDefence, teamOnOpportunity, hostTeam, guestTeam, matchCommentaryList);
                }
            }
            if (matchMinute > 90) {
                matchInProgress = false;
            }
        }
        String matchResult = "Koniec meczu. Na tablicy widnieje wynik" + match.getHostScore() + " : " + match.getGuestScore();
        matchCommentaryList.put(matchMinute, matchResult);
        System.out.println("Koniec. Wynik meczu: " + match.getHostScore() + " : " + match.getGuestScore());
        int hostTeamMid = hostTeam.getMidfield();
        int hostTeamAtt = hostTeam.getAttack();
        int hostTeamDef = hostTeam.getDefence();
        int hostTeamGoalkpr = hostTeam.getGoalkeeperSkill();
        int guestTeamMid = guestTeam.getMidfield();
        int guestTeamAtt = guestTeam.getAttack();
        int guestTeamDef = guestTeam.getDefence();
        int guestTeamGoalkpr = guestTeam.getGoalkeeperSkill();
        System.out.println("Stats:  ");
        System.out.println("HostTeam: Midfield: " + hostTeamMid + " Attack: " + hostTeamAtt + " Def: " + hostTeamDef + " Goalkpr: " + hostTeamGoalkpr);
        System.out.println("GuestTeam: Midfield: " + guestTeamMid + " Attack: " + guestTeamAtt + " Def: " + guestTeamDef + " Goalkpr: " + guestTeamGoalkpr);

        return matchCommentaryList;

    }

    private int commentaryBallPossesion() {
        Random random = new Random();
        int commentarySent = random.nextInt(4);
        return commentarySent;
    }

    private void matchCommentary(MatchTeam team, int typeOfCommentary, HashMap<Integer, String> matchCommmentaryList, int matchMinute) {
        switch (typeOfCommentary) {
            case 1:
                String commentaryBallPossesion1 = matchMinute
                        + "min. Uwijaj?? si?? jak mr??wki i wygrali walk?? o pi??k?? w ??rodku pola pi??karze "
                        + team.getTeamName() + "\r\n";
                System.out.println(commentaryBallPossesion1);
                matchCommmentaryList.put(matchMinute, commentaryBallPossesion1);
                break;
            case 2:
                String commentaryCreationChance1 = matchMinute
                        + "min. Ruszy?? teraz na przeciwnika z balem przy nodze grajek zespo??u "
                        + team.getTeamName() + "\r\n";
                System.out.println(commentaryCreationChance1);
                matchCommmentaryList.put(matchMinute, commentaryCreationChance1);
                break;
            case 3:
                String commentaryCA1 = matchMinute
                        + "min. Oni s?? jak stal, nieugi??ci w obronie. Odbi??r i mkn?? z kontr?? jak torpeda zawodnicy "
                        + team.getTeamName() + "\r\n";
                System.out.println(commentaryCA1);
                matchCommmentaryList.put(matchMinute, commentaryCA1);
                break;
            case 4:
                String commentaryGoal1 = matchMinute
                        + "min. Gooooooooooooooooooool!!!! Stadiony ??wiata!!! Bramka dla "
                        + team.getTeamName() + "\r\n";
                System.out.println(commentaryGoal1);
                matchCommmentaryList.put(matchMinute, commentaryGoal1);
                break;
            default:
                System.out.println("Pi??kna dzi?? pogoda, nieprawda???");
        }
    }

    //metoda dla calej akcji bramkowej
    private void opportunityEvent(MatchTeam teamOnOpportunity
            , MatchTeam teamInDefence, MatchTeam hostTeam
            , MatchTeam guestTeam, HashMap<Integer, String> matchCommentaryList) {
        if (attackSucceedOverDefence(teamOnOpportunity, hostTeam, guestTeam)) {
//                System.out.println("MatchServ, opportunityEvent, aatackSucceedOverDef ");
            int forwarderAttack = getForwarderAttack(teamOnOpportunity, hostTeam, guestTeam);
            if (forwardScoresVsGoalkeeper(teamInDefence.getGoalkeeperSkill(), forwarderAttack)) {
                goalEvent(teamOnOpportunity);
                matchCommentary(teamOnOpportunity, 4, matchCommentaryList, matchMinute);
                //i tu odsie??yc wynik na stronie/ po kazdym evencie
                //i dodac methodMatchCommentary()
            }
        }
    }


    //sprawdza posiadanie, na jego podstawie losuje kto ma akcj??
    private MatchTeam ballPossesionCheckOut(MatchTeam hostTeam, MatchTeam guestTeam) {
        Random random = new Random();
        int chance;
        double totalMidfieldDouble = hostTeam.getMidfield() + guestTeam.getMidfield();
        int totalMidfield = (int) totalMidfieldDouble;

        double hostTeamMidfield = hostTeam.getMidfield();
        double hostTeamMatchMidPartDouble = (hostTeamMidfield / totalMidfield) * 100;
        int hostTeamMatchMid = (int) hostTeamMatchMidPartDouble;
//            System.out.println("matchServ,ballPossesionCheckOut, hostTeam: "+hostTeamMatchMid);

        double guestTeamMidfield = guestTeam.getMidfield();
        double guestTeamMatchMidPartDouble = (guestTeamMidfield / totalMidfield) * 100;
        int guestTeamMatchMid = (int) guestTeamMatchMidPartDouble;
//            System.out.println("matchServ,ballPossesionCheckOut, guestTeam: "+guestTeamMatchMid);

        chance = random.nextInt(100) + 1;
        if (chance >= hostTeamMatchMid) {
            return guestTeam;
        } else {
            return hostTeam;
        }
    }

    //tu mo??na regulowa?? ilo???? akcji
    private boolean opportunitySucceed() {
        Random random = new Random();
        int succeed = random.nextInt(2);
        if (succeed == 0) {
            return true;
        } else {
            return false;
        }
    }

    //sparawdza czy akcja si?? uda??a por??wnuj??c atak do defensywy
    private boolean attackSucceedOverDefence(MatchTeam teamOnOpportunity, MatchTeam hostTeam, MatchTeam
            guestTeam) {
        Random random = new Random();
        boolean attackSucceed = false;
        if (teamOnOpportunity.equals(hostTeam)) {
            double sumAttackDefence = hostTeam.getAttack() + guestTeam.getDefence();
            double attackPartDouble = ((hostTeam.getAttack() / sumAttackDefence) * 100);
            int attackPart = (int) attackPartDouble;
            double defPartDouble = ((guestTeam.getDefence() / sumAttackDefence) * 100);
            int defPart = (int) defPartDouble;
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
                int attackPart = (int) attackPartDouble;
                double defPartDouble = hostTeam.getDefence() / sumAttackDefence * 100;
                int defPart = (int) defPartDouble;
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
                    .filter(player -> player.getPosition().equals(Position.LF)
                            || player.getPosition().equals(Position.CF)
                            || player.getPosition().equals(Position.RF))
                    .findFirst().get().getAttacking();
        } else if (attackingTeam.equals(guestTeam)) {
            //tu zakladamy sredni atak defaultowego zespo??u
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
        if ((goal * 0.7) > goalkeeperSkill) {
            return true;
        } else {
            return false;
        }
    }

    public void goalEvent(MatchTeam matchTeam) {
        // wez z kontrollera
        MatchScore matchScore = new MatchScore();
        Match match = this.matchRepository.findAll().stream().filter(Match::isInProgress).findFirst().get();
//Long matchId=this.matchRepository.findAll().stream().filter(Match::isInProgress).findFirst().get().getId();
        if (matchTeam.equals(match.getMatchTeams().get(0))) {
//                System.out.println("MatchContr, goalScored: host " + match.getHostScore());
            match.setHostScore(match.getHostScore() + 1);
//                System.out.println("MatchContr, goalScored: host " + match.getHostScore());
            System.out.println("Match score: Gospodarze: " + match.getHostScore() + " Go??cie: " + match.getGuestScore());
        } else if (matchTeam.equals(match.getMatchTeams().get(1))) {
//                System.out.println("MatchContr, goalScored: guest " + match.getGuestScore());
            match.setGuestScore(match.getGuestScore() + 1);
//                System.out.println("MatchContr, goalScored: guest " + match.getGuestScore());
            System.out.println("Match score: Gospodarze: " + match.getHostScore() + " Go??cie: " + match.getGuestScore());

        } else {
            throw new IllegalArgumentException("B????dny zesp????");
        }
        this.matchRepository.save(match);
    }

    private boolean randomCAChance(int teamCA) {
        Random random = new Random();
        // musi byc wyliczony poziom kontrataku dru??yny
        //zak??adamy na potrzeby testu: 30 na 100 max
        if (random.nextInt(100) < teamCA) {
            return true;
        } else return false;
    }

    public void readDataForHeader() {
        //sprawdz bo ponizej jest skopiowane z Team Serviceu

        this.matchService.createUserTeam();
        this.matchService.createDefaultOppTeam();

        Optional<MatchTeam> hostTeamOpt = this.teamService.findAllMatchTeams().stream()
                .filter(matchTeam1 -> matchTeam1.getTeamName()
                        .equals("Tres Tigres"))
                .findFirst();
        String hostTeamName = hostTeamOpt.get().getTeamName();

        Optional<MatchTeam> guestTeamOpt = this.teamService.findAllMatchTeams()
                .stream().filter(matchTeam -> matchTeam.getTeamName().equals("Cream Team FC"))
                .findFirst();

    }

}
