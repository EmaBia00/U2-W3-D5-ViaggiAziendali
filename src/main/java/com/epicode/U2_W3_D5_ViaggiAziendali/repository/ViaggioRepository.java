package com.epicode.U2_W3_D5_ViaggiAziendali.repository;
import com.epicode.U2_W3_D5_ViaggiAziendali.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> { }