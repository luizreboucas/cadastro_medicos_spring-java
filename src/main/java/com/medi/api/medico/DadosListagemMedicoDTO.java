package com.medi.api.medico;

public record DadosListagemMedicoDTO (String nome, String email, Especialidade especialidade, String crm){

    public DadosListagemMedicoDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getEspecialidade(),medico.getCrm());
    }
}
