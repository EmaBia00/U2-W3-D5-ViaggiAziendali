package com.epicode.U2_W3_D5_ViaggiAziendali.controller;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Dipendente;
import com.epicode.U2_W3_D5_ViaggiAziendali.exception.ResourceNotFoundException;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.DipendenteRepository;
import com.epicode.U2_W3_D5_ViaggiAziendali.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    // Ottenere tutti i dipendenti
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    // Ottenere un dipendente per ID
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        return ResponseEntity.ok(dipendente);
    }

    // Creare un nuovo dipendente
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody Dipendente dipendente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dipendenteRepository.save(dipendente));
    }

    // Aggiornare un dipendente
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @RequestBody Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));

        dipendente.setNome(dipendenteDetails.getNome());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());
        dipendente.setUsername(dipendenteDetails.getUsername());

        return ResponseEntity.ok(dipendenteRepository.save(dipendente));
    }

    // Eliminare un dipendente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));
        dipendenteRepository.delete(dipendente);
        return ResponseEntity.noContent().build();
    }

    // Upload immagine profilo su Cloudinary
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadImmagine(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));

        try {
            String imageUrl = cloudinaryService.uploadFile(file);
            dipendente.setImmagineProfilo(imageUrl);
            dipendenteRepository.save(dipendente);
            return ResponseEntity.ok("Immagine caricata con successo: " + imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nel caricamento dell'immagine");
        }
    }
}
