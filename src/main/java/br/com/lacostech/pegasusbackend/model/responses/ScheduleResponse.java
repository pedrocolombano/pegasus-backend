package br.com.lacostech.pegasusbackend.model.responses;

import br.com.lacostech.pegasusbackend.model.BreedModel;
import br.com.lacostech.pegasusbackend.model.ProceedingModel;
import br.com.lacostech.pegasusbackend.model.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime schedule;
    private String petName;
    private BreedModel breed;
    private ProceedingModel proceedingModel;

    public ScheduleResponse(final Schedule entity) {
        if (Objects.nonNull(entity)) {
            BeanUtils.copyProperties(entity, this);

            this.breed = new BreedModel(entity.getPetBreed());
            this.proceedingModel = new ProceedingModel(entity.getProceeding());
        }
    }

}
