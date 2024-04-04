package com.medi.api.domain.pacientes;

public record PacienteDTO(Long id, String email, String nome) {
    public PacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getEmail(),paciente.getNome());
    }
}
