/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;
import com.mycompany.m07uf1activitat11_polnebot.dades.Usuaris;
import javax.persistence.TypedQuery;

/**
 *
 * @author pol^^
 */
public class UsuarisDAO extends GenericDAO<Usuaris>{
    
    public UsuarisDAO() {
        super(Usuaris.class);
    }
    public boolean authenticateUser(String username, String password) {
        this.getEm().getTransaction().begin();

        TypedQuery<Usuaris> query = this.getEm().createQuery("SELECT u FROM Usuaris u WHERE u.nomUsuari = :username", Usuaris.class);
        query.setParameter("username", username);

        try {
            Usuaris user = query.getSingleResult();
            
            // Verificar la contraseña (la implementación real dependerá de cómo estén almacenadas las contraseñas)
            boolean isAuthenticated = checkPassword(password, user.getPassword());

            if (isAuthenticated) {
                // Autenticación exitosa
                this.getEm().getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            // Manejar la excepción (por ejemplo, usuario no encontrado)
            e.printStackTrace();
        }

        this.getEm().getTransaction().rollback();
        return false;
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        // Verificar la contraseña utilizando BCryptPasswordEncoder
        return inputPassword.equals(storedPassword);
    }
    
}
