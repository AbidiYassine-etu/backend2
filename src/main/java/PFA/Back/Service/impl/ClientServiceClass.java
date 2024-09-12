package PFA.Back.Service.impl;

import PFA.Back.Model.client;
import PFA.Back.Repository.clientRepository;
import PFA.Back.Service.ClientServiceInterface;
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
public class ClientServiceClass implements ClientServiceInterface {

    @Autowired
    private clientRepository clientRepository;

    @Autowired
    private StorageUtils storageUtils;

    @Override
    public List<client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public client addClient(client client, MultipartFile file) {
        String fileName = storageUtils.CreateNameImage(file);
        storageUtils.store(file, fileName);
        client.setImage(fileName);
        return clientRepository.save(client);
    }

    @Override
    public client getClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public client updateClient(Long id, client client) {
        Optional<client> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isPresent()) {
            client existingClient = existingClientOptional.get();
            // Update fields
            existingClient.setNom(client.getNom());
            existingClient.setEmail(client.getEmail());
            existingClient.setPassword(client.getPassword());
            existingClient.setTel(client.getTel() == 0 ? existingClient.getTel() : client.getTel());

            return clientRepository.save(existingClient);
        } else {
            return null;
        }
    }

    @Override
    public client updateClientImage(Long id, MultipartFile file) {
        Optional<client> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isPresent()) {
            client existingClient = existingClientOptional.get();
            if (file != null && !file.isEmpty()) {
                String fileName = storageUtils.CreateNameImage(file);
                storageUtils.store(file, fileName);
                existingClient.setImage(fileName);
                return clientRepository.save(existingClient);
            } else {
                // Handle case where file is null or empty
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storageUtils.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @Override
    public client accepter(Long id) {
        client x = clientRepository.findById(id).orElse(null);
        if (x != null) {
            x.setTest(1);
            return clientRepository.save(x);
        }
        return null;
    }
}
