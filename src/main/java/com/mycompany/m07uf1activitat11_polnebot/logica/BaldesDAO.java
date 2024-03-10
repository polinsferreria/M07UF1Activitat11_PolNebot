/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;
import com.mycompany.m07uf1activitat11_polnebot.dades.Baldes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class BaldesDAO extends GenericDAO<Baldes>{
    
    public BaldesDAO() {
        super(Baldes.class);
    }
    public String obtenirNomBaldaPerId(int idBalda) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            TypedQuery<String> query = em.createQuery("SELECT b.nom FROM Baldes b WHERE b.idBalda = :id", String.class);
            query.setParameter("id", idBalda);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar la situación en la que no se encuentra ningún Baldes con la ID proporcionada
            return null;
        } finally {
            em.close();
        }
    }

    public List<Baldes> obtenirBaldesPerPrestatge(int idPrestatge) {
        EntityManager em = this.getEmf().createEntityManager();

        try {
            TypedQuery<Baldes> query = em.createQuery("SELECT b FROM Baldes b WHERE b.idPrestatge = :idPrestatge", Baldes.class);
            query.setParameter("idPrestatge", idPrestatge);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
