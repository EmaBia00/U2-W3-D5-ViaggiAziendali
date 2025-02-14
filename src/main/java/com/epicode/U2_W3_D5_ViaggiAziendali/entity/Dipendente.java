package com.epicode.U2_W3_D5_ViaggiAziendali.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(unique = true, nullable = false)
    private String email;

    private String immagineProfilo;
}

