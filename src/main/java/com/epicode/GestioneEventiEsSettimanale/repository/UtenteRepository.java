package com.epicode.GestioneEventiEsSettimanale.repository;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
    Utente findByEmail(String email);
    Optional<Utente> findById (Long id);
    boolean existsByUsername(@NotBlank @Size(min = 3, max = 15) String username);
    boolean existsByEmail(String email);
}
