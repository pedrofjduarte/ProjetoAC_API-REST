package com.projeto.ac.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCurso;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long cargaHoraria;

    @Column(nullable = false)
    private char status;

}
