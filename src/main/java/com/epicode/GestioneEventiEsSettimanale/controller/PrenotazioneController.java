package com.epicode.GestioneEventiEsSettimanale.controller;

import com.epicode.GestioneEventiEsSettimanale.model.Prenotazione;
import com.epicode.GestioneEventiEsSettimanale.payload.PrenotazioneDTO;
import com.epicode.GestioneEventiEsSettimanale.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Prenotazione> prenotaPosti(@RequestBody PrenotazioneDTO prenotazioneDTO, @RequestHeader("utenteId") Long utenteId) {
        Prenotazione prenotazione = eventoService.prenotaPosti(prenotazioneDTO.getEventoId(), utenteId, prenotazioneDTO.getNumeroPosti());
        if (prenotazione != null) {
            return new ResponseEntity<>(prenotazione, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> annullaPrenotazione(@PathVariable Long id, @RequestHeader("utenteId") Long utenteId) {
        eventoService.annullaPrenotazione(id, utenteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
