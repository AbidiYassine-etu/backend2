package PFA.Back.Service;

import PFA.Back.Model.Materieux;

import java.util.List;

public interface MaterieuxServiceInterface {
    List<Materieux> findAllMaterieux();
    Materieux addMaterieux(Materieux materieux);
    Materieux getMaterieuxById(long id);
    Materieux updateMaterieux(Long id, Materieux materieux);
    void deleteMaterieux(Long id);
}
