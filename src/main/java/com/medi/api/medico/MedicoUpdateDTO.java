package com.medi.api.medico;

import com.medi.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotBlank;

public record MedicoUpdateDTO(@NotBlank Long id, String nome, String telefone, DadosEndereco endereco) {
}
