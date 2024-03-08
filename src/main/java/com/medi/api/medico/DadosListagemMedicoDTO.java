package com.medi.api.medico;

public record DadosListagemMedicoDTO (Long id,String telefone,String nome, String email, Especialidade especialidade, String crm){

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getId(),medico.getTelefone(),medico.getNome(), medico.getEmail(), medico.getEspecialidade(),medico.getCrm());
    }
}
