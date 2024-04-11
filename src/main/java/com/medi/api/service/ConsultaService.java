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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        var medico = getMedico(consultaDto);
        var paciente = pacienteRepository.findById(consultaDto.paciente_id()).get();
        var data = consultaDto.data();
        var consulta = new Consulta(null,medico,paciente,data);
        consultaRepository.save(consulta);
        return new ConsultaCompletaDTO(consulta);
    }

    public Page<ConsultaCompletaDTO> getConsultasService(Pageable paginacao){
        var consultas = consultaRepository.findAll(paginacao).map(ConsultaCompletaDTO::new);
        return consultas;
    }

    private Medico getMedico(ConsultaDTO consultaDTO){
        if(consultaDTO.medico_id() != null){
            if(!medicoRepository.existsById(consultaDTO.medico_id())) throw new RuntimeException("médico não encontrado");
            return medicoRepository.findById(consultaDTO.medico_id()).get();
        }
        return medicoRepository.getMedicoPorEspecialidade(consultaDTO.especialidade(), consultaDTO.data());
    }

}
