package com.example.demo.controller;

import com.example.demo.model.Exames;
import com.example.demo.repository.ExamesJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4204")
@RestController
@RequestMapping("/exames")
public class ExamesController {
    @Autowired
    private ExamesJDBCRepository examesJDBCRepository;

    @GetMapping
    public List findAll(){
        return examesJDBCRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Exames> findById(@PathVariable("id") long id){
        return examesJDBCRepository.findById(id);
    }

    @GetMapping("/ordemCrescente")
    public List findByNomePacienteCrescente(){
        return  examesJDBCRepository.findByNomePacienteCrescente();
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable("id") long id){
        return examesJDBCRepository.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int insert(
            @RequestBody Exames exames
    ){
        return examesJDBCRepository.insert(exames);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable("id") long id, @RequestBody Exames exames){
        return examesJDBCRepository.findById(id)
                .map(record -> {
                    record.setNomeExame(exames.getNomeExame());
                    record.setNomePaciente(exames.getNomePaciente());
                    record.setResultadoExame(exames.getResultadoExame());
                    record.setDataExame(exames.getDataExame());
                    int updated = examesJDBCRepository.update(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


}
