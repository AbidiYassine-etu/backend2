package PFA.Back.Service;

import PFA.Back.Model.Admin;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminServiceInterface {
    List<Admin> findAllAdmin();
    Admin addAdmin(Admin admin, MultipartFile file);
    Admin getAdminById(long id);
    Admin updateAdmin(Long id, Admin admin);
    Admin updateAdminImage(Long id, MultipartFile file);
    void deleteAdmin(Long id);
    ResponseEntity<Resource> getFile(String filename);
}
