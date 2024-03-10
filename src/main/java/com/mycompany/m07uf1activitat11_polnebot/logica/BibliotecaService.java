/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot.logica;

import com.mycompany.m07uf1activitat11_polnebot.dades.Llibre;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author pol^^
 */
public class BibliotecaService {

    private LlibreDAO llibreDAO;
    private BaldesDAO baldesDAO;
    private ConfiguracioDAO configuracioDAO;
    private PrestatgesDAO prestatgesDAO;
    private PersonalDAO personalDAO;
    private PrivilegisDAO privilegisDAO;
    private TipusFonsDAO tipusFonsDAO;
    private TipusUsuariDAO tipusUsuariDAO;
    private UsuarisDAO usuarisDAO;
    
    public static EntityManagerFactory emf;
    public static EntityManager em;

    public BibliotecaService() {
        emf = Persistence.createEntityManagerFactory("com.mycompany_m07uf1ACT11_PolNebot_jar_1.0-SNAPSHOT_PU");
        em = emf.createEntityManager();
        
        llibreDAO = new LlibreDAO();
        baldesDAO = new BaldesDAO();
        configuracioDAO = new ConfiguracioDAO();
        prestatgesDAO = new PrestatgesDAO();
        personalDAO = new PersonalDAO();
        privilegisDAO = new PrivilegisDAO();
        tipusFonsDAO = new TipusFonsDAO();
        tipusUsuariDAO = new TipusUsuariDAO();
        usuarisDAO = new UsuarisDAO();
        
        
        
    }

    public PrestatgesDAO getPrestatgesDAO() {
        return prestatgesDAO;
    }

    public LlibreDAO getLlibreDAO() {
        return llibreDAO;
    }

    public BaldesDAO getBaldesDAO() {
        return baldesDAO;
    }

    public ConfiguracioDAO getConfiguracioDAO() {
        return configuracioDAO;
    }

    public PersonalDAO getPersonalDAO() {
        return personalDAO;
    }

    public PrivilegisDAO getPrivilegisDAO() {
        return privilegisDAO;
    }

    public TipusFonsDAO getTipusFonsDAO() {
        return tipusFonsDAO;
    }

    public TipusUsuariDAO getTipusUsuariDAO() {
        return tipusUsuariDAO;
    }

    public UsuarisDAO getUsuarisDAO() {
        return usuarisDAO;
    }
    
    /*
    private EntityManagerFactory emf;
    private EntityManager em;

    public BibliotecaService() {
        emf = Persistence.createEntityManagerFactory("com.mycompany_m07uf1ACT11_PolNebot_jar_1.0-SNAPSHOT_PU");
        em = emf.createEntityManager();
    }

    public List<Llibre> obtenirTotsElsLlibres() {
        TypedQuery<Llibre> query = em.createQuery("SELECT l FROM Llibre l", Llibre.class);
        return query.getResultList();
    }

    public List<Usuaris> obtenirTotsElsUsuaris() {
        TypedQuery<Usuaris> query = em.createQuery("SELECT u FROM Usuaris u", Usuaris.class);
        return query.getResultList();
    }

    public List<TipusFons> obtenirTotsElsTipusFons() {
        TypedQuery<TipusFons> query = em.createQuery("SELECT t FROM TipusFons t", TipusFons.class);
        return query.getResultList();
    }

    public List<Prestatges> obtenirTotsElsPrestatges() {
        TypedQuery<Prestatges> query = em.createQuery("SELECT p FROM Prestatges p", Prestatges.class);
        return query.getResultList();
    }

    public List<Baldes> obtenirTotsElsBaldes() {
        TypedQuery<Baldes> query = em.createQuery("SELECT b FROM Baldes b", Baldes.class);
        return query.getResultList();
    }

    //<editor-fold defaultstate="collapsed" desc="Menú TipusFons">
    public String obtenirNomTipusFonsPerId(int idTipusFons) {
        TypedQuery<String> query = em.createQuery("SELECT tf.tipus FROM TipusFons tf WHERE tf.idTipusFons = :id", String.class);
        query.setParameter("id", idTipusFons);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar la situación en la que no se encuentra ningún TipusFons con la ID proporcionada
            return null;
        }
    }

    public boolean altaTipusFons(String tipus) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crea una instancia de TipusFons con los datos proporcionados
            TipusFons tipusFons = new TipusFons();
            tipusFons.setTipus(tipus);

            // Guarda el nuevo TipusFons en la base de datos
            em.persist(tipusFons);

            em.getTransaction().commit();
            return true; // Si no hay excepciones, consideramos que el alta fue exitosa.
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al dar de alta el TipusFons.
        } finally {
            em.close();
        }
    }

    public boolean baixaTipusFons(int idTipusFons) {
        TipusFons tipusFons = em.find(TipusFons.class, idTipusFons);
        if (tipusFons != null) {
            em.getTransaction().begin();
            em.remove(tipusFons);
            //this.em.refresh(tipusFons);
            em.getTransaction().commit();
            return true; // Éxito en la eliminación
        } else {
            return false;
        }

    }

    public boolean modificarTipusFons(int idTipusFons, String nouTipus) {
        //EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Busca el TipusFons existente por su ID
            TipusFons tipusFons = em.find(TipusFons.class, idTipusFons);

            if (tipusFons != null) {
                // Modifica el tipo del TipusFons
                tipusFons.setTipus(nouTipus);

                // Actualiza el TipusFons en la base de datos
                em.merge(tipusFons);

                //this.em.refresh(tipusFons);
                em.getTransaction().commit();
                return true; // Si no hay excepciones, consideramos que la modificación fue exitosa.
            } else {
                // Manejo de la situación donde el TipusFons no se encuentra
                System.out.println("TipusFons no encontrado con ID: " + idTipusFons);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al modificar el TipusFons.
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Prestatges">
    public String obtenirNomPrestatgePerId(int idPrestatge) {
        EntityManager em = emf.createEntityManager();

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

    public boolean altaPrestatges(String nomPrestatge) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crea una instancia de Prestatges con los datos proporcionados
            Prestatges prestatges = new Prestatges();
            prestatges.setNom(nomPrestatge);

            // Guarda el nuevo Prestatges en la base de datos
            em.persist(prestatges);

            em.getTransaction().commit();
            return true; // Si no hay excepciones, consideramos que el alta fue exitosa.
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al dar de alta el Prestatges.
        } finally {
            em.close();
        }
    }

    public boolean baixaPrestatges(int idPrestatges) {
        Prestatges prestatges = em.find(Prestatges.class, idPrestatges);
        if (prestatges != null) {
            em.getTransaction().begin();
            em.remove(prestatges);
            //this.em.refresh(prestatges);
            em.getTransaction().commit();
            return true; // Éxito en la eliminación
        } else {
            return false;
        }

    }

    public boolean modificarPrestatges(int idPrestatges, String nomPrestatge) {
        //EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Busca el Prestatges existente por su ID
            Prestatges prestatges = em.find(Prestatges.class, idPrestatges);

            if (prestatges != null) {
                // Modifica el tipo del Prestatges
                prestatges.setNom(nomPrestatge);

                // Actualiza el Prestatges en la base de datos
                em.merge(prestatges);

                //this.em.refresh(prestatges);
                em.getTransaction().commit();
                return true; // Si no hay excepciones, consideramos que la modificación fue exitosa.
            } else {
                // Manejo de la situación donde el TipusFons no se encuentra
                System.out.println("Prestatge no encontrado con ID: " + idPrestatges);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al modificar el Prestatges.
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Baldes">
    public String obtenirNomBaldaPerId(int idBalda) {
        EntityManager em = emf.createEntityManager();

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
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Baldes> query = em.createQuery("SELECT b FROM Baldes b WHERE b.idPrestatge = :idPrestatge", Baldes.class);
            query.setParameter("idPrestatge", idPrestatge);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean altaBaldes(String nomBalda, int idPrestatge) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crea una instancia de Baldes con los datos proporcionados
            Baldes baldes = new Baldes();
            baldes.setNom(nomBalda);
            baldes.setIdPrestatge(idPrestatge);
            // Guarda el nuevo Baldes en la base de datos
            em.persist(baldes);

            em.getTransaction().commit();
            return true; // Si no hay excepciones, consideramos que el alta fue exitosa.
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al dar de alta el baldes.
        } finally {
            em.close();
        }
    }

    public boolean baixaBaldes(int idBaldes) {
        Baldes baldes = em.find(Baldes.class, idBaldes);
        if (baldes != null) {
            em.getTransaction().begin();
            em.remove(baldes);
            //this.em.refresh(baldes);
            em.getTransaction().commit();
            return true; // Éxito en la eliminación
        } else {
            return false;
        }

    }

    public boolean modificarBaldes(int idBaldes, String nomBaldes, int idPrestatges) {
        //EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Busca el Baldes existente por su ID
            Baldes baldes = em.find(Baldes.class, idBaldes);

            if (baldes != null) {
                // Modifica el tipo del Baldes
                baldes.setNom(nomBaldes);
                baldes.setIdPrestatge(idPrestatges);

                // Actualiza el Baldes en la base de datos
                em.merge(baldes);

                //this.em.refresh(baldes);
                em.getTransaction().commit();
                return true; // Si no hay excepciones, consideramos que la modificación fue exitosa.
            } else {
                // Manejo de la situación donde el Baldes no se encuentra
                System.out.println("Balda no encontrado con ID: " + idBaldes);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al modificar el Baldes.
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Llibres">
    public boolean altaLlibre(int idTipusFons, String titol, String autor, String isbn,
            int quantitatDisponible, int idPrestatge, int idBalda) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crea una instancia de Llibre con los datos proporcionados
            Llibre llibre = new Llibre();
            llibre.setIdTipusfons(idTipusFons);
            llibre.setTitol(titol);
            llibre.setAutor(autor);
            llibre.setIsbn(isbn);
            llibre.setQuantitatDisponible(quantitatDisponible);
            llibre.setIdPrestatge(idPrestatge);
            llibre.setIdBalda(idBalda);

            // Guarda el nuevo Llibre en la base de datos
            em.persist(llibre);

            em.getTransaction().commit();
            return true; // Si no hay excepciones, consideramos que el alta fue exitosa.
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al dar de alta el libro.
        } finally {
            em.close();
        }
    }

    public boolean baixaLlibre(int idLlibre) {
        Llibre llibre = em.find(Llibre.class, idLlibre);
        if (llibre != null) {
            em.getTransaction().begin();
            em.remove(llibre);
            //this.em.refresh(baldes);
            em.getTransaction().commit();
            return true; // Éxito en la eliminación
        } else {
            return false;
        }
    }

    public boolean modificarLlibre(int idLlibre, int idTipusFons, String titol, String autor,
            String isbn, int quantitatDisponible, int idPrestatge, int idBalda) {
        try {
            em.getTransaction().begin();

            // Busca el libro existente por su ID
            Llibre llibre = em.find(Llibre.class, idLlibre);

            if (llibre != null) {
                // Modifica los atributos del libro
                llibre.setIdTipusfons(idTipusFons);
                llibre.setTitol(titol);
                llibre.setAutor(autor);
                llibre.setIsbn(isbn);
                llibre.setQuantitatDisponible(quantitatDisponible);
                llibre.setIdPrestatge(idPrestatge);
                llibre.setIdBalda(idBalda);

                // Actualiza el libro en la base de datos
                em.merge(llibre);

                em.getTransaction().commit();
                return true; // Modificación exitosa
            } else {
                // Manejo de la situación donde el libro no se encuentra
                System.out.println("Libro no encontrado con ID: " + idLlibre);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al modificar el libro.
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Usuaris">
    public boolean altaUsuari(String nomUsuari, String password, int idTipusUsuari) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Crea una instancia de Usuaris con los datos proporcionados
            Usuaris usuari = new Usuaris();
            usuari.setNomUsuari(nomUsuari);
            usuari.setPassword(password);
            usuari.setIdTipusUsuari(idTipusUsuari);

            // Guarda el nuevo usuario en la base de datos
            em.persist(usuari);

            em.getTransaction().commit();
            return true; // Si no hay excepciones, consideramos que el alta fue exitosa.
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al dar de alta al usuario.
        } finally {
            em.close();
        }
    }

    public boolean modificarUsuari(int idUsuari, String nomUsuari, String password, int idTipusUsuari) {
        //EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Buscar el usuario existente por ID
            Usuaris usuari = em.find(Usuaris.class, idUsuari);

            if (usuari != null) {
                // Actualizar los datos del usuario
                usuari.setNomUsuari(nomUsuari);
                usuari.setPassword(password);
                usuari.setIdTipusUsuari(idTipusUsuari);

                // Guardar los cambios en la base de datos
                em.merge(usuari);

                em.getTransaction().commit();
                this.em.refresh(usuari);
                return true; // Si no hay excepciones, consideramos que la modificación fue exitosa.
            } else {
                // Manejar el caso en que el usuario no se encuentra por el ID proporcionado
                System.err.println("Usuario no encontrado con ID: " + idUsuari);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al modificar al usuario.
        }
    }

    public boolean baixaUsuari(int idUsuari) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Busca el usuario en la base de datos utilizando su ID
            Usuaris usuarioEnBD = em.find(Usuaris.class, idUsuari);

            // Si el usuario existe, lo elimina
            if (usuarioEnBD != null) {
                em.remove(usuarioEnBD);
                em.getTransaction().commit();
                return true; // Éxito en la eliminación
            } else {
                // El usuario no existe en la base de datos
                return false; // No se pudo encontrar el usuario para eliminar
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Deshace la transacción en caso de excepción.
            }
            e.printStackTrace();
            return false; // Manejo de excepciones si ocurre algún error al eliminar al usuario.
        } finally {
            em.close();
        }
    }

    public boolean authenticateUser(String username, String password) {
        em.getTransaction().begin();

        TypedQuery<Usuaris> query = em.createQuery("SELECT u FROM Usuaris u WHERE u.nomUsuari = :username", Usuaris.class);
        query.setParameter("username", username);

        try {
            Usuaris user = query.getSingleResult();
            
            // Verificar la contraseña (la implementación real dependerá de cómo estén almacenadas las contraseñas)
            boolean isAuthenticated = checkPassword(password, user.getPassword());

            if (isAuthenticated) {
                // Autenticación exitosa
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            // Manejar la excepción (por ejemplo, usuario no encontrado)
            e.printStackTrace();
        }

        em.getTransaction().rollback();
        return false;
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        // Verificar la contraseña utilizando BCryptPasswordEncoder
        return inputPassword.equals(storedPassword);
    }
    //</editor-fold>

    public void closeEntityManager() {
        em.close();
        emf.close();
    }
     */

    
}
