package com.projeto.ac.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAluno;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String celular;

    @DateTimeFormat(pattern	="dd-MM-yyyy")
    @Column(nullable = false)
    private Date dataNascimento;

    @Column(nullable = false)
    private char status;

    @ManyToOne
    private Curso curso;

}
