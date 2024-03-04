package com.medi.api.medico;

import com.medi.api.endereco.DadosEndereco;

public record DadosCadastroMedicoDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco) {}
