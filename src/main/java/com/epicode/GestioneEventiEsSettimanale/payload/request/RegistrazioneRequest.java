package com.epicode.GestioneEventiEsSettimanale.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    private String cognome;

    @NotBlank
    @Email
    private String email;

    private Set<String> ruolo;

}
