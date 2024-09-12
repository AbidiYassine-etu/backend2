package PFA.Back.Controller;

import PFA.Back.Model.Societe;
import PFA.Back.Service.SocieteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/societe")
@CrossOrigin("*")
public class SocieteController {

    @Autowired
    public SocieteServiceInterface societeServiceInterface;

    @GetMapping("/findAllSociete")
    public List<Societe> findAllSociete() {
        return societeServiceInterface.findAllSociete();
    }

    @PostMapping("/addSociete")
    public Societe addSociete(Societe societe, @RequestParam ("file")  MultipartFile file){
        return societeServiceInterface.addSociete(societe,file);
    }


    @GetMapping("/getSocieteById/{id}")
    public Societe getSocieteById(@PathVariable Long id) {
        return societeServiceInterface.getSocieteById(id);
    }

    @PutMapping("/updateSociete/{id}")
    public ResponseEntity<Societe> updateSociete(@PathVariable("id") Long id, Societe societe) {
        Societe updatedSociete = societeServiceInterface.updateSociete(id, societe);
        if (updatedSociete != null) {
            return ResponseEntity.ok(updatedSociete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateSocietePhoto/{id}/photo")
    public ResponseEntity<Societe> updateSocietePhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Societe updatedSociete = societeServiceInterface.updateSocietePhoto(id, file);
        if (updatedSociete != null) {
            return ResponseEntity.ok(updatedSociete);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSociete(@PathVariable Long id) {
        societeServiceInterface.deleteSociete(id);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return societeServiceInterface.getFile(filename);
    }
}
