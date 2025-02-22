package com.epicode.GestioneEventiEsSettimanale.service;

import com.epicode.GestioneEventiEsSettimanale.model.Prenotazione;
import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.repository.PrenotazioneRepository;
import com.epicode.GestioneEventiEsSettimanale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;



    public Utente creaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public Optional<Utente> findById(Long id){
        return utenteRepository.findById(id);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public List<Prenotazione> getPrenotazioni(Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId).orElse(null);
        if (utente != null) {
            return utente.getPrenotazioni(); // Assicurati di avere il getter per prenotazioni in Utente
        }
        return null;
    }
}
