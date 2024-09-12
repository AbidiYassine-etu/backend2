package PFA.Back.Service;

import PFA.Back.Model.Societe;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SocieteServiceInterface {
    List<Societe> findAllSociete();


    Societe addSociete(Societe societe, MultipartFile file);
    void deleteSociete(Long id);

    Societe getSocieteById(Long id);

    Societe updateSociete(Long id, Societe societe);
    Societe updateSocietePhoto(Long id, MultipartFile file);

    ResponseEntity<Resource> getFile(String filename);
}
