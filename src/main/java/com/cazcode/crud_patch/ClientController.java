package com.cazcode.crud_patch;

import com.cazcode.crud_patch.dto.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Client>> getAllProducts(int page, int size) {
        return ResponseEntity.ok(clientService.getAllClientsPaginated(page, size));
    }

    @PostMapping
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clientService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clientService.update(id, cliente));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> patchClient(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(clientService.updateClientPartial(id, updates));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
