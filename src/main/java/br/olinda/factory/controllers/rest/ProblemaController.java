package br.olinda.factory.controllers.rest;

import br.olinda.factory.DTOs.ProblemaDTO;
import br.olinda.factory.model.entities.Problema;
import br.olinda.factory.model.repositories.ProblemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("problemas")
@CrossOrigin("*")
public class ProblemaController {

    private final ProblemaRepository problemaRepository = new ProblemaRepository();

    @GetMapping
    public ResponseEntity<List<Problema>> readAll() {
        try {
            List<Problema> problemas = problemaRepository.readAll();
            return ResponseEntity.ok(problemas);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProblemaDTO problemaDTO) {
        Problema problema = new Problema();
        problema.setDescricao(problemaDTO.descricao());

        try {
            problemaRepository.create(problema);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Problema> read(@PathVariable int id) {
        try {
            Problema problema = problemaRepository.read(id);
            if (problema == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(problema);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
