/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;

import com.mycompany.m07uf1activitat11_polnebot.dades.Llibre;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestec;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pol^^
 */
public class PrestecDAO extends GenericDAO<Prestec> {

    public PrestecDAO() {
        super(Prestec.class);
    }

    public int estaLibroEnPrestamo(Llibre libro) {
        int respuesta = 0;
        List<Prestec> prestamos = obtenerTodos();
        for (Prestec prestamo : prestamos) {
            if (prestamo.getIdLlibre() == libro.getIdLlibre()) {
                //esta en prestamo
                respuesta = 1;
                
                if (prestamo.getDataDevolucio().before(new Date())) {
                    // El libro está en préstamo si el id del libro coincide y la fecha de devolución es nula
                    //esta en prestamo y hay tiempo para devolver 
                    respuesta = 2;
                } else {
                    //esta en prestamo y NO hay tiempo para devolver 
                    respuesta = 3;
                    respuesta = comprobrarEntregado(prestamo,respuesta);
                }
                
            }
        }
        //el libro no esta en prestamo
        return respuesta;
    }
    private int comprobrarEntregado(Prestec p,int r){
        if (p.getDataEntrega() == null) {//no tiene fecha de entrega INCIDECIA
            r= 4;
        }
        return r;
    }
}
