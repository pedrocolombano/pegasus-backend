package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.entities.Address;
import br.com.lacostech.pegasusbackend.model.entities.User;
import br.com.lacostech.pegasusbackend.model.requests.AddressRequest;
import br.com.lacostech.pegasusbackend.model.responses.AddressResponse;
import br.com.lacostech.pegasusbackend.repositories.AddressRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AddressService {

    private final AuthService authService;
    private final AddressRepository addressRepository;

    @Transactional
    public AddressResponse insert(AddressRequest request) {
        Address address = new Address();
        copyDataFromRequest(address, request);
        return new AddressResponse(addressRepository.save(address));
    }

    @Transactional
    public AddressResponse update(Long id, AddressRequest request) {
        Address address = addressRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Address id not found"));
        copyDataFromRequest(address, request);
        return new AddressResponse(addressRepository.save(address));
    }

    private void copyDataFromRequest(Address entity, AddressRequest request) {
        if (Objects.nonNull(request)) {
            try {
                BeanUtils.copyProperties(request, entity);

                User user = authService.getAuthenticatedUser();
                entity.setUser(user);
            } catch (EntityNotFoundException e) {
                throw new NotFoundException("User id not found");
            }
        }
    }

}
