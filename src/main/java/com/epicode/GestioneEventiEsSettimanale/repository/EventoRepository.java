package com.epicode.GestioneEventiEsSettimanale.repository;

import com.epicode.GestioneEventiEsSettimanale.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
