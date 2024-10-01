package br.olinda.factory.controllers.rest;

import br.olinda.factory.DTOs.FuncionarioDTO;
import br.olinda.factory.model.entities.Funcionario;
import br.olinda.factory.model.entities.Setor;
import br.olinda.factory.model.repositories.FuncionarioRepository;
import br.olinda.factory.model.repositories.SetorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("funcionarios")
@CrossOrigin("*")
public class FuncionarioController {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private final SetorRepository setorRepository = new SetorRepository();

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.nome());

        
        try {
            Setor setor = setorRepository.read(funcionarioDTO.setorId());
            if (setor == null) {
                return ResponseEntity.badRequest().build();
            }
            funcionario.setSetor(setor);
            funcionario.setCargo(funcionarioDTO.cargo());

            funcionarioRepository.create(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> read(@PathVariable int id) {
        try {
            Funcionario funcionario = funcionarioRepository.read(id);
            if (funcionario == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(funcionario);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> readAll() {
        try {
            List<Funcionario> funcionarios = funcionarioRepository.readAll();
            return ResponseEntity.ok(funcionarios);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<List<Funcionario>> searchBySetor(@PathVariable int setorId) {
        try {
            List<Funcionario> funcionarios = funcionarioRepository.searchBySetor(setorId);
            return ResponseEntity.ok(funcionarios);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
