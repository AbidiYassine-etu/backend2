package PFA.Back.Service.impl;

import PFA.Back.Model.Materieux;
import PFA.Back.Repository.MaterieuxRepository;
import PFA.Back.Service.MaterieuxServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterieuxServiceImpl implements MaterieuxServiceInterface {

    @Autowired
    private MaterieuxRepository materieuxRepository;

    @Override
    public List<Materieux> findAllMaterieux() {
        // Fetch all Materieux from the repository
        return materieuxRepository.findAll();
    }

    @Override
    public Materieux addMaterieux(Materieux materieux) {
        // Save the Materieux object to the repository
        return materieuxRepository.save(materieux);
    }

    @Override
    public Materieux getMaterieuxById(long id) {
        // Fetch Materieux by ID, or throw an exception if not found
        Optional<Materieux> materieux = materieuxRepository.findById(id);
        return materieux.orElseThrow(() -> new RuntimeException("Materieux not found with id: " + id));
    }

    @Override
    public Materieux updateMaterieux(Long id, Materieux materieux) {
        // Fetch the existing Materieux object, then update it with the new data
        Materieux existingMaterieux = getMaterieuxById(id);

        existingMaterieux.setNom(materieux.getNom());
        existingMaterieux.setQuantite(materieux.getQuantite()); // Assuming quantite is either String or numeric

        // Save the updated Materieux object
        return materieuxRepository.save(existingMaterieux);
    }

    @Override
    public void deleteMaterieux(Long id) {
        // Fetch the Materieux object by ID, and delete it
        Materieux materieux = getMaterieuxById(id);
        materieuxRepository.delete(materieux);
    }
}
