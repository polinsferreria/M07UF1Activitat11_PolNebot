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
@Table(name = "TipusUsuari")
public class TipusUsuari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipusUsuari;

    @Column(name = "tipus")
    private String tipus;

    @Column(name = "privilegis")
    private int privilegis;

    public int getIdTipusUsuari() {
        return idTipusUsuari;
    }

    public void setIdTipusUsuari(int idTipusUsuari) {
        this.idTipusUsuari = idTipusUsuari;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getPrivilegis() {
        return privilegis;
    }

    public void setPrivilegis(int privilegis) {
        this.privilegis = privilegis;
    }

    
}