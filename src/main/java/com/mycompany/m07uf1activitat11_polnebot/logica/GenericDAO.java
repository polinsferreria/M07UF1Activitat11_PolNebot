/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class GenericDAO<T> {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        
        emf = BibliotecaService.emf;
        em = BibliotecaService.em;
        //emf = Persistence.createEntityManagerFactory("com.mycompany_m07uf1ACT11_PolNebot_jar_1.0-SNAPSHOT_PU");
        //em = emf.createEntityManager();
    }
    public GenericDAO( EntityManager em, EntityManagerFactory emf){
        this.em = em;
        this.emf = emf;
    }

    // Resto del código...

    public List<T> obtenerTodos() {
        TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }

    public boolean altaEntidad(T entidad) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean bajaEntidad(Object id) {
        Object entidad = em.find(entityClass, id);
        if (entidad != null) {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }

    public boolean modificarEntidad(T entidad) {
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getEm() {
        return em;
    }

    // Otros métodos específicos podrían requerir una lógica más compleja debido a la naturaleza específica de las entidades.
    
    // ...

    public void cerrarEntityManager() {
        em.close();
        emf.close();
    }
}

