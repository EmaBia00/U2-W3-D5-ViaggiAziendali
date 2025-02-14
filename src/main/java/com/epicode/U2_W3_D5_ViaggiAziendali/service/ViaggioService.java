package com.epicode.U2_W3_D5_ViaggiAziendali.service;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Viaggio;
import com.epicode.U2_W3_D5_ViaggiAziendali.exception.ResourceNotFoundException;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    public Viaggio getViaggioById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato con ID: " + id));
    }

    public Viaggio createViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Long id, Viaggio viaggioDetails) {
        Viaggio viaggio = getViaggioById(id);
        viaggio.setDestinazione(viaggioDetails.getDestinazione());
        viaggio.setData(viaggioDetails.getData());
        viaggio.setStato(viaggioDetails.getStato());
        return viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(Long id) {
        Viaggio viaggio = getViaggioById(id);
        viaggioRepository.delete(viaggio);
    }

    public Viaggio cambiaStatoViaggio(Long id, Viaggio.StatoViaggio nuovoStato) {
        Viaggio viaggio = getViaggioById(id);
        viaggio.setStato(nuovoStato);
        return viaggioRepository.save(viaggio);
    }
}