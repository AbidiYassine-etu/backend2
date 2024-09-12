package PFA.Back.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Admin extends Utilisateur{
    private String role="admin";
}

