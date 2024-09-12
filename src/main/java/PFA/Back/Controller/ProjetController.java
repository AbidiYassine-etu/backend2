package PFA.Back.Controller;

import PFA.Back.Model.Projet;
import PFA.Back.Service.ProjetServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetServiceInterface projetService;

    // Get all projects
    @GetMapping("/allProjet")
    public ResponseEntity<List<Projet>> getAllProjets() {
        List<Projet> projets = projetService.findAllprojet();
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    // Get a project by ID
    @GetMapping("/getProjetById/{id}")
    public ResponseEntity<Projet> getProjetById(@PathVariable("id") long id) {
        Projet projet = projetService.getProjetById(id);
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    // Add a new project with an optional PDF file
    @PostMapping("/addProjet")
    public ResponseEntity<Projet> addProjet(@ModelAttribute Projet projet,
                                            @RequestParam(value = "files", required = false) MultipartFile[] files) {
        Projet newProjet = projetService.addProjet(projet, files);
        return new ResponseEntity<>(newProjet, HttpStatus.CREATED);
    }




    @PutMapping("/updateProjet/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable("id") Long id,
                                               @ModelAttribute Projet projet,
                                               @RequestParam(value = "files", required = false) MultipartFile[] files) {
        Projet updatedProjet = projetService.updateProjet(id, projet, files);
        return new ResponseEntity<>(updatedProjet, HttpStatus.OK);
    }



    // Delete a project by ID
    @DeleteMapping("deleteProjet/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable("id") Long id) {
        projetService.deleteProjet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Download a PDF file associated with a project
    @GetMapping("/downloadProjet/files/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) {
        return projetService.getFile(filename);
    }
}

