package com.projeto.ac.controller;


import com.projeto.ac.model.Curso;
import com.projeto.ac.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> buscarCursos(){
        return cursoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Curso adicionarCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Curso> buscarCurso(@PathVariable long id){
        return cursoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> apagarCurso(@PathVariable("id") long id) {
        return cursoRepository.findById(id)
                .map(record -> {
                    cursoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable("id") long id,
                                          @RequestBody Curso curso){
        return cursoRepository.findById(id)
                .map(record -> {
                    record.setNome(curso.getNome());
                    record.setCargaHoraria(curso.getCargaHoraria());
                    record.setStatus(curso.getStatus());
                    Curso updated = cursoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
