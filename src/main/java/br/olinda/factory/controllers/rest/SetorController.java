package br.olinda.factory.controllers.rest;

import br.olinda.factory.DTOs.SetorDTO;
import br.olinda.factory.model.entities.Setor;
import br.olinda.factory.model.repositories.SetorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("setores")
@CrossOrigin("*")
public class SetorController {

    private final SetorRepository repository = new SetorRepository();

    @GetMapping
    public ResponseEntity<List<Setor>> readAll() {
        try {
            List<Setor> setores = repository.readAll();
            return ResponseEntity.ok(setores);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SetorDTO setorDTO) {
        Setor setor = new Setor();
        setor.setNome(setorDTO.nome());

        try {
            repository.create(setor);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Setor> read(@PathVariable int id) {
        try {
            Setor setor = repository.read(id);
            return setor != null ? ResponseEntity.ok(setor) : ResponseEntity.notFound().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
