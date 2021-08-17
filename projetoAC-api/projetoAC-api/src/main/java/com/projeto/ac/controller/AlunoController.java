package com.projeto.ac.controller;

import com.projeto.ac.model.Aluno;
import com.projeto.ac.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> buscarAlunos(){
        return alunoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  Aluno adicionarAluno(@RequestBody Aluno aluno){
        return alunoRepository.save(aluno);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Aluno> buscarAluno(@PathVariable long id){
        return alunoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> apagarAluno(@PathVariable("id") long id) {
        return alunoRepository.findById(id)
                .map(record -> {
                    alunoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable("id") long id,
                                                @RequestBody Aluno aluno){
        return alunoRepository.findById(id)
                .map(record -> {
                    record.setNome(aluno.getNome());
                    record.setEmail(aluno.getEmail());
                    record.setCelular(aluno.getCelular());
                    record.setDataNascimento(aluno.getDataNascimento());
                    record.setStatus(aluno.getStatus());
                    record.setCurso(aluno.getCurso());
                    Aluno updated = alunoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
