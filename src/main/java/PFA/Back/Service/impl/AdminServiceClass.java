package PFA.Back.Service.impl;

import PFA.Back.Model.Admin;
import PFA.Back.Repository.AdminRepository;
import PFA.Back.Service.AdminServiceInterface;
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
public class AdminServiceClass implements AdminServiceInterface {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StorageUtils storageUtils;

    @Override
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin addAdmin(Admin admin, MultipartFile file) {
        String fileName = storageUtils.CreateNameImage(file);
        storageUtils.store(file, fileName);
        admin.setImage(fileName);
        return adminRepository.save(admin);
    }
    @Override
    public Admin getAdminById(long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Optional<Admin> existingAdminOptional = adminRepository.findById(id);
        if (existingAdminOptional.isPresent()) {
            Admin existingAdmin = existingAdminOptional.get();
            // Mise à jour des champs
            existingAdmin.setNom(admin.getNom());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setPassword(admin.getPassword());
            existingAdmin.setTel(admin.getTel() == 0 ? existingAdmin.getTel() : admin.getTel());


            return adminRepository.save(existingAdmin);
        } else {
            return null;
        }
    }

    @Override
    public Admin updateAdminImage(Long id, MultipartFile file) {
        Optional<Admin> existingAdminOptional = adminRepository.findById(id);
        if (existingAdminOptional.isPresent()) {
            Admin existingAdmin = existingAdminOptional.get();
            if (file != null && !file.isEmpty()) {
                String fileName = storageUtils.CreateNameImage(file);
                storageUtils.store(file, fileName);
                existingAdmin.setImage(fileName);
                return adminRepository.save(existingAdmin);
            } else {
                // Gérer le cas où le fichier est null ou vide
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storageUtils.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
