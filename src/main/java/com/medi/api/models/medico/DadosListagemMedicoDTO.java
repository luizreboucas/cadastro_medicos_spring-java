package com.medi.api.models.medico;

public record DadosListagemMedicoDTO (Long id,String telefone,String nome, String email, Especialidade especialidade, String crm, Boolean ativo){

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getId(),medico.getTelefone(),medico.getNome(), medico.getEmail(), medico.getEspecialidade(),medico.getCrm(), medico.getAtivo());
    }
}
