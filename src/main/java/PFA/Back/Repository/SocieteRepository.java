
package PFA.Back.Repository;

import PFA.Back.Model.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Importation de List


@Repository

public interface SocieteRepository extends JpaRepository<Societe,Long> {


}
