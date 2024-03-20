package com.medi.api.domain.medico;

import com.medi.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;

public record MedicoUpdateDTO(@NotBlank Long id, String nome, String telefone, DadosEndereco endereco) {
}
