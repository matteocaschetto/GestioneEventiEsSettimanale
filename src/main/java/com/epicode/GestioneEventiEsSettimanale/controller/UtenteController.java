package com.epicode.GestioneEventiEsSettimanale.controller;

import com.epicode.GestioneEventiEsSettimanale.model.Utente;
import com.epicode.GestioneEventiEsSettimanale.payload.request.LoginRequest;
import com.epicode.GestioneEventiEsSettimanale.payload.request.RegistrazioneRequest;
import com.epicode.GestioneEventiEsSettimanale.payload.response.JwtResponse;
import com.epicode.GestioneEventiEsSettimanale.repository.UtenteRepository;
import com.epicode.GestioneEventiEsSettimanale.security.jwt.JwtUtils;
import com.epicode.GestioneEventiEsSettimanale.service.UtenteService;
import com.epicode.GestioneEventiEsSettimanale.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 360)
@RestController
@RequestMapping("/api/utenti") // Modificato il path
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UtenteRepository utenteRepository;

    @PostMapping("/registrazione") // Aggiunto endpoint per la registrazione
    public ResponseEntity<?> registraUtente(@RequestBody RegistrazioneRequest registrazioneRequest) {
        if (utenteRepository.existsByUsername(registrazioneRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username è già in uso!");
        }

        if (utenteRepository.existsByEmail(registrazioneRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email è già in uso!");
        }

        // Crea un nuovo utente
        Utente user = new Utente(registrazioneRequest.getUsername(),
                registrazioneRequest.getEmail(),
                encoder.encode(registrazioneRequest.getPassword()),
                registrazioneRequest.getRuolo().toString());

        utenteRepository.save(user);

        return ResponseEntity.ok("Utente registrato con successo!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> ruoli = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                ruoli));
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/organizzatore") // Modificato il ruolo
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }
}
