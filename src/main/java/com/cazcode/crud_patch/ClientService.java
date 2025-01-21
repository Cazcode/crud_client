package com.cazcode.crud_patch;

import com.cazcode.crud_patch.dto.Cliente;
import com.cazcode.crud_patch.mapper.ClientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Cliente> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper.INSTANCE::toCliente)
                .toList();
    }

    public Cliente findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client does not exits!"));
        return ClientMapper.INSTANCE.toCliente(client);
    }

    public Cliente save(Cliente cliente) {
        Client client = ClientMapper.INSTANCE.toClient(cliente);
        Client clientSave = clientRepository.save(client);
        return ClientMapper.INSTANCE.toCliente(clientSave);
    }

    public Cliente update(Long id, Cliente cliente) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client does not exits!"));
        client.setPhone(client.getPhone());
        client.setName(client.getName());
        client.setEmail(client.getEmail());

        Client clientSave = clientRepository.save(client);
        return ClientMapper.INSTANCE.toCliente(clientSave);
    }


    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public Cliente updateClientPartial(Long id, Map<String, Object> updates) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    client.setName(value != null ? value.toString() : null);
                    break;
                case "email":
                    client.setEmail(value != null ? value.toString() : null);
                    break;
                case "phone":
                    client.setPhone(value != null ? value.toString() : null);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        Client save = clientRepository.save(client);
        return ClientMapper.INSTANCE.toCliente(client);
    }

    public Page<Client> getAllClientsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }
}
