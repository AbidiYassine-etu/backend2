package PFA.Back.Controller;

import PFA.Back.Model.Admin;
import PFA.Back.Service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    public AdminServiceInterface adminServiceInterface;
    @GetMapping("/findAllAdmin")
    public List<Admin> findAllAdmin(){
        return adminServiceInterface.findAllAdmin();
    }

    @PostMapping("/addAdmin")
    public Admin addAdmin(Admin admin, @RequestParam MultipartFile file){
        return adminServiceInterface.addAdmin(admin,file);
    }
    @GetMapping("/getAdminById/{id}")
    public Admin getAdminById(@PathVariable Long id) {

        return  adminServiceInterface.getAdminById(id);
    }

    @PutMapping("updatedAdmin/{id}")
    public ResponseEntity<Admin> updatedAdmin(@PathVariable("id") Long id,  Admin admin) {
        Admin updatedAdmin = adminServiceInterface.updateAdmin(id, admin);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("updateAdminImage/{id}/photo")
    public ResponseEntity<Admin> updateAdminImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Admin updatedAdmin = adminServiceInterface.updateAdminImage(id, file);
        if (updatedAdmin != null) {
            return ResponseEntity.ok(updatedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }

    }



    @DeleteMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable Long id){
        adminServiceInterface.deleteAdmin(id);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return adminServiceInterface.getFile(filename);
    }
}
