package com.medi.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query("""
            select c from Consulta c
            where
            c.paciente.id = :consulta.paciente_id
            and
            c.data_consulta = :consulta.data
            """)
    public Consulta existeConsultaNoMesmoDia(ConsultaDTO consulta);
}
