package com.projeto.ac.controller;


import com.projeto.ac.model.Curso;
import com.projeto.ac.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> listarCursos(){
       return cursoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Curso adicionarCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }
}
