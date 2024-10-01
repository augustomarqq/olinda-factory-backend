package br.olinda.factory.model.entities;

import java.util.List;

public class Setor {

    private int id;
    private String nome;
    
    private List<Funcionario> funcionarios;
    private List<Relato> relatos;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    public List<Relato> getRelatos() {
        return relatos;
    }
    public void setRelatos(List<Relato> relatos) {
        this.relatos = relatos;
    }
    
}