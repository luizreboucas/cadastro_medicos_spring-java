package com.medi.api.domain.pacientes;

import com.medi.api.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record PacienteCadastroDTO(@NotNull String nome,@NotNull String email, Endereco endereco) {
}
