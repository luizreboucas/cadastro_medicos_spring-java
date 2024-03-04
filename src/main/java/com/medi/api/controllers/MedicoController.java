package com.medi.api.controllers;

import com.medi.api.medico.DadosCadastroMedicoDTO;
import com.medi.api.medico.Medico;
import com.medi.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void createMedico(@RequestBody DadosCadastroMedicoDTO dados){
        repository.save(new Medico(dados));
    }
}
