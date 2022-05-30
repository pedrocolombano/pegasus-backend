package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.repositories.BreedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

}
