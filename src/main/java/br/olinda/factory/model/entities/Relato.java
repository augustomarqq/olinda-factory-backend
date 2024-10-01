package br.olinda.factory.model.entities;

import java.util.Date;

public class Relato {

    private int id;
    private Setor setor;
    private Problema problema;
    private Date data;

    public Relato(){}
    public Relato(Setor setor, Problema problema){
        this.setor = setor;
        this.problema = problema;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    public Problema getProblema() {
        return problema;
    }
    public void setProblema(Problema problema) {
        this.problema = problema;
    }
    
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }

}