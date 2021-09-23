package pl.com.k1313.goal4goal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.k1313.goal4goal.domain.repository.TeamRepository;

@Service
public class GameService {

    @Autowired
    TeamRepository teamRepository;


    public void getGameSquad(){

    }

    public Integer getTeamDefence(){ return teamRepository.getTotalDefence(); }

    public Integer getTeamPlaymaking(){ return teamRepository.getTeamPlaymaking(); }

    public Integer getTeamAttacking(){  return teamRepository.getTeamAttacking(); }

    public Integer getTeamCounterAttacking(){ return teamRepository.getTeamCounterAttacking(); }

    public Integer getTeamSelfConfidence(){ return teamRepository.getTeamSelfConfidence(); }

    public Integer getTeamTactics(){ return teamRepository.getTeamTactics(); }


}
