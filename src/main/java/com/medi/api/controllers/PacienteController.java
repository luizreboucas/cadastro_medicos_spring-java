package com.medi.api.controllers;

import com.medi.api.domain.pacientes.Paciente;
import com.medi.api.domain.pacientes.PacienteCadastroDTO;
import com.medi.api.domain.pacientes.PacienteDTO;
import com.medi.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDTO> cadastraPaciente(@RequestBody PacienteCadastroDTO paciente){
        Paciente novoPaciente = new Paciente(paciente);
        repository.save(novoPaciente);
        return ResponseEntity.ok(new PacienteDTO(novoPaciente));
    }
}
