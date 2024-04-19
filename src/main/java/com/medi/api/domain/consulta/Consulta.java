package com.medi.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medi.api.domain.medico.Medico;
import com.medi.api.domain.pacientes.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "consultas")
@Entity(name = "Consulta")
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data_consulta;

}
