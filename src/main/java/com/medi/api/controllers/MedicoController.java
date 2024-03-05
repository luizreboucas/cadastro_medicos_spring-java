package com.medi.api.controllers;

import com.medi.api.medico.DadosCadastroMedicoDTO;
import com.medi.api.medico.Medico;
import com.medi.api.medico.MedicoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public Medico createMedico(@RequestBody DadosCadastroMedicoDTO dados){
        Medico medico = new Medico(dados);
        repository.save(medico);
        return medico;
    }

    @GetMapping
    @Transactional
    public List<Medico> getMedicos(){
        List<Medico> medicos = repository.findAll();
        return medicos;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Medico deleteMedico(@PathVariable Long id) throws Exception{
        Optional<Medico> medicoOptional = repository.findById(id);
        if(medicoOptional.isEmpty()) throw new Exception("Médico Não  encontrado");
        Medico medico = medicoOptional.get();
        repository.delete(medico);
        return medico;
    }

    @GetMapping("/{id}")
    @Transactional
    public Medico getMedico(@PathVariable Long id) throws Exception{
        Optional<Medico> optionalMedico= repository.findById(id);
        if(optionalMedico.isEmpty()) throw new Exception("Médico não encontrado");
        return optionalMedico.get();
    }
}
