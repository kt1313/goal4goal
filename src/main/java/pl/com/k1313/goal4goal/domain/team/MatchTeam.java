package pl.com.k1313.goal4goal.domain.team;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
//@Component
public class MatchTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private  String teamName;
    private int attack;
    private int midfield;
    private int defence;

    private int goalkeeperSkill;

    public MatchTeam() {
    }

    public MatchTeam(String teamName, int attack, int midfield, int defence, int goalkeeperSkill) {

        this.teamName=teamName;
        this.attack = attack;
        this.midfield = midfield;
        this.defence = defence;
        this.goalkeeperSkill = goalkeeperSkill;
    }

    public MatchTeam(long id, String teamName, int attack, int midfield, int defence, int goalkeeperSkill) {

        this.id=id;
        this.teamName=teamName;
        this.attack = attack;
        this.midfield = midfield;
        this.defence = defence;
        this.goalkeeperSkill = goalkeeperSkill;
    }
    public String getTeamName() {return teamName;}

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
                "teamName='" + teamName + '\'' +
                ", attack=" + attack +
                ", midfield=" + midfield +
                ", defence=" + defence +
                ", goalkeeperSkill=" + goalkeeperSkill +
                '}';
    }
}
