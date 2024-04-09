package com.medi.api.controllers;

import com.medi.api.domain.pacientes.Paciente;
import com.medi.api.domain.pacientes.PacienteCadastroDTO;
import com.medi.api.domain.pacientes.PacienteDTO;
import com.medi.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> getPacientes (Pageable paginacao){
        var pacientes = repository.findAll(paginacao).map(PacienteDTO::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPaciente (@PathVariable Long id){
        var paciente = repository.findById(id);
        if (paciente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PacienteDTO(paciente.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO newPacienteData){
        var newPaciente = repository.findById(id);
        if(newPaciente.isEmpty()) return ResponseEntity.notFound().build();
        newPaciente.get().update(newPacienteData);
        return ResponseEntity.ok(new PacienteDTO(newPaciente.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id){
        var paciente = repository.findById(id);
        if(paciente.isEmpty()) return ResponseEntity.notFound().build();
        repository.delete(paciente.get());
        return ResponseEntity.ok("Paciente deletado com sucesso");
    }

}
