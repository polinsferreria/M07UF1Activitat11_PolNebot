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
@Table(name = "TipusFons")
public class TipusFons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipusFons;

    @Column(name = "tipus")
    private String tipus;

    public int getIdTipusFons() {
        return idTipusFons;
    }

    public void setIdTipusFons(int idTipusFons) {
        this.idTipusFons = idTipusFons;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    
}