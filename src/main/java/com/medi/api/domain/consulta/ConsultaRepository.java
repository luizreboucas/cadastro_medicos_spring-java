package com.medi.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("""
            select c from Consulta c
            where
            c.medico.id = :medico_id
            and
            c.data_consulta = :data
            """)
    public Consulta existeConsultaNoMesmoDia(Long medico_id, LocalDateTime data);
}
