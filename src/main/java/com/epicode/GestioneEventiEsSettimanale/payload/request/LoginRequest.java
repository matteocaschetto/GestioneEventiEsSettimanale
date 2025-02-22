package com.epicode.GestioneEventiEsSettimanale.payload.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
public class LoginRequest {

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;


}
