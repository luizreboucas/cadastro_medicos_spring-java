package com.medi.api.domain.consulta.validacoes;

import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.utils.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class ValidadorConsultaComAntecedenciaDeTrintaMinutos implements ValidadorConsulta{

    public void validar(ConsultaDTO consulta){
        var dataconsulta = consulta.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataconsulta).toMinutes();
        if(diferenca < 30) throw new ValidacaoException("a consulta deve ser marcada com no mínimo 30 minutos de antecedência");
    }
}
