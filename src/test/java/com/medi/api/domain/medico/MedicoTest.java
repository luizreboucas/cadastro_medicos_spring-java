package com.medi.api.domain.medico;

import com.medi.api.domain.endereco.DadosEndereco;
import com.medi.api.domain.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureTestEntityManager
class MedicoTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("deve retornar um médico atualizado")
    public void updateTest(){
        var endereco = new DadosEndereco("rua feliz n 5", "parquelandai", "423434", "fortaleza", "CE", "78", "");
        var dadosMedico = new DadosCadastroMedicoDTO("luiz", "luiz@gmail", "3719273874", "473847", Especialidade.CARDIOLOGIA, endereco);
        var medico = createMedico(dadosMedico);
        var novosDados = new MedicoUpdateDTO(medico.getId(),"luiza",null,null);
        medico.update(novosDados);
        var result = entityManager.find(Medico.class, medico.getId());

        assertThat(result).isEqualTo(new Medico(medico.getId(), "luiza", "luiz@gmail", "3719273874", "473847", true,Especialidade.CARDIOLOGIA, new Endereco(endereco)));

    }

    @Test
    @DisplayName("deve desativar um médico")
    public void desativarCenario1(){
        var endereco = new DadosEndereco("rua feliz n 5", "parquelandai", "423434", "fortaleza", "CE", "78", "");
        var dadosMedico = new DadosCadastroMedicoDTO("luiz", "luiz@gmail", "3719273874", "473847", Especialidade.CARDIOLOGIA, endereco);
        var medico = createMedico(dadosMedico);
        medico.desativar();
        assertThat(medico.getAtivo()).isFalse();
    }

    private Medico createMedico(DadosCadastroMedicoDTO medicoupdate){
        var medico = new Medico(medicoupdate);
        entityManager.persist(medico);
        return medico;
    }

}