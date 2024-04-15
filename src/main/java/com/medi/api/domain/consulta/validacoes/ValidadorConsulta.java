package com.medi.api.domain.consulta.validacoes;

import com.medi.api.domain.consulta.ConsultaDTO;

public interface ValidadorConsulta {
    void validar(ConsultaDTO dadosConsulta);
}
