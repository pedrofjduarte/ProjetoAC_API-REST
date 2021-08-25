package com.projeto.ac.controller;


import com.projeto.ac.model.Curso;
import com.projeto.ac.repository.CursoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cursos")
@Api(value="API REST Aluno/Curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @ApiOperation(value="Retornar Cursos")
    @GetMapping
    public List<Curso> buscarCursos(){
        return cursoRepository.findAll();
    }

    @ApiOperation(value="Cadastrar Curso")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Curso adicionarCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }

    @ApiOperation(value="Retornar Curso")
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Curso> buscarCurso(@PathVariable long id){
        return cursoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value="Apagar Curso")
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> apagarCurso(@PathVariable("id") long id) {
        return cursoRepository.findById(id)
                .map(record -> {
                    cursoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value="Atualizar Curso")
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
