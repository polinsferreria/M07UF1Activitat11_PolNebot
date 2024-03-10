package com.mycompany.m07uf1activitat11_polnebot.dades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuracio")
public class Configuracio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idconf;
    
    @Column(name = "tempsPrestec")
    private int tempsPrestec;

    @Column(name = "tempsSancio")
    private int tempsSancio;

    // Constructores, getters y setters
    public Configuracio() {
        // Constructor vacío necesario para JPA
    }

    public Configuracio(int tempsPrestec, int tempsSancio) {
        this.tempsPrestec = tempsPrestec;
        this.tempsSancio = tempsSancio;
    }

    public int getTempsPrestec() {
        return tempsPrestec;
    }

    public void setTempsPrestec(int tempsPrestec) {
        this.tempsPrestec = tempsPrestec;
    }

    public int getTempsSancio() {
        return tempsSancio;
    }

    public void setTempsSancio(int tempsSancio) {
        this.tempsSancio = tempsSancio;
    }

    // Otros métodos, si es necesario
    @Override
    public String toString() {
        return "Configuracio{"
                + "tempsPrestec=" + tempsPrestec
                + ", tempsSancio=" + tempsSancio
                + '}';
    }

    public int getIdconf() {
        return idconf;
    }

    public void setIdconf(int idconf) {
        this.idconf = idconf;
    }
}
