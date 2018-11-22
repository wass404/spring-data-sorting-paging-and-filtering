package com.example.paginationgridview.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long solde;
    @Column
    private long numero;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "idTest")
    private Test test;

    public Compte() {
    }

    public Compte(long id, long solde, long numero, String description, Test test) {
        this.id = id;
        this.solde = solde;
        this.numero = numero;
        this.description = description;
        this.test = test;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSolde() {
        return solde;
    }

    public void setSolde(long solde) {
        this.solde = solde;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonIgnore
    public Test getTest() {
        return test;
    }
    @JsonSetter
    public void setTest(Test test) {
        this.test = test;
    }
}
