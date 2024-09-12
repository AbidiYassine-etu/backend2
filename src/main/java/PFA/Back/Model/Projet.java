package PFA.Back.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private String pdfFilePath;

    @ElementCollection
    private List<String> pdfFilePaths;



}
