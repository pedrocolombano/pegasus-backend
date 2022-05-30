package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.model.entities.Proceeding;
import br.com.lacostech.pegasusbackend.repositories.ProceedingRepository;
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

    @Transactional
    public ProceedingModel update(final Long id, final ProceedingModel request) {
        Proceeding proceeding = proceedingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proceed id " + id + " not found"));
        copyDataFromRequest(request, proceeding);

        proceeding = proceedingRepository.save(proceeding);
        return new ProceedingModel(proceeding);
    }

    public void deleteById(final Long id) {
        try {
            proceedingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Proceed id " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Data integrity violation");
        }
    }

    private void copyDataFromRequest(final ProceedingModel request, final Proceeding entity) {
        if (Objects.nonNull(request)) {
            entity.setName(request.getName());
            entity.setPrice(request.getPrice());
        }
    }

}
