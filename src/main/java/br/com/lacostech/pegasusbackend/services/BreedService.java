package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.model.entities.Breed;
import br.com.lacostech.pegasusbackend.model.enums.PetType;
import br.com.lacostech.pegasusbackend.repositories.BreedRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.DatabaseException;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BreedService {

    private final BreedRepository breedRepository;

    @Transactional(readOnly = true)
    public List<BreedModel> findAll() {
        return breedRepository.findAll()
                .stream()
                .map(BreedModel::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public BreedModel insert(final BreedModel request) {
        Breed breed = new Breed();
        copyDataFromRequest(request, breed);

        breed = breedRepository.save(breed);
        return new BreedModel(breed);
    }

    @Transactional
    public BreedModel update(final Long id, final BreedModel request) {
        Breed breed = breedRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Breed id " + id + " not found"));
        copyDataFromRequest(request, breed);

        breed = breedRepository.save(breed);
        return new BreedModel(breed);
    }

    public void deleteById(final Long id) {
        try {
            breedRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Breed id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    private void copyDataFromRequest(final BreedModel request, final Breed entity) {
        if (Objects.nonNull(request)) {
            entity.setName(request.getName());

            PetType petType = PetType.fromDescription(request.getPetType());
            entity.setPetType(petType);
        }
    }

}
