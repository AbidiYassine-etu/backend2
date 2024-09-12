package PFA.Back.Service.impl;

import PFA.Back.Model.Projet;
import PFA.Back.Repository.ProjetRepository;
import PFA.Back.Service.ProjetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetServiceInterface {

    @Autowired
    private ProjetRepository projetRepository;

    private final Path fileStorageLocation = Paths.get("storage").toAbsolutePath().normalize();

    @Override
    public List<Projet> findAllprojet() {
        return projetRepository.findAll();
    }

    public Projet addProjet(Projet projet, MultipartFile[] files) {
        try {
            // Save all files to the filesystem
            List<String> filePaths = new ArrayList<>();
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String fileName = file.getOriginalFilename();
                        Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                        filePaths.add(targetLocation.toString());
                    }
                }
            }

            // Save the file paths in the project
            projet.setPdfFilePaths(filePaths);
            return projetRepository.save(projet);
        } catch (Exception e) {
            throw new RuntimeException("Could not store files. Please try again!", e);
        }
    }


    @Override
    public Projet getProjetById(long id) {
        Optional<Projet> projet = projetRepository.findById(id);
        return projet.orElseThrow(() -> new RuntimeException("Project not found"));
    }


    public Projet updateProjet(Long id, Projet projet, MultipartFile[] files) {
        try {
            // Retrieve the existing project
            Projet existingProjet = projetRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            // Update project details
            existingProjet.setNom(projet.getNom());
            existingProjet.setDescription(projet.getDescription());
            existingProjet.setDateDebut(projet.getDateDebut());
            existingProjet.setDateFin(projet.getDateFin());

            // Handle file update if files are provided
            List<String> filePaths = new ArrayList<>();
            if (files != null && files.length > 0) {
                for (MultipartFile file : files) {
                    if (!file.isEmpty()) {
                        String fileName = file.getOriginalFilename();
                        Path targetLocation = this.fileStorageLocation.resolve(fileName).normalize();
                        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                        filePaths.add(targetLocation.toString());
                    }
                }
            }

            // Update the file paths in the project
            existingProjet.setPdfFilePaths(filePaths);
            return projetRepository.save(existingProjet);
        } catch (Exception e) {
            throw new RuntimeException("Could not update project or store files. Please try again!", e);
        }
    }



    @Override
    public void deleteProjet(Long id) {
        Projet existingProjet = getProjetById(id);
        projetRepository.delete(existingProjet);
    }

    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        try {
            Path filePath = fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok().body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not download the file " + filename, e);
        }
    }
}

