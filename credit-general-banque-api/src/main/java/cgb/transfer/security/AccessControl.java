package cgb.transfer.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import cgb.transfer.repository.UserRepository;

@Component
public class AccessControl {

    private final UserRepository userRepository;

    public AccessControl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserOfCustomer(Authentication authentication, Long customerId) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .map(user -> user.getCustomer().getId().equals(customerId))
                .orElse(false);
    }
}
