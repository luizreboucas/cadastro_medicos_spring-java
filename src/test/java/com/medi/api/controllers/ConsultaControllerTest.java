package com.medi.api.controllers;

import com.medi.api.domain.consulta.ConsultaCompletaDTO;
import com.medi.api.domain.consulta.ConsultaDTO;
import com.medi.api.domain.medico.Especialidade;
import com.medi.api.domain.medico.Medico;
import com.medi.api.domain.pacientes.Paciente;
import com.medi.api.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    JacksonTester<ConsultaDTO> consultaJson;
    @Autowired
    JacksonTester<ConsultaCompletaDTO> consultaCompletaJson;

    @MockBean
    ConsultaService consultaService;


    @Test
    @DisplayName("Deveria retornar um código 400")
    @WithMockUser
    void criarConsultaCenario1() throws Exception {
        var response = mvc.perform(post("/consulta"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria retornar um status 200")
    @WithMockUser
    void criarConsultaCenario2() throws Exception {
        var medico = new Medico();
        var paciente = new Paciente();
        medico.setId(1L);
        paciente.setId(1L);
        var dataConsulta = LocalDateTime.now().plusHours(4);
        var jsonRetornado = consultaCompletaJson.write(new ConsultaCompletaDTO(1L,medico,paciente,dataConsulta)).getJson();
        when(consultaService.createConsulta(any())).thenReturn(new ConsultaCompletaDTO(1L,medico,paciente,dataConsulta));
        var response = mvc.perform(post("/consulta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consultaJson.write(new ConsultaDTO(1L,1L, dataConsulta, Especialidade.CARDIOLOGIA)).getJson()))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        System.out.println(jsonRetornado);
        assertThat(response.getContentAsString()).isEqualTo(jsonRetornado);
    }


}