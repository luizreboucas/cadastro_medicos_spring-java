package com.medi.api.domain.pacientes;

import com.medi.api.domain.endereco.Endereco;

public record PacienteDTO(Long id, String email, String nome, Endereco endereco) {
    public PacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getEmail(),paciente.getNome(), paciente.getEndereco());
    }
}
