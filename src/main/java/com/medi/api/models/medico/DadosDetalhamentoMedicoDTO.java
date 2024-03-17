package com.medi.api.models.medico;

import com.medi.api.endereco.Endereco;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedicoDTO(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(),medico.getCrm(), medico.getEspecialidade(),medico.getEndereco());
    }
}
