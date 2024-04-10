package com.medi.api.controllers;

import com.medi.api.domain.consulta.ConsultaCompletaDTO;
import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.service.ConsultaService;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
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
}
