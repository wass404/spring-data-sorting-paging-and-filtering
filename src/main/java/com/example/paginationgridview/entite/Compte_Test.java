package com.example.paginationgridview.entite;


import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;


public class Compte_Test {
    private long idTest;
    private long idCompte;
    private long solde;
    private long numero;
    private String nom;
    private String prenom;

    public Compte_Test() {
    }

    public Compte_Test(long idTest, long idComte, long solde, long numero, String nom, String prenom) {
        this.idTest = idTest;
        this.idCompte = idComte;
        this.solde = solde;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
    }



    public long getIdTest() {
        return idTest;
    }

    public void setIdTest(long idTest) {
        this.idTest = idTest;
    }

    public long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(long idCompte) {
        this.idCompte = idCompte;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
