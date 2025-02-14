package com.epicode.U2_W3_D5_ViaggiAziendali.service;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Dipendente;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Prenotazione;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Viaggio;
import com.epicode.U2_W3_D5_ViaggiAziendali.exception.ConflictException;
import com.epicode.U2_W3_D5_ViaggiAziendali.exception.ResourceNotFoundException;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.DipendenteRepository;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.PrenotazioneRepository;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata con ID: " + id));
    }

    public Prenotazione creaPrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, dataRichiesta)) {
            throw new ConflictException("Il dipendente ha già una prenotazione per questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(dataRichiesta);
        prenotazione.setNote(note);

        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        Prenotazione prenotazione = getPrenotazioneById(id);
        prenotazioneRepository.delete(prenotazione);
    }
}