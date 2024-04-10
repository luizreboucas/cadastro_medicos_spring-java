package com.medi.api.domain.consulta;

import java.util.Date;

public record ConsultaDTO(Long medico_id, Long paciente_id, Date data) {

    public ConsultaDTO(Consulta consulta){
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData_consulta());
    }
}
