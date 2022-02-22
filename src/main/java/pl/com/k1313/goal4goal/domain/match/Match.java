package pl.com.k1313.goal4goal.domain.match;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.k1313.goal4goal.domain.team.MatchTeam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Setter(value = AccessLevel.NONE)
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    MatchTeam hostTeam;
    MatchTeam guestTeam;

    MatchScore matchScore;

    boolean inProgress;

    public Match() {
    }

    public Match(MatchTeam hostTeam, MatchTeam guestTeam, MatchScore matchScore, boolean inProgress) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        this.matchScore = matchScore;
        this.inProgress = inProgress;
    }


    public MatchTeam getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(MatchTeam hostTeam) {
        this.hostTeam = hostTeam;
    }

    public MatchTeam getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(MatchTeam guestTeam) {
        this.guestTeam = guestTeam;
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", hostTeam=" + hostTeam +
                ", guestTeam=" + guestTeam +
                ", matchScore=" + matchScore +
                '}';
    }
}

