package PFA.Back.Service.impl;

import PFA.Back.Model.Societe;
import PFA.Back.Model.client;
import PFA.Back.Repository.SocieteRepository;
import PFA.Back.Service.SocieteServiceInterface;
import PFA.Back.utils.StorageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class SocieteServiceClass implements SocieteServiceInterface {

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private StorageUtils storageUtils;

    @Override
    public List<Societe> findAllSociete() {
        return societeRepository.findAll();
    }

    @Override
    public Societe addSociete(Societe societe, MultipartFile file) {
        String fileName = storageUtils.CreateNameImage(file);
        storageUtils.store(file, fileName);
        societe.setImage(fileName);
        return societeRepository.save(societe);
    }


    @Override
    public Societe getSocieteById(Long id) {
        return societeRepository.findById(id).orElse(null);
    }

    @Override
    public Societe updateSociete(Long id, Societe societe) {
        Optional<Societe> existingSocieteOptional = societeRepository.findById(id);
        if (existingSocieteOptional.isPresent()) {
            Societe existingSociete = existingSocieteOptional.get();
            existingSociete.setNom(societe.getNom());
            existingSociete.setAdresse(societe.getAdresse());
            existingSociete.setEmail(societe.getEmail());
            existingSociete.setFax(societe.getFax() == 0 ? existingSociete.getFax() : societe.getFax());
            existingSociete.setTelephone(societe.getTelephone() == 0 ? existingSociete.getTelephone() : societe.getTelephone());
            existingSociete.setMatricule(societe.getMatricule() == 0 ? existingSociete.getMatricule() : societe.getMatricule());
            return societeRepository.save(existingSociete);
        } else {
            return null;
        }
    }

    @Override
    public Societe updateSocietePhoto(Long id, MultipartFile file) {
        Optional<Societe> existingSocieteOptional = societeRepository.findById(id);
        if (existingSocieteOptional.isPresent()) {
            Societe existingSociete = existingSocieteOptional.get();
            if (file != null && !file.isEmpty()) {
                String fileName = storageUtils.createNameImage(file); // Assurez-vous que cette méthode existe
                storageUtils.store(file, fileName);
                existingSociete.setImage(fileName);
            }
            return societeRepository.save(existingSociete);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSociete(Long id) {
        societeRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storageUtils.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
