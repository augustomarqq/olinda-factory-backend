package br.olinda.factory.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Funcionario {

    private int id;
    private String nome;
    @JsonIgnore
    private Setor setor;
    private String cargo;

    public Funcionario(){}
    public Funcionario(String nome, Setor setor){
        this.nome = nome;
        this.setor = setor;
    }

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

    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}