package br.com.lacostech.pegasusbackend.model.entities;

import br.com.lacostech.pegasusbackend.model.enums.PetSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime schedule;

    @Column(nullable = false, length = 60)
    private String petName;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed petBreed;

    @ManyToOne
    @JoinColumn(name = "proceeding_id")
    private Proceeding proceeding;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private PetSize petSize;

}
