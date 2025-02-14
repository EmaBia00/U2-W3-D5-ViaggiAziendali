package com.epicode.U2_W3_D5_ViaggiAziendali.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrenotazioneRequest {

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private Long dipendenteId;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private Long viaggioId;

    @NotNull(message = "La data richiesta è obbligatoria")
    @FutureOrPresent(message = "La data della prenotazione non può essere nel passato")
    private LocalDate dataRichiesta;

    private String note;
}
