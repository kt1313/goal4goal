package pl.com.k1313.goal4goal.domain.team;

public class Formation {

    private String formationName;
    private  Integer formationLevel;


    public Formation(String formationName, Integer formationLevel) {
        this.formationName = formationName;
        this.formationLevel=formationLevel;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "formationName='" + formationName + '\'' +
                ", formationLevel=" + formationLevel +
                '}';
    }
}
