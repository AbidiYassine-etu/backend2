package PFA.Back.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class client extends Utilisateur {

    private String role = "client";
    private int test;

    // Si vous avez des relations avec d'autres entités, décommentez et configurez-les ici
    // @OneToOne
    // @JoinColumn(name = "evaluateur_id")
    // private Evaluateur evaluateur;
}
