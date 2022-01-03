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

import javax.validation.Valid;
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

//    @PostMapping("/firstsquadplayers")
//    public String firstsquadplayers(Model model){
//        List<Player> firstsquadplayers=this.playerService.findAllPlayers().stream()
//                .filter(Player::isFirstSquadPlayer)
//                .collect(Collectors.toList());
//        model.addAttribute("firstsquadplayers", firstsquadplayers);
//        System.out.println("?????");
//        System.out.println(firstsquadplayers);
//        System.out.println("?????");
//        return "firstsquadplayers";
//    }

    //obsluga powolan do 11
    //pobiera wszystkie checkboxy o nazwie firstsquadplayer i sprawdza czy tickniete
    //wtedy tworzy pierwsza 11
    @PostMapping("/firstsquadplayers")
    public String handleFirstSquad(@RequestParam("firstSquadPlayer") List<String> ids, Model model) {


        if (ids != null) {
            for (String idplayer : ids) {
                System.out.println("------------------");
                System.out.println(idplayer);//sprawdxzam id playera
                long l = Long.parseLong(idplayer);
                System.out.println(l);
                this.playerService.getPlayerById(l).setFirstSquadPlayer(true);
                System.out.println(playerService.getPlayerById(l));
                System.out.println("*****************");
                System.out.println("?????");
                List<Player> firstsquadplayers = this.playerService.findAllPlayers().stream()
                        .filter(Player::isFirstSquadPlayer)
                        .collect(Collectors.toList());
                System.out.println(firstsquadplayers);
                model.addAttribute("firstsquadplayers", firstsquadplayers);
                System.out.println("?????");
                //do tej pory dziala, pobiera zaznaczonych i listuje w konsoli
                //teraz ma zmienic flage firstSquadPlayer
                //powinien uzyc editPlayer, ale wartość firstsquadplayer pobrac z checkboxa...

            }
            List<Player> first11 = this.playerService.findAllPlayers().stream()
                    .filter(Player::isFirstSquadPlayer).collect(Collectors.toList());
            System.out.println(first11);
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
