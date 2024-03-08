package com.medi.api.controllers;

import com.medi.api.medico.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public Medico createMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        Medico medico = new Medico(dados);
        repository.save(medico);
        return medico;
    }

    @GetMapping
    public Page<DadosListagemMedicoDTO> getMedicos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(DadosListagemMedicoDTO::new);
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

    @PutMapping
    @Transactional
    public void updateMedico(@RequestBody MedicoUpdateDTO medicoAtualizado){
        var medico = repository.getReferenceById(medicoAtualizado.id());
        medico.update(medicoAtualizado);
    }
}
