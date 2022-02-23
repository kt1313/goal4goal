package pl.com.k1313.goal4goal.domain.match;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Setter(value = AccessLevel.NONE)
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany
    private List<MatchTeam> matchTeams;

    private int hostScore;
    private int guestScore;
    private boolean isPenaltyScore;
    private boolean inProgress;


    @OneToMany
    List<MatchTeam> matchteams;

    //zlikwiduj klasę i zastosuj metodę, i tak będziesz archiwizował cały mecz
    private MatchScore matchScore(Match match) {
        int hostScore = match.getHostScore();
        int guestScore = match.getGuestScore();
        boolean penaltyScore = match.isPenaltyScore;
        boolean finalScore = match.inProgress;
        return new MatchScore(hostScore, guestScore, penaltyScore, finalScore);
    }


    public Match() {
    }

    public Match(List<MatchTeam> matchTeams, boolean inProgress) {
        this.matchteams = matchTeams;
        this.inProgress = inProgress;
    }

    public List<MatchTeam> getMatchteams() {
        return matchteams;
    }

    public void setMatchteams(List<MatchTeam> matchteams) {
        this.matchteams = matchteams;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public List<MatchTeam> getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(List<MatchTeam> matchTeams) {
        this.matchTeams = matchTeams;
    }

    public int getHostScore() {
        return hostScore;
    }

    public void setHostScore(int hostScore) {
        this.hostScore = hostScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
    }

    public boolean isPenaltyScore() {
        return isPenaltyScore;
    }

    public void setPenaltyScore(boolean penaltyScore) {
        isPenaltyScore = penaltyScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", hostScore=" + hostScore +
                ", guestScore=" + guestScore +
                ", isPenaltyScore=" + isPenaltyScore +
                ", inProgress=" + inProgress +
                ", matchteams=" + matchteams +
                '}';
    }
}


