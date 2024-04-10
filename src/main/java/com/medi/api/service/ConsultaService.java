package com.medi.api.service;

import com.medi.api.domain.consulta.Consulta;
import com.medi.api.domain.consulta.ConsultaCompletaDTO;
import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.domain.consulta.ConsultaRepository;
import com.medi.api.domain.medico.Medico;
import com.medi.api.domain.medico.MedicoRepository;
import com.medi.api.domain.pacientes.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public ConsultaCompletaDTO createConsulta(ConsultaDTO consultaDto){
        var medico = medicoRepository.findById(consultaDto.medico_id()).get();
        var paciente = pacienteRepository.findById(consultaDto.paciente_id()).get();
        var data = consultaDto.data();
        var consulta = new Consulta(null,medico,paciente,data);
        consultaRepository.save(consulta);
        return new ConsultaCompletaDTO(consulta);
    }

}
