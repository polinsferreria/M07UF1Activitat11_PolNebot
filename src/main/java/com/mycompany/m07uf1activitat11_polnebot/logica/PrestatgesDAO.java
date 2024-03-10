/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestatges;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class PrestatgesDAO extends GenericDAO<Prestatges>{
    
    public PrestatgesDAO() {
        super(Prestatges.class);
    }
    public String obtenirNomPrestatgePerId(int idPrestatge) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            TypedQuery<String> query = em.createQuery("SELECT p.nom FROM Prestatges p WHERE p.idPrestatge = :id", String.class);
            query.setParameter("id", idPrestatge);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar la situación en la que no se encuentra ningún prestatge con la ID proporcionada
            return null;
        } finally {
            em.close();
        }
    }

}
