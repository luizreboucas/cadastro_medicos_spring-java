package com.medi.api.controllers;

import com.medi.api.domain.consulta.ConsultaCompletaDTO;
import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.domain.consulta.ConsultaRepository;
import com.medi.api.service.ConsultaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    private ConsultaService consultaService;
    @PostMapping
    @Transactional
    public ResponseEntity<ConsultaCompletaDTO> criarConsulta(@RequestBody ConsultaDTO consulta){
        System.out.println("antes de criar a consulta");
        var consultaCompleta = consultaService.createConsulta(consulta);
        System.out.println(consultaCompleta);
        System.out.println("consulta deu certo!!!!!!!!!!!!!!");
        if(consultaCompleta == null) throw new RuntimeException("Não foi possível criar consulta");
        return ResponseEntity.ok(consultaCompleta);
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaCompletaDTO>> getConsultas(Pageable paginacao){
        var consultas = consultaRepository.findAll(paginacao).map(ConsultaCompletaDTO::new);
        return ResponseEntity.ok(consultas);
    }
}
