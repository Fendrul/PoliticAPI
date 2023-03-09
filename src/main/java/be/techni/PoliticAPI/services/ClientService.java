package be.techni.PoliticAPI.services;

import be.techni.PoliticAPI.models.dto.ClientDTO;
import be.techni.PoliticAPI.models.entities.Client;
import be.techni.PoliticAPI.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO getById(long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return ClientDTO.fromEntity(client);
    }

    public ClientDTO getByName(String name) {
        Client client = clientRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return ClientDTO.fromEntity(client);
    }
}
