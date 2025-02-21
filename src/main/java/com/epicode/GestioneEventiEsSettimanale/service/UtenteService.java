package com.epicode.GestioneEventiEsSettimanale.service;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
}
