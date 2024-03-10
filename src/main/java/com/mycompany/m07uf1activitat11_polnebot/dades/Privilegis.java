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
@Table(name = "Privilegis")
public class Privilegis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrivilegis;

    @Column(name = "nom")
    private String nom;

    

    public int getIdPrivilegis() {
        return idPrivilegis;
    }

    public void setIdPrivilegis(int idPrivilegis) {
        this.idPrivilegis = idPrivilegis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /*@Column(name = "privilegis")
    private int privilegis;
    public int getPrivilegis() {
        return privilegis;
    }

    public void setPrivilegis(int privilegis) {
        this.privilegis = privilegis;
    }*/

    
}