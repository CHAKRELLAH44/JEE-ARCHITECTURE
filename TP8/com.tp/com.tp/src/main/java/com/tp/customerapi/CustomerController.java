package com.tp.customerapi;

import com.tp.customerapi.model.Customer;
import com.tp.customerapi.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/all")
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer c) {
        return repo.save(c);
    }

    @PutMapping("/update/{identityRef}")
    public Customer update(@PathVariable String identityRef, @RequestBody Customer c) {
        Customer existing = repo.findByIdentityRef(identityRef);
        existing.setFirstname(c.getFirstname());
        existing.setLastname(c.getLastname());
        existing.setUsername(c.getUsername());
        existing.setIdentityRef(c.getIdentityRef());
        return repo.save(existing);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
