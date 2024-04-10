package com.medi.api.domain.consulta;

import com.medi.api.domain.medico.Especialidade;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ConsultaDTO(Long medico_id, @NotNull Long paciente_id, @NotNull Date data, Especialidade especialidade) {
}
