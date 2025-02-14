package com.epicode.U2_W3_D5_ViaggiAziendali.controller;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Prenotazione;
import com.epicode.U2_W3_D5_ViaggiAziendali.payload.PrenotazioneRequest;
import com.epicode.U2_W3_D5_ViaggiAziendali.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // Ottenere tutte le prenotazioni
    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    // Creare una nuova prenotazione
    @PostMapping("/crea")
    public ResponseEntity<Prenotazione> creaPrenotazione(@RequestBody PrenotazioneRequest request) {
        Prenotazione prenotazione = prenotazioneService.creaPrenotazione(
                request.getDipendenteId(), request.getViaggioId(), request.getDataRichiesta(), request.getNote());
        return ResponseEntity.ok(prenotazione);
    }

    // Eliminare una prenotazione
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
