package com.epicode.U2_W3_D5_ViaggiAziendali.controller;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Viaggio;
import com.epicode.U2_W3_D5_ViaggiAziendali.exception.ResourceNotFoundException;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioRepository viaggioRepository;

    // Ottenere tutti i viaggi
    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    // Ottenere un viaggio per ID
    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioById(@PathVariable Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));
        return ResponseEntity.ok(viaggio);
    }

    // Creare un nuovo viaggio
    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(viaggioRepository.save(viaggio));
    }

    // Aggiornare un viaggio
    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable Long id, @RequestBody Viaggio viaggioDetails) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        viaggio.setDestinazione(viaggioDetails.getDestinazione());
        viaggio.setData(viaggioDetails.getData());
        viaggio.setStato(viaggioDetails.getStato());

        return ResponseEntity.ok(viaggioRepository.save(viaggio));
    }

    // Eliminare un viaggio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));
        viaggioRepository.delete(viaggio);
        return ResponseEntity.noContent().build();
    }

    // Modificare lo stato del viaggio
    @PatchMapping("/{id}/stato")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable Long id, @RequestParam Viaggio.StatoViaggio stato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        viaggio.setStato(stato);
        return ResponseEntity.ok(viaggioRepository.save(viaggio));
    }
}
