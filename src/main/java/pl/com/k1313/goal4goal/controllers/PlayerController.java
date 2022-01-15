package pl.com.k1313.goal4goal.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.k1313.goal4goal.controllers.dto.PlayerContractingDTO;
import pl.com.k1313.goal4goal.controllers.dto.PlayerUpdateDTO;
import pl.com.k1313.goal4goal.domain.player.Player;
import pl.com.k1313.goal4goal.domain.player.PlayerService;
import pl.com.k1313.goal4goal.domain.player.Position;
import pl.com.k1313.goal4goal.domain.team.TeamService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/players")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService service) {
        this.playerService = service;
    }

    @Autowired
    public TeamService teamService;

    //localhost8080://players
    @GetMapping
    public String guests(Model model) {
        model.addAttribute("players",
                this.playerService.findAllPlayers());
        return "players";
    }

    @GetMapping("/hire")
    public String createNewPlayer() {
        return "playerform";
    }

    //obsluga powolan do 11
    //pobiera wszystkie checkboxy o nazwie firstsquadplayer i sprawdza czy tickniete
    //wtedy tworzy pierwsza 11
    //a potem tworzy tabele z wybranymi nazwiskami na odpowiednich pozycjach
    @PostMapping("/firstsquadplayers")
    public String handleFirstSquad(@RequestParam("firstSquadPlayer") List<String> ids, Model model) {

//        np
//        First11 {  Player leftWinger; Player goalkeeper; Player... }
//        uzupełnic odpowiednio
//        potem przesłać do templatki
//        i w templatce już masz tabele
//        czy ułożone odpowiednio dvy
//        i dajesz sobie tylko w komorce w srodku po lewej np. <td th:id="leftwinger" th:value="first11.leftWinger.name"/>
//
        List<Player> firstsquadplayers = new ArrayList<>();
        if (ids != null) {
            for (String idplayer : ids) {
                long l = Long.parseLong(idplayer);
                Player first11Player = this.playerService.getPlayerById(l);
                System.out.println(first11Player.getPosition());
                String first11PlayerPos = String.valueOf(first11Player.getPosition());
                first11Player.setFirstSquadPlayer(true);
                String first11PlayerFirstName = first11Player.getFirstName();
                String first11PlayerLastName = first11Player.getLastName();
                String first11PlayerFullName = first11PlayerFirstName + " "+first11PlayerLastName;
                System.out.println(")o)o)o)o)o)");
                System.out.println(first11PlayerFullName + " "+"ID: "+first11Player.getId()+" " + first11PlayerPos);
                System.out.println(")o)o)o)o)o)");

                if (first11PlayerPos == "Goalkeeper") {
                    Player goalkeeper = first11Player;
                    firstsquadplayers.add(goalkeeper);
                } else if (first11PlayerPos == "RightWingback") {
                    Player rightWingback = first11Player;
                    firstsquadplayers.add(rightWingback);
                } else if (first11PlayerPos == "RightCentreBack") {
                    Player rightCentreBack = first11Player;
                    firstsquadplayers.add(rightCentreBack);
                } else if (first11PlayerPos == "CentreBack") {
                    Player centreBack = first11Player;
                    firstsquadplayers.add(centreBack);
                } else if (first11PlayerPos == "LeftCentreBack") {
                    Player leftCentreBack = first11Player;
                    firstsquadplayers.add(leftCentreBack);
                } else if (first11PlayerPos == "LeftWingback") {
                    Player leftWingback = first11Player;
                    firstsquadplayers.add(leftWingback);
                } else if (first11PlayerPos == "RightWinger") {
                    Player rightWinger = first11Player;
                    firstsquadplayers.add(rightWinger);
                } else if (first11PlayerPos == "CentreMidfielderDefending") {
                    Player centreMidfielderDefending = first11Player;
                    firstsquadplayers.add(centreMidfielderDefending);
                } else if (first11PlayerPos == "CentreMidfielder") {
                    Player centreMidfielder = first11Player;
                    firstsquadplayers.add(centreMidfielder);
                } else if (first11PlayerPos == "CentreMidfielderAttacking") {
                    Player centreMidfielderAttacking = first11Player;
                    firstsquadplayers.add(centreMidfielderAttacking);
                } else if (first11PlayerPos == "LeftWinger") {
                    Player leftWingback = first11Player;
                    firstsquadplayers.add(leftWingback);
                } else if (first11PlayerPos == "RightForward") {
                    Player rightForward = first11Player;
                    firstsquadplayers.add(rightForward);
                } else if (first11PlayerPos == "CentreForward") {
                    Player centreForward = first11Player;
                    firstsquadplayers.add(centreForward);
                } else if (first11PlayerPos == "LeftForward") {
                    Player leftForward = first11Player;
                    firstsquadplayers.add(leftForward);
                }
//                    firstsquadplayers.add(this.playerService.getPlayerById(l));
//                System.out.println(playerService.getPlayerById(l));
            }
            // tworze X dla sprawdzania
            int x = 1;
            for (Player p : firstsquadplayers
            ) {
                System.out.println("poszczególni zawodnicy: " + x);
                System.out.println(p.getFirstName() + " " + p.getLastName()+" "+p.getAttacking());
                x++;
            }
            //obliczanie ataku
            int first111Attack = 0;
            for (Player p : firstsquadplayers
            ) {
                first111Attack += p.getAttacking();
            }
            System.out.println("Suma ataku pierwszej 11: " + first111Attack);
            System.out.println("---------KONIEC-------");
            model.addAttribute("firstsquadplayers", firstsquadplayers);
            String[][] first11FinalTable = this.teamService.setUpFirst11(firstsquadplayers);
            model.addAttribute("first11FinalTable", first11FinalTable);
        }
        return "firstsquadplayers";
    }


    @PostMapping
    public String handleCreateNewPlayer
            (@Valid PlayerContractingDTO playerDTO,
             BindingResult result, Model model) {
        System.out.println(playerDTO);
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "playerform";
        } else {
            this.playerService.createNewPlayer(playerDTO);
            return "redirect:/players";
        }
    }

    @GetMapping("/delete/{id}")
    public String removePlayer(@PathVariable("id") Long id) {
        this.playerService.removeById(id);

        return "redirect:/players";
    }

    @GetMapping("/managePlayer/{id}")
    public String editPlayer(@PathVariable Long id, Model model) {
        Player player = this.playerService.getPlayerById(id);
        model.addAttribute("player", player);
        return "managePlayer";
    }

    @PostMapping("/managePlayer")
    public String editPlayer(PlayerUpdateDTO updatedPlayer) {
        this.playerService.update(updatedPlayer);
        return "redirect:/players";
    }


}
