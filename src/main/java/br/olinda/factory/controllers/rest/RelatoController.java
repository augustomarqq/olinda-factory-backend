package br.olinda.factory.controllers.rest;

import br.olinda.factory.DTOs.RelatoDTO;
import br.olinda.factory.model.entities.Relato;
import br.olinda.factory.model.entities.Setor;
import br.olinda.factory.model.entities.Problema;
import br.olinda.factory.model.repositories.RelatoRepository;
import br.olinda.factory.model.repositories.SetorRepository;
import br.olinda.factory.model.repositories.ProblemaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("relatos")
@CrossOrigin("*")
public class RelatoController {

    private final RelatoRepository relatoRepository = new RelatoRepository();
    private final SetorRepository setorRepository = new SetorRepository();
    private final ProblemaRepository problemaRepository = new ProblemaRepository();

    @GetMapping
    public ResponseEntity<List<Relato>> readAll() {
        try {
            List<Relato> relatos = relatoRepository.readAll();
            return ResponseEntity.ok(relatos);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RelatoDTO relatoDTO) {
        Relato relato = new Relato();

        try {
            // Buscar o Setor correspondente ao setorId
            Setor setor = setorRepository.read(relatoDTO.setorId());
            if (setor == null) {
                return ResponseEntity.badRequest().build();
            }
            relato.setSetor(setor);

            Problema problema = problemaRepository.read(relatoDTO.problemaId());
            if (problema == null) {
                return ResponseEntity.badRequest().build();
            }
            relato.setProblema(problema);

            relatoRepository.create(relato);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relato> read(@PathVariable int id) {
        try {
            Relato relato = relatoRepository.read(id);
            if (relato == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(relato);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<List<Relato>> searchBySetor(@PathVariable int setorId) {
        try {
            List<Relato> relatos = relatoRepository.searchBySetor(setorId);
            return ResponseEntity.ok(relatos);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}