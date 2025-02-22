package com.epicode.GestioneEventiEsSettimanale.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 15)
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ruolo; // "Utente Normale" o "Organizzatore di Eventi"

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;


    private String nome;
    private String cognome;




    @OneToMany(mappedBy = "utente") // Un utente può avere molte prenotazioni
    private List<Prenotazione> prenotazioni;

    public Utente(String username, String email, String password, String ruolo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }


}

