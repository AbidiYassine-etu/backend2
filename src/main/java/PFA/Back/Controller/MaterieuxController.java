package PFA.Back.Controller;

import PFA.Back.Model.Materieux;
import PFA.Back.Service.MaterieuxServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materieux")
public class MaterieuxController {

    @Autowired
    private MaterieuxServiceInterface materieuxService;

    // Get all materieux
    @GetMapping("/getAllMaterieux")
    public ResponseEntity<List<Materieux>> getAllMaterieux() {
        List<Materieux> materieuxList = materieuxService.findAllMaterieux();
        return new ResponseEntity<>(materieuxList, HttpStatus.OK);
    }

    // Get a single materieux by ID
    @GetMapping("/getMateriexById/{id}")
    public ResponseEntity<Materieux> getMaterieuxById(@PathVariable("id") Long id) {
        Materieux materieux = materieuxService.getMaterieuxById(id);
        return new ResponseEntity<>(materieux, HttpStatus.OK);
    }

    // Add a new materieux
    @PostMapping(value = "/addMaterieux",consumes = "application/json")
    public ResponseEntity<Materieux> addMaterieux(@Valid @RequestBody Materieux materieux) {
        Materieux newMaterieux = materieuxService.addMaterieux(materieux);
        return new ResponseEntity<>(newMaterieux, HttpStatus.CREATED);
    }

    // Update an existing materieux
    @PutMapping("/updateMaterieux/{id}")
    public ResponseEntity<Materieux> updateMaterieux(@PathVariable("id") Long id, @RequestBody Materieux materieux) {
        Materieux updatedMaterieux = materieuxService.updateMaterieux(id, materieux);
        return new ResponseEntity<>(updatedMaterieux, HttpStatus.OK);
    }

    // Delete a materieux by ID
    @DeleteMapping("/deleteMaterieux/{id}")
    public ResponseEntity<Void> deleteMaterieux(@PathVariable("id") Long id) {
        materieuxService.deleteMaterieux(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
