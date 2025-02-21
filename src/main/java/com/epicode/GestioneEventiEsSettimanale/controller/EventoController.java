package com.epicode.GestioneEventiEsSettimanale.controller;

import com.epicode.GestioneEventiEsSettimanale.model.Evento;
import com.epicode.GestioneEventiEsSettimanale.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> creaEvento(@RequestBody Evento evento) {
        Evento nuovoEvento = eventoService.creaEvento(evento);
        return new ResponseEntity<>(nuovoEvento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaEvento(@PathVariable Long id) {
        eventoService.eliminaEvento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> modificaEvento(@PathVariable Long id, @RequestBody Evento evento) {
        evento.setId(id);
        Evento eventoModificato = eventoService.modificaEvento(evento);
        return new ResponseEntity<>(eventoModificato, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        List<Evento> eventi = eventoService.getAllEventi();
        return new ResponseEntity<>(eventi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Evento evento = eventoService.getEventoById(id);
        if (evento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }
}
