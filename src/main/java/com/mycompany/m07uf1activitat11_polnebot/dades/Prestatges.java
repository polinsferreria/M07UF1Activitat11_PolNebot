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
@Table(name = "Prestatges")
public class Prestatges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestatge;

    @Column(name = "nom")
    private String nom;

    public Prestatges() {
    }
    
    public Prestatges(String nom) {
        this.nom = nom;
    }

    public Prestatges(int idPrestatge, String nom) {
        this.idPrestatge = idPrestatge;
        this.nom = nom;
    }
    
    

    public int getIdPrestatge() {
        return idPrestatge;
    }

    public void setIdPrestatge(int idPrestatge) {
        this.idPrestatge = idPrestatge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
}
