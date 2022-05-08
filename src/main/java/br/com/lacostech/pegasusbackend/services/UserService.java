package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Role;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.requests.UserRequest;
import br.com.lacostech.pegasusbackend.model.responses.UserResponse;
import br.com.lacostech.pegasusbackend.repositories.RoleRepository;
import br.com.lacostech.pegasusbackend.repositories.UserRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.DatabaseException;
import br.com.lacostech.pegasusbackend.services.exceptions.InvalidDataException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public UserResponse findById(final Long id) {
        authService.validateSelfOrAdmin(id);
        User user = getUserById(id);
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse insert(final UserRequest request) {
        User user = new User();
        validateUserAccount(request);
        copyDataFromRequest(user, request);

        user = userRepository.save(user);
        return new UserResponse(user);
    }

    @Transactional
    public UserResponse update(final Long id, final UserRequest request) {
        authService.validateSelfOrAdmin(id);

        User user = getUserById(id);
        validateUserAccount(request);
        copyDataFromRequest(user, request);

        user = userRepository.save(user);
        return new UserResponse(user);
    }

    public void deleteById(final Long id) {
        authService.validateSelfOrAdmin(id);
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | IllegalArgumentException e) {
            throw new NotFoundException("User id not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    @Transactional(readOnly = true)
    private User getUserById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User id not found"));
    }

    @Transactional(readOnly = true)
    private void validateUserAccount(final UserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (Objects.nonNull(user) && !isSameUser(request, user)) {
            throw new InvalidDataException("Email already being used");
        }
    }

    private boolean isSameUser(final UserRequest request, final User user) {
        return user.getId().equals(request.getId());
    }

    private void copyDataFromRequest(final User entity, final UserRequest request) {
        if (Objects.nonNull(request)) {
            BeanUtils.copyProperties(request, entity);

            entity.setPassword(passwordEncoder.encode(request.getPassword()));

            entity.getRoles().clear();
            request.getRoles().forEach(r -> {
                Role role = roleRepository.findById(r.getId())
                        .orElseThrow(() -> new NotFoundException("Role id " + r.getId() + " not found"));
                entity.getRoles().add(role);
            });
        }
    }

}
