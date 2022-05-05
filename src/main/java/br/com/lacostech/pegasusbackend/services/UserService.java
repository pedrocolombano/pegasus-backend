package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Role;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.requests.UserRequest;
import br.com.lacostech.pegasusbackend.model.responses.UserResponse;
import br.com.lacostech.pegasusbackend.repositories.RoleRepository;
import br.com.lacostech.pegasusbackend.repositories.UserRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.InvalidDataException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        authService.validateSelfOrAdmin(id);
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id not found"));
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse insert(UserRequest request) {
        User user = new User();
        validateUserAccount(request);
        copyDataFromRequest(user, request);
        return new UserResponse(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    private void validateUserAccount(UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (Objects.nonNull(user) && !isSameUser(request, user)) {
            throw new InvalidDataException("Email already being used");
        }
    }

    private boolean isSameUser(UserRequest request, User user) {
        return user.getId().equals(request.getId());
    }

    private void copyDataFromRequest(User entity, UserRequest request) {
        if (Objects.nonNull(request)) {
            try {
                BeanUtils.copyProperties(request, entity);

                entity.setPassword(passwordEncoder.encode(request.getPassword()));

                entity.getRoles().clear();
                request.getRoles().forEach(r -> {
                    Role role = roleRepository.getById(r.getId());
                    entity.getRoles().add(role);
                });
            } catch (EntityNotFoundException e) {
                throw new NotFoundException("Role id not found");
            }
        }
    }

}
