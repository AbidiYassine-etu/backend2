package PFA.Back.Service;


import PFA.Back.Model.Projet;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



public interface ProjetServiceInterface {
    List<Projet> findAllprojet();
    Projet addProjet(Projet projet, MultipartFile[] files);
    Projet getProjetById(long id);
    Projet updateProjet(Long id, Projet projet,MultipartFile[] files);
    void deleteProjet(Long id);
    ResponseEntity<Resource> getFile(String filename);
}


