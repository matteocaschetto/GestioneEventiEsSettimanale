package com.epicode.GestioneEventiEsSettimanale.service;

import com.epicode.GestioneEventiEsSettimanale.model.Evento;
import com.epicode.GestioneEventiEsSettimanale.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento creaEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    public Evento modificaEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }
}
