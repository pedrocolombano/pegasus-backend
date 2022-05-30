package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.repositories.ProceedingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProceedingService {

    private final ProceedingRepository proceedingRepository;

    @Transactional(readOnly = true)
    public List<ProceedingModel> findAll() {
        return proceedingRepository
                .findAll()
                .stream()
                .map(ProceedingModel::new)
                .collect(Collectors.toList());
    }

}
