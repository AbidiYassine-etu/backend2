package PFA.Back.Service;

import PFA.Back.Model.client;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientServiceInterface {

    // Récupérer tous les clients
    List<client> findAllClients();

    // Ajouter un client avec un fichier
    client addClient(client client, MultipartFile file);

    // Supprimer un client par ID
    void deleteClient(Long id);

    // Accepter un client (exemple de fonctionnalité)
    client accepter(Long id);

    // Mettre à jour un client par ID
    client updateClient(Long id, client client);

    // Récupérer un client par ID
    client getClientById(long id);

    // Mettre à jour l'image d'un client par ID
    client updateClientImage(Long id, MultipartFile file);

    // Obtenir un fichier (ex: image) par nom de fichier
    ResponseEntity<Resource> getFile(String filename);
}
