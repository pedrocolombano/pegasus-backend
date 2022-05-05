package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.responses.UserResponse;
import br.com.lacostech.pegasusbackend.repositories.UserRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        authService.validateSelfOrAdmin(id);
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id not found"));
        return new UserResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

}
