//package pl.com.k1313.goal4goal.domain.team;
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import pl.com.k1313.goal4goal.domain.player.Player;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.List;
//@Data
//@Entity
//public class First11 {
//
////    @Autowired Player player;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long first11_id;
//
//    private Player goalkeeper;
//    private Player rightWinger;
//    private Player rightWingback;
//    private Player centreback;
//    private Player rightCentreback;
//    private Player leftCentreback;
//    private Player leftWingback;
//    private Player centreMidfielderDefending;
//    private Player centreMidfielder;
//    private Player centreMidfielderAttacking;
//    private Player leftWinger;
//    private Player rightForward;
//    private Player centreForward;
//    private Player leftForward;
//
//
//    public First11() {
//    }
//
//    public First11(Player goalkeeper, Player rightWingback, Player rightCentreback, Player centreback,
//                   Player leftCentreback, Player leftWingback, Player rightWinger, Player centreMidfielderDefending,
//                   Player centreMidfielder, Player centreMidfielderAttacking, Player leftWinger,
//                   Player rightForward, Player centreForward, Player leftForward){
//        this.goalkeeper=goalkeeper;
//        this.leftWingback=leftWingback;
//        this.leftCentreback=leftCentreback;
//        this.centreback=centreback;
//        this.rightCentreback=rightCentreback;
//        this.rightWingback=rightWingback;
//        this.rightWinger=rightWinger;
//        this.centreMidfielderDefending=centreMidfielderDefending;
//        this.centreMidfielder=centreMidfielder;
//        this.centreMidfielderAttacking=centreMidfielderAttacking;
//        this.leftWinger=leftWinger;
//        this.rightForward=rightForward;
//        this.centreForward=centreForward;
//        this.leftForward=leftForward;
//    }
//
//    public First11(Player goalkeeper, Player rightWingback, Player rightCentreback,
//                   Player centreback) {
//        this.goalkeeper=goalkeeper;
//        this.rightWingback=rightWingback;
//        this.rightCentreback=rightCentreback;
//        this.centreback=centreback;
//    }
//
//
//    public void setGoalkeeper(Player goalkeeper) {
//        this.goalkeeper = goalkeeper;
//    }
//
//    public void setRightWinger(Player rightWinger) {
//        this.rightWinger = rightWinger;
//    }
//
//    public void setRightWingback(Player rightWingback) {
//        this.rightWingback = rightWingback;
//    }
//
//    public void setCentreBack(Player centreBack) {
//        this.centreback = centreBack;
//    }
//
//    public void setRightCentreBack(Player rightCentreBack) {
//        this.rightCentreback = rightCentreBack;
//    }
//
//    public void setLeftCentreBack(Player leftCentreBack) {
//        this.leftCentreback = leftCentreBack;
//    }
//
//    public void setLeftWingback(Player leftWingback) {
//        this.leftWingback = leftWingback;
//    }
//
//    public void setCentreMidfielderDefending(Player centreMidfielderDefending) {
//        this.centreMidfielderDefending = centreMidfielderDefending;
//    }
//
//    public void setCentreMidfielder(Player centreMidfielder) {
//        this.centreMidfielder = centreMidfielder;
//    }
//
//    public void setCentreMidfielderAttacking(Player centreMidfielderAttacking) {
//        this.centreMidfielderAttacking = centreMidfielderAttacking;
//    }
//
//    public void setLeftWinger(Player leftWinger) {
//        this.leftWinger = leftWinger;
//    }
//
//    public void setRightForward(Player rightForward) {
//        this.rightForward = rightForward;
//    }
//
//    public void setCentreForward(Player centreForward) {
//        this.centreForward = centreForward;
//    }
//
//    public void setLeftForward(Player leftForward) {
//        this.leftForward = leftForward;
//    }
//
//
//
////    public Player getGoalkeeper() {
////        return goalkeeper;
////    }
////
////    public Player getRightWinger() {
////        return rightWinger;
////    }
////
////    public Player getRightWingback() {
////        return rightWingBack;
////    }
////
////    public Player getCentreback() {
////        return centreBack;
////    }
////
////    public Player getRightCentreback() {
////        return rightCentreBack;
////    }
////
////    public Player getLeftCentreback() {
////        return leftCentreBack;
////    }
////
////    public Player getLeftWingback() {
////        return leftWingBack;
////    }
////
////    public Player getCentreMidfielderDefending() {
////        return centreMidfielderDefending;
////    }
////
////    public Player getCentreMidfielder() {
////        return centreMidfielder;
////    }
////
////    public Player getCentreMidfielderAttacking() {
////        return centreMidfielderAttacking;
////    }
////
////    public Player getLeftWinger() {
////        return leftWinger;
////    }
////
////    public Player getRightForward() {
////        return rightForward;
////    }
////
////    public Player getCentreForward() {
////        return centreForward;
////    }
////
////    public Player getLeftForward() {
////        return leftForward;
////    }
//
//    @Override
//    public String toString() {
//        return "First11{" +
//                "goalkeeper=" + goalkeeper +
//                ", rightWinger=" + rightWinger +
//                ", rightWingback=" + rightWingback +
//                ", centreback=" + centreback +
//                ", rightCentreback=" + rightCentreback +
//                ", leftCentreback=" + leftCentreback +
//                ", leftWingback=" + leftWingback +
//                ", centreMidfielderDefending=" + centreMidfielderDefending +
//                ", centreMidfielder=" + centreMidfielder +
//                ", centreMidfielderAttacking=" + centreMidfielderAttacking +
//                ", leftWinger=" + leftWinger +
//                ", rightForward=" + rightForward +
//                ", centreForward=" + centreForward +
//                ", leftForward=" + leftForward +
//                '}';
//    }
//}
