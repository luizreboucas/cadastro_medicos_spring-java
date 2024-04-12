package com.medi.api.domain.consulta;

import com.medi.api.domain.medico.Medico;
import com.medi.api.domain.pacientes.Paciente;

import java.time.LocalDateTime;
import java.util.Date;

public record ConsultaCompletaDTO(Long id, Medico medico, Paciente paciente, LocalDateTime data_consulta) {

    public ConsultaCompletaDTO(Consulta consulta){
        this(consulta.getId(),consulta.getMedico(), consulta.getPaciente(), consulta.getData_consulta());
    }
}
