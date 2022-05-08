package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Address;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.requests.AddressRequest;
import br.com.lacostech.pegasusbackend.model.responses.AddressResponse;
import br.com.lacostech.pegasusbackend.repositories.AddressRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.DatabaseException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AddressService {

    private final AuthService authService;
    private final AddressRepository addressRepository;

    @Transactional
    public AddressResponse insert(final AddressRequest request) {
        Address address = new Address();
        copyDataFromRequest(address, request);

        address = addressRepository.save(address);
        return new AddressResponse(address);
    }

    @Transactional
    public AddressResponse update(final Long id, final AddressRequest request) {
        Address address = addressRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Address id not found"));
        copyDataFromRequest(address, request);

        address = addressRepository.save(address);
        return new AddressResponse(address);
    }

    public void deleteById(final Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException | IllegalArgumentException e) {
            throw new NotFoundException("Address id not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    private void copyDataFromRequest(final Address entity, final AddressRequest request) {
        if (Objects.nonNull(request)) {
            BeanUtils.copyProperties(request, entity);

            User user = authService.getAuthenticatedUser();
            entity.setUser(user);
        }
    }

}
