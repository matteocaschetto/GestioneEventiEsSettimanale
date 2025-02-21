package com.epicode.GestioneEventiEsSettimanale.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ruolo; // "Utente Normale" o "Organizzatore di Eventi"

    @Column(unique = true)
    private String email;

    private String nome;
    private String cognome;
}

