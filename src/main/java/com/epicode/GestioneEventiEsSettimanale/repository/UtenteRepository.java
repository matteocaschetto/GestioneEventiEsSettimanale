package com.epicode.GestioneEventiEsSettimanale.repository;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
    Utente findByEmail(String email);
}
