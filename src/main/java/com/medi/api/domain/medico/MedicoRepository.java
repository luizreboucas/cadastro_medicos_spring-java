package com.medi.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    @Query("""
            select m from Medico as m
            where m.especialidade = :especialidade
            and
            m.id not in(select c.medico.id from Consulta as c where c.data_consulta = :data)
            order by rand()
            limit 1
            """)
    Medico getMedicoPorEspecialidade(Especialidade especialidade, Date data);
}
