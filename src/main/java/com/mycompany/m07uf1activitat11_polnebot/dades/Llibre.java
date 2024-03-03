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

import javax.persistence.*;

@Entity
@Table(name = "Llibre") 
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;

    @Column(name = "idCodi")
    private int idCodi;

    @Column(name = "idTipusfons")
    private int idTipusfons;

    @Column(name = "titol")
    private String titol;

    @Column(name = "autor")
    private String autor;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "quantitatDisponible")
    private int quantitatDisponible;

    @Column(name = "idPrestatge")
    private int idPrestatge;

    @Column(name = "idBalda")
    private int idBalda;

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public int getIdCodi() {
        return idCodi;
    }

    public void setIdCodi(int idCodi) {
        this.idCodi = idCodi;
    }

    public int getIdTipusfons() {
        return idTipusfons;
    }

    public void setIdTipusfons(int idTipusfons) {
        this.idTipusfons = idTipusfons;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantitatDisponible() {
        return quantitatDisponible;
    }

    public void setQuantitatDisponible(int quantitatDisponible) {
        this.quantitatDisponible = quantitatDisponible;
    }

    public int getIdPrestatge() {
        return idPrestatge;
    }

    public void setIdPrestatge(int idPrestatge) {
        this.idPrestatge = idPrestatge;
    }

    public int getIdBalda() {
        return idBalda;
    }

    public void setIdBalda(int idBalda) {
        this.idBalda = idBalda;
    }

    

    @ManyToOne
    @JoinColumn(name = "idTipusfons", insertable = false, updatable = false)
    private TipusFons tipusFons;

    @ManyToOne
    @JoinColumn(name = "idPrestatge", insertable = false, updatable = false)
    private Prestatges prestatges;

    @ManyToOne
    @JoinColumn(name = "idBalda", insertable = false, updatable = false)
    private Baldes baldes;
}
