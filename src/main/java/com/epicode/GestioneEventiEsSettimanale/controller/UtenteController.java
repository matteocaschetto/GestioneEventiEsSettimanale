package com.epicode.GestioneEventiEsSettimanale.controller;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping
    public ResponseEntity<Utente> creaUtente(@RequestBody Utente utente) {
        Utente nuovoUtente = utenteService.creaUtente(utente);
        return new ResponseEntity<>(nuovoUtente, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Utente> findByUsername(@PathVariable String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Utente> findByEmail(@PathVariable String email) {
        Utente utente = utenteService.findByEmail(email);
        if (utente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }
}
