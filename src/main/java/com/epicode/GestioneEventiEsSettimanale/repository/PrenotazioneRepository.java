package com.epicode.GestioneEventiEsSettimanale.repository;

import com.epicode.GestioneEventiEsSettimanale.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

}
