package PFA.Back.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Table(name="societe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Societe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String nom;
    private String adresse;
    private String email;
    private long fax;
    private long telephone;
    private long matricule;
    private String image;
}
