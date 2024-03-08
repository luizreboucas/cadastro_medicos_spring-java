package com.medi.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(DadosEndereco dados){
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.uf = dados.uf();
        this.complemento = dados.complemento();
    }

    public void update(DadosEndereco enderecoAtualizado){
        if(enderecoAtualizado.cep() != null) this.cep = enderecoAtualizado.cep();
        if(enderecoAtualizado.logradouro() != null) this.logradouro = enderecoAtualizado.logradouro();
        if(enderecoAtualizado.uf() != null) this.uf = enderecoAtualizado.uf();
        if(enderecoAtualizado.bairro() != null) this.bairro = enderecoAtualizado.bairro();
        if(enderecoAtualizado.cidade() != null) this.cidade = enderecoAtualizado.cidade();
        if(enderecoAtualizado.complemento() != null) this.complemento = enderecoAtualizado.complemento();
        if(enderecoAtualizado.numero() != null) this.numero = enderecoAtualizado.numero();
    }
}
