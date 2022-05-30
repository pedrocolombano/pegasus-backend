package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.model.entities.Breed;
import br.com.lacostech.pegasusbackend.repositories.BreedRepository;
import lombok.AllArgsConstructor;
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

    private void copyDataFromRequest(final BreedModel request, final Breed entity) {
        if (Objects.nonNull(request)) {
            entity.setName(request.getName());
        }
    }

}
