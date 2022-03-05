package pl.com.k1313.goal4goal.domain.match;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


public class MatchCommentary {

    String matchCommentary;

    public MatchCommentary() {
    }

    public MatchCommentary(String matchCommentary) {
        this.matchCommentary = matchCommentary;
    }

}
