package com.medi.api.domain.consulta.validacoes;

import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.domain.consulta.ConsultaRepository;
import com.medi.api.utils.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorApenasUmaConsultaPorDia implements ValidadorConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(ConsultaDTO consulta){
        if(consultaRepository.existeConsultaNoMesmoDia(consulta.medico_id(), consulta.data()) != null) throw new ValidacaoException("já existe uma consulta marcada para esse dia");
    }
}
