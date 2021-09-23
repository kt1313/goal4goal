package pl.com.k1313.goal4goal.domain.repository;

import jdk.jshell.spi.ExecutionControl;
import pl.com.k1313.goal4goal.domain.Player;

import java.util.Collection;

public interface TeamRepository {

    void setUpGameSquad() throws ExecutionControl.NotImplementedException;



    Collection<Player> getAllGamePlayers();

    Integer getTotalDefence() throws ExecutionControl.NotImplementedException;


    Integer getTeamPlaymaking() throws ExecutionControl.NotImplementedException;


    Integer getTeamAttacking() throws ExecutionControl.NotImplementedException;


    Integer getTeamCounterAttacking() throws ExecutionControl.NotImplementedException;


    Integer getTeamSelfConfidence() throws ExecutionControl.NotImplementedException;


    Integer getTeamTactics() throws ExecutionControl.NotImplementedException;


}
