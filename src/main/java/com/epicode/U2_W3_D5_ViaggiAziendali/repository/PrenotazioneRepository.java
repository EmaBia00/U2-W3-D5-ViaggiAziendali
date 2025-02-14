package com.epicode.U2_W3_D5_ViaggiAziendali.repository;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Dipendente;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
}