package com.epicode.U2_W3_D5_ViaggiAziendali.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public enum StatoViaggio {
        IN_PROGRAMMA, COMPLETATO
    }
}

