package com.medi.api.domain.pacientes;

import com.medi.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name="pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @Embedded
    private Endereco endereco;

    public Paciente(PacienteCadastroDTO paciente){
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.endereco = paciente.endereco();
    }
}
