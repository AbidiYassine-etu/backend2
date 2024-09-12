package PFA.Back.Controller;

import PFA.Back.Model.client;
import PFA.Back.Service.ClientServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientServiceInterface clientServiceInterface;

    // Obtenir tous les clients
    @GetMapping("/findAllClients")
    public List<client> findAllClients() {
        return clientServiceInterface.findAllClients();
    }

    // Ajouter un client avec une image
    @PostMapping("/addClient")
    public client addClient(client client, @RequestParam("file") MultipartFile file) {
        return clientServiceInterface.addClient(client, file);
    }

    // Obtenir un client par ID
    @GetMapping("/getClientById/{id}")
    public client getClientById(@PathVariable Long id) {
        return clientServiceInterface.getClientById(id);
    }

    // Mise à jour d'un client
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<client> updateClient(@PathVariable("id") Long id, client client) {
        client updatedClient = clientServiceInterface.updateClient(id, client);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mise à jour de l'image du client
    @PutMapping("/updateClientImage/{id}/photo")
    public ResponseEntity<client> updateClientImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        client updatedClient = clientServiceInterface.updateClientImage(id, file);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un client
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientServiceInterface.deleteClient(id);
    }

    // Obtenir un fichier par nom
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return clientServiceInterface.getFile(filename);
    }
}
