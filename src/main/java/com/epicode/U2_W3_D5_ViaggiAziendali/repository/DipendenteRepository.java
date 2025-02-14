package com.epicode.U2_W3_D5_ViaggiAziendali.repository;

import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByUsername(String username);
}