package PFA.Back.Repository;

import PFA.Back.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
   Utilisateur getUtilisateurByEmailAndPassword(String email,String password);
}
