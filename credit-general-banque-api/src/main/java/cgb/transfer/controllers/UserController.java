package cgb.transfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import cgb.transfer.entity.User;
import cgb.transfer.repository.UserRepository;
import cgb.transfer.services.JwtService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/{username}")
    //#id == authentication.principal en 
    //@PreAuthorize("#username == principal.username or hasRole('ADMIN')")
    @PreAuthorize("#username == principal.username or hasAuthority('ROLE_ADMIN')")
    //ATTENTION Avec hasAuthority, il faut rajouter ROLE_
    //@PreAuthorize("permitAll()")
    public ResponseEntity<User> getUser(@PathVariable String username, Principal principal) {
        System.out.println("Principal name: " + principal.getName() +"To string "+ principal.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("<-- Authorities -->: " + authentication.getAuthorities().toString());   
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/auth/{id}")
    //@PreAuthorize("permitAll()")
    public ResponseEntity<User> getUserBy(@PathVariable Long id) {
        //System.out.println("Principal name: " + principal.getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("<-- Authorities -->: " + authentication.getAuthorities().toString());
        System.out.println("                   |-- Authority -->: " + authentication.getAuthorities().stream().findFirst());
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
