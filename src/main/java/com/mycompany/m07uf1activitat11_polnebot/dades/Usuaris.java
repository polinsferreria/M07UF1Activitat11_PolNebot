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
@Table(name = "Usuaris")
public class Usuaris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuari;
    
    @Column(name = "nomUsuari", unique = true)
    private String nomUsuari;

    @Column(name = "password")
    private String password;

    @Column(name = "idTipusUsuari")
    private int idTipusUsuari;

    public Usuaris(int idUsuari, String nomUsuari, String password, TipusUsuari tipusUsuari) {
        this.idUsuari = idUsuari;
        this.nomUsuari = nomUsuari;
        this.password = password;
        this.idTipusUsuari = tipusUsuari.getIdTipusUsuari();
        this.tipusUsuari = tipusUsuari;
    }

    public Usuaris(String nomUsuari, String password, TipusUsuari tipusUsuari) {
        this.nomUsuari = nomUsuari;
        this.password = password;
        this.idTipusUsuari = tipusUsuari.getIdTipusUsuari();
    }

    public Usuaris(){}
    
    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTipusUsuari() {
        return idTipusUsuari;
    }

    public void setIdTipusUsuari(int idTipusUsuari) {
        this.idTipusUsuari = idTipusUsuari;
    }

    
    
    @ManyToOne
    @JoinColumn(name = "idTipusUsuari", insertable = false, updatable = false)
    private TipusUsuari tipusUsuari;
}