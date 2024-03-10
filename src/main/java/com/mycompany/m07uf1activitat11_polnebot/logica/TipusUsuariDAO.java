/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;
import com.mycompany.m07uf1activitat11_polnebot.dades.TipusUsuari;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class TipusUsuariDAO extends GenericDAO<TipusUsuari>{
    
    public TipusUsuariDAO() {
        super(TipusUsuari.class);
    }
    public TipusUsuari obtenirTipusUsuariPerId(int idTipusUsuari) {
        TypedQuery<TipusUsuari> query = this.getEm().createQuery("SELECT tu FROM TipusUsuari tu WHERE tu.idTipusUsuari = :id", TipusUsuari.class);
        query.setParameter("id", idTipusUsuari);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar la situación en la que no se encuentra ningún TipusFons con la ID proporcionada
            return null;
        }
    }
    
}
