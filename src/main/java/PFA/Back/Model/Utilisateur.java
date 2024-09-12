package PFA.Back.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Data
@Table(name="Utilisateur")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nom;
    private String email;
    private String password;
    private String image;
    private long tel;


  /*  @OneToMany
    private List<Resultat> resultat;
    @OneToOne
    private Societe societe;
    @ManyToMany
    private List<Principe> principes;*/
}