package com.epicode.U2_W3_D5_ViaggiAziendali.runner;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Dipendente;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Viaggio;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.DipendenteRepository;
import com.epicode.U2_W3_D5_ViaggiAziendali.repository.ViaggioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public DataInitializer(DipendenteRepository dipendenteRepository, ViaggioRepository viaggioRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    @Override
    public void run(String... args) {
        if (dipendenteRepository.count() == 0) {
            Dipendente dip1 = new Dipendente();
            dip1.setUsername("mrossi");
            dip1.setNome("Mario");
            dip1.setCognome("Rossi");
            dip1.setEmail("mario.rossi@example.com");
            dipendenteRepository.save(dip1);

            Dipendente dip2 = new Dipendente();
            dip2.setUsername("alverdi");
            dip2.setNome("Alessia");
            dip2.setCognome("Verdi");
            dip2.setEmail("alessia.verdi@example.com");
            dipendenteRepository.save(dip2);

            System.out.println("✅ Dipendenti inizializzati!");
        }

        if (viaggioRepository.count() == 0) {
            Viaggio viaggio1 = new Viaggio();
            viaggio1.setDestinazione("New York");
            viaggio1.setData(LocalDate.of(2025, 3, 15));
            viaggio1.setStato(Viaggio.StatoViaggio.IN_PROGRAMMA);
            viaggioRepository.save(viaggio1);

            Viaggio viaggio2 = new Viaggio();
            viaggio2.setDestinazione("Tokyo");
            viaggio2.setData(LocalDate.of(2025, 4, 10));
            viaggio2.setStato(Viaggio.StatoViaggio.IN_PROGRAMMA);
            viaggioRepository.save(viaggio2);

            System.out.println("✅ Viaggi inizializzati!");
        }
    }
}