/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.dades;

import javax.persistence.*;

/**
 *
 * @author pol^^
 */

@Entity
@Table(name = "Personal")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonal;

    @Column(name = "nom")
    private String nom;

    @Column(name = "cognom")
    private String cognom;

    @Column(name = "idTipusUsuari")
    private int idTipusUsuari;

    @Column(name = "numeroCarnet")
    private String numeroCarnet;
    
    @Column(name = "sancionat", nullable = false)
    private int sancionat = 0;

    public Personal() {
    }
    
    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getIdTipusUsuari() {
        return idTipusUsuari;
    }

    public void setIdTipusUsuari(int idTipusUsuari) {
        this.idTipusUsuari = idTipusUsuari;
    }

    public String getNumeroCarnet() {
        return numeroCarnet;
    }

    public void setNumeroCarnet(String numeroCarnet) {
        this.numeroCarnet = numeroCarnet;
    }

    

    @ManyToOne
    @JoinColumn(name = "idTipusUsuari", insertable = false, updatable = false)
    private TipusUsuari tipusUsuari;
    
}