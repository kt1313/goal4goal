package pl.com.k1313.goal4goal.domain.team;

import org.springframework.stereotype.Component;


@Component
public class MatchTeam {


    private int attack;
    private int midfield;
    private int defence;

    private int goalkeeperSkill;

    public MatchTeam(){}

    public MatchTeam(int attack, int midfield, int defence, int goalkeeperSkill){

        this.attack=attack;
        this.midfield=midfield;
        this.defence=defence;
        this.goalkeeperSkill=goalkeeperSkill;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMidfield() {
        return midfield;
    }

    public void setMidfield(int midfield) {
        this.midfield = midfield;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getGoalkeeperSkill() {
        return goalkeeperSkill;
    }

    public void setGoalkeeperSkill(int goalkeeperSkill) {
        this.goalkeeperSkill = goalkeeperSkill;
    }

    @Override
    public String toString() {
        return "MatchTeam{" +
                "attack=" + attack +
                ", midfield=" + midfield +
                ", defence=" + defence +
                ", goalkeeperSkill=" + goalkeeperSkill +
                '}';
    }
}
