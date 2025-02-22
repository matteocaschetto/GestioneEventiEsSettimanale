package com.epicode.GestioneEventiEsSettimanale.service;

import com.epicode.GestioneEventiEsSettimanale.model.Evento;
import com.epicode.GestioneEventiEsSettimanale.model.Prenotazione;
import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.repository.EventoRepository;
import com.epicode.GestioneEventiEsSettimanale.repository.PrenotazioneRepository;
import com.epicode.GestioneEventiEsSettimanale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public Evento creaEvento(Evento evento, Utente creatore) {
        evento.setCreatore(creatore);
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(Long id, Utente utente) {
        Evento evento = eventoRepository.findById(id).orElse(null);
        if (evento != null && evento.getCreatore().equals(utente)) {
            eventoRepository.delete(evento);
        }
    }

    public Evento modificaEvento(Evento evento, Utente utente) {
        Evento eventoEsistente = eventoRepository.findById(evento.getId()).orElse(null);
        if (eventoEsistente != null && eventoEsistente.getCreatore().equals(utente)) {
            evento.setCreatore(utente);
            return eventoRepository.save(evento);
        }
        return null;
    }

    public List<Evento> getAllEventi() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Prenotazione prenotaPosti(Long eventoId, Long utenteId, int numeroPosti) {
        Evento evento = eventoRepository.findById(eventoId).orElse(null);
        Utente utente = utenteRepository.findById(utenteId).orElse(null);

        if (evento != null && utente != null && evento.getPostiDisponibili() >= numeroPosti) {
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setUtente(utente);
            prenotazione.setEvento(evento);
            prenotazione.setDataPrenotazione(new Date());
            prenotazione.setNumeroPosti(numeroPosti);

            evento.setPostiDisponibili(evento.getPostiDisponibili() - numeroPosti);
            eventoRepository.save(evento); // Aggiorna i posti disponibili nell'evento

            return prenotazioneRepository.save(prenotazione); // Assicurati di avere il repository per Prenotazione
        }

        return null; // Gestisci i casi di errore (evento non trovato, utente non trovato, posti insufficienti)
    }

    public void annullaPrenotazione(Long prenotazioneId, Long utenteId) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId).orElse(null);
        if (prenotazione != null && prenotazione.getUtente().getId().equals(utenteId)) {
            Evento evento = prenotazione.getEvento();
            evento.setPostiDisponibili(evento.getPostiDisponibili() + prenotazione.getNumeroPosti());
            eventoRepository.save(evento);
            prenotazioneRepository.delete(prenotazione);
        }
    }
}
