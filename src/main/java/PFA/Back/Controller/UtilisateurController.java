package PFA.Back.Controller;

import PFA.Back.Model.Utilisateur;
import PFA.Back.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/util")
@RestController
public class UtilisateurController {
    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @PostMapping(path = "/login")
    public Utilisateur Login (@RequestBody Utilisateur util) {

        Utilisateur acteur1  = utilisateurRepository.getUtilisateurByEmailAndPassword(util.getEmail(),util.getPassword());

        if (acteur1!=null) {

            System.out.println("Login avec succè");
            return acteur1;
        } else {
            System.out.println("erreur de connection");
            return null;
        }

    }
}
