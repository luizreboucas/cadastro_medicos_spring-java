package com.medi.api.domain.medico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medi.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedicoDTO dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.telefone = dados.telefone();
        this.ativo = true;
    }

    public void update(MedicoUpdateDTO medicoAtualizado) {
        if(medicoAtualizado.nome() != null) this.nome = medicoAtualizado.nome();
        if(medicoAtualizado.telefone() != null) this.telefone = medicoAtualizado.telefone();
        if(medicoAtualizado.endereco() != null) this.endereco.update(medicoAtualizado.endereco());
    }

    public void desativar(){
        this.ativo = false;
    }
}
