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
@Table(name = "Baldes")
public class Baldes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBalda;

    @Column(name = "nom")
    private String nom;

    @Column(name = "idPrestatge")
    private int idPrestatge;

    public Baldes(String nom, int idPrestatge) {
        this.nom = nom;
        this.idPrestatge = idPrestatge;
    }

    public Baldes(int idBalda, String nom, int idPrestatge) {
        this.idBalda = idBalda;
        this.nom = nom;
        this.idPrestatge = idPrestatge;
    }

    
    
    public int getIdBalda() {
        return idBalda;
    }

    public void setIdBalda(int idBalda) {
        this.idBalda = idBalda;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdPrestatge() {
        return idPrestatge;
    }

    public void setIdPrestatge(int idPrestatge) {
        this.idPrestatge = idPrestatge;
    }

    

    @ManyToOne
    @JoinColumn(name = "idPrestatge", insertable = false, updatable = false)
    private Prestatges prestatges;
}
