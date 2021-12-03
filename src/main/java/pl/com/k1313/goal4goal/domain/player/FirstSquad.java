package pl.com.k1313.goal4goal.domain.player;

public enum FirstSquad {

        YES ("First Squad Player"),
        NO("On The Bench");

        private final String firstSquadEnum;

        FirstSquad(String position){
            this.firstSquadEnum=position;
        }

        public String toString(){
            return this.firstSquadEnum;
        }
    }

