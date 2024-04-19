package com.medi.api.controllers;
import com.medi.api.domain.medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity createMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> getMedicos(Pageable paginacao){
        var page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> getMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        if(!medico.getAtivo()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }



    @PutMapping
    @Transactional
    public ResponseEntity updateMedico(@RequestBody MedicoUpdateDTO medicoAtualizado){
        var medico = repository.getReferenceById(medicoAtualizado.id());
        medico.update(medicoAtualizado);
        DadosDetalhamentoMedicoDTO dadosDetalhamento = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.ok(dadosDetalhamento);
    }
}
