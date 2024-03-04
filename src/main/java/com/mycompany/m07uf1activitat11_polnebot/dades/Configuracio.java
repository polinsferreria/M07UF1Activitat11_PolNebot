package com.mycompany.m07uf1activitat11_polnebot.dades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuracio")
public class Configuracio {

    @Id
    private int tempsPrestec;

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
        return "Configuracio{" +
                "tempsPrestec=" + tempsPrestec +
                ", tempsSancio=" + tempsSancio +
                '}';
    }
}

