package com.medi.api.domain.medico;

import com.medi.api.domain.consulta.Consulta;
import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.domain.consulta.ConsultaRepository;
import com.medi.api.domain.endereco.DadosEndereco;
import com.medi.api.domain.endereco.Endereco;
import com.medi.api.domain.pacientes.Paciente;
import com.medi.api.domain.pacientes.PacienteCadastroDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Em caso de nenhum médico com a especialdade desejada da consulta deve retornar null")
    void getMedicoPorEspecialidadeCenario1() {
        var medico = cadastrarMedico(setarDadosCadastroMedico("luiz","luiz@voll","854756475","4444", Especialidade.CARDIOLOGIA));
        var paciente = cadastrarPaciente(this.setarDadosCadastroPaciente("amanda", "amanda@amanda"));
        var proximaSegundaAsDez = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10);
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez, Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.getMedicoPorEspecialidade(Especialidade.GINECOLOGIA, LocalDateTime.now().plusHours(2));
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deve retornar um médico se um médico estiver disponível no dia da consulta")
    public void getMedicoPorEspecialidadeCenario2(){
        var medico = cadastrarMedico(setarDadosCadastroMedico("luiz","luiz@voll","854756475","4444", Especialidade.CARDIOLOGIA));
        var paciente = cadastrarPaciente(this.setarDadosCadastroPaciente("amanda", "amanda@amanda"));
        var proximaSegundaAsDez = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10);
        cadastrarConsulta(medico, paciente,proximaSegundaAsDez, Especialidade.CARDIOLOGIA);
        var medicoretorno = medicoRepository.getMedicoPorEspecialidade(Especialidade.CARDIOLOGIA, LocalDateTime.now().plusHours(2));
        assertThat(medicoretorno).isNotNull();
    }

    public Medico cadastrarMedico(DadosCadastroMedicoDTO medicodto){
        var medico = new Medico(medicodto);
        em.persist(medico);
        return medico;
    }
    public Paciente cadastrarPaciente(PacienteCadastroDTO pacientedto){
        var paciente = new Paciente(pacientedto);
        em.persist(paciente);
        return paciente;
    }
    public Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data, Especialidade especialidade){
        var consulta = new Consulta(null,medico,paciente,data);
        em.persist(consulta);
        return consulta;
    }

    public DadosCadastroMedicoDTO setarDadosCadastroMedico(String nome, String email, String telefone, String crm, Especialidade especialidade){
        var endereco = new DadosEndereco("rua xavier", "parquelandia", "312314","fortaleza","CE", "23E", "" );
        return new DadosCadastroMedicoDTO(nome, email, telefone, crm, Especialidade.CARDIOLOGIA, endereco);
    }
    public PacienteCadastroDTO setarDadosCadastroPaciente(String nome, String email){
        var dadosEndereco = new DadosEndereco("rua xavier", "parquelandia", "312314","fortaleza","CE", "23E", "" );
        var endereco = new Endereco(dadosEndereco);
        return new PacienteCadastroDTO(nome, email, endereco);
    }

    public ConsultaDTO setarConsultaDadastro(Long medicoId, Long pacienteId, LocalDateTime data, Especialidade especialidade){
        return new ConsultaDTO(medicoId,pacienteId,data, especialidade);
    }
}