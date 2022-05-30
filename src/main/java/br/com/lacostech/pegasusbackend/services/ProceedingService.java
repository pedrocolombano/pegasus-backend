package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.model.entities.Proceeding;
import br.com.lacostech.pegasusbackend.repositories.ProceedingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public ProceedingModel insert(final ProceedingModel request) {
        Proceeding proceeding = new Proceeding();
        copyDataFromRequest(request, proceeding);

        proceeding = proceedingRepository.save(proceeding);
        return new ProceedingModel(proceeding);
    }

    private void copyDataFromRequest(final ProceedingModel request, final Proceeding entity) {
        if (Objects.nonNull(request)) {
            entity.setName(request.getName());
            entity.setPrice(request.getPrice());
        }
    }

}
