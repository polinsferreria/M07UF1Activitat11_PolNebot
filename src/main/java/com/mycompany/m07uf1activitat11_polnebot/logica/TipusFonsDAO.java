/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;
import com.mycompany.m07uf1activitat11_polnebot.dades.TipusFons;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class TipusFonsDAO extends GenericDAO<TipusFons>{
    
    public TipusFonsDAO() {
        super(TipusFons.class);
    }
    public String obtenirNomTipusFonsPerId(int idTipusFons) {
        TypedQuery<String> query = this.getEm().createQuery("SELECT tf.tipus FROM TipusFons tf WHERE tu.idTipusFons = :id", String.class);
        query.setParameter("id", idTipusFons);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar la situación en la que no se encuentra ningún TipusFons con la ID proporcionada
            return null;
        }
    }

}
