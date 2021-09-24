package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Repository;
import pl.com.k1313.goal4goal.domain.Player;
import pl.com.k1313.goal4goal.domain.Team;

import java.util.Collection;
import java.util.Map;

public interface TeamRepository {


    Map<Integer,Player> setUpGameSquad() throws ExecutionControl.NotImplementedException;

    Team getTeamName() throws ExecutionControl.NotImplementedException;



    Collection<Player> getAllGamePlayers();

    public Integer getTotalDefence() throws ExecutionControl.NotImplementedException;


    Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException;


    Integer getTeamAttacking() throws ExecutionControl.NotImplementedException;


    Integer getTeamCounterAttacking() throws ExecutionControl.NotImplementedException;


    Integer getTeamSelfConfidence() throws ExecutionControl.NotImplementedException;


    Integer getTeamTactics() throws ExecutionControl.NotImplementedException;

}
