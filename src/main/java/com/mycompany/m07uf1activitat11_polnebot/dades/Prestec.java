/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.dades;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author pol^^
 */

@Entity
@Table(name = "prestec")
public class Prestec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestec;

    @Column(name = "idLlibre")
    private int idLlibre;

    @Column(name = "idPersona")
    private int idPersona;

    @Column(name = "dataPrestec")
    private Date dataPrestec;

    @Column(name = "dataDevolucio")
    private Date dataDevolucio;
    
    @Column(name = "dataEntrega")
    private Date dataEntrega;
    
    public Prestec() {
        // Default constructor
    }

    public Prestec(int idLlibre, int idPersona, Date dataPrestec, Date dataDevolucio) {
        this.idLlibre = idLlibre;
        this.idPersona = idPersona;
        this.dataPrestec = dataPrestec;
        this.dataDevolucio = dataDevolucio;
    }

    public int getIdPrestec() {
        return idPrestec;
    }

    public void setIdPrestec(int idPrestec) {
        this.idPrestec = idPrestec;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Date getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(Date dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public Date getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(Date dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
}
