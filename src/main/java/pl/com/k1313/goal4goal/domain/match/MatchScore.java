package pl.com.k1313.goal4goal.domain.match;

public class MatchScore {
    int hostScore;
    int guestScore;
    boolean penaltyScore;

    public MatchScore(){}

    public MatchScore(int hostScore, int guestScore){
        this.hostScore=hostScore;
        this.guestScore = guestScore;
    }

    public MatchScore(int hostScore, int guestScore, boolean penaltyScore){
        this.hostScore=hostScore;
        this.guestScore = guestScore;
        this.penaltyScore=penaltyScore;
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
        return penaltyScore;
    }

    public void setPenaltyScore(boolean penaltyScore) {
        this.penaltyScore = penaltyScore;
    }
}
