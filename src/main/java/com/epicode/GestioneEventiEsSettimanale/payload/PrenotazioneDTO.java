package com.epicode.GestioneEventiEsSettimanale.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {

    private Long eventoId;
    private int numeroPosti;

}
