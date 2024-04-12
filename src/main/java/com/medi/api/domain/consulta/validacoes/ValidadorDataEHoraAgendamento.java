package com.medi.api.domain.consulta.validacoes;

import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.utils.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorDataEHoraAgendamento {

    public void validar(ConsultaDTO consulta){
        var dataConsulta = consulta.data();
        var eDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioDeAbertura = dataConsulta.getHour() < 7;
        var depoisDoHorarioDeAbertura = dataConsulta.getHour() > 18;

        if(eDomingo | antesDoHorarioDeAbertura | depoisDoHorarioDeAbertura) throw new ValidacaoException("consulta fora do horário de funcionamento");
    }
}
