package cgb.transfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import cgb.transfer.entity.Customer;
import cgb.transfer.repository.UserRepository;
import cgb.transfer.services.CustomerService;

// Préauthorisation en lien avec le composant demo.security.AccessControl 
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @accessControl.isUserOfCustomer(authentication, #id)")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') and @accessControl.isUserOfCustomer(authentication, #id)")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!customerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        return ResponseEntity.ok(customerService.save(customer));
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }
    
    @GetMapping("/S/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') and @accessControlComptable.isUserOfCustomer(authentication, #id)")
    public ResponseEntity<Customer> getCustomerWithS(@PathVariable Long id, @RequestBody Customer customer) {
        if (!customerService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        return ResponseEntity.ok(customerService.save(customer));
    }
    
    
    @GetMapping("/contient-S")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') and @accessControlComptable.isUserOfCustomer(authentication, #id)")
    public ResponseEntity<String> getCustomerWithtest() {
        return ResponseEntity.ok("HasRole is OK");
    }
    
}
