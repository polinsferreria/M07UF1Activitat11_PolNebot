package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.Baldes;
import com.mycompany.m07uf1activitat11_polnebot.dades.Llibre;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestatges;
import com.mycompany.m07uf1activitat11_polnebot.dades.TipusFons;
import com.mycompany.m07uf1activitat11_polnebot.dades.Usuaris;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author pol^^
 */
public class MenuPrincipal extends JFrame {

    private BibliotecaService bibliotecaService;
    private Usuaris Usuarilogin;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JMenuBar menuBar;
    private JMenu usuarisMenu;
    private JMenu menuPrincipal;
    private JMenu llibresMenu;
    private JMenu prestatgesMenu;
    private JMenu BaldesMenu;
    private JMenu TipusFonsMenu;
    private JMenu menuAjuda;
    private JPanel loginPanel;
    private int tipoConsulta;//[1-3]usuaris, [4-6]Llibres, [7-9]MantenimentFons, [10-12]Prestatges, [13-15]Baldes

    public MenuPrincipal(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
        createMenu();
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Menú Principal");

        // Agregar componentes y manejar eventos según sea necesario
        createLoginComponents();

        setVisible(true);
    }

    private void createLoginComponents() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado exterior

        JLabel titleLabel = new JLabel("Iniciar Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        loginPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        loginPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                Usuarilogin = new Usuaris();
                Usuarilogin.setNomUsuari(username);
                Usuarilogin.setPassword(new String(password));

                System.out.println("Contraseña ingresada: " + new String(password));
                // Realizar la autenticación con el servicio de biblioteca
                boolean isAuthenticated = bibliotecaService.authenticateUser(username, new String(password));

                if (isAuthenticated) {

                    // Mostrar el menú principal después de iniciar sesión
                    MostrarMenuSegunPermisos();

                    // Hacer invisible el login
                    loginPanel.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(MenuPrincipal.this, "Inicio de sesión fallido", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpia los campos después de intentar iniciar sesión
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        loginPanel.add(loginButton, gbc);

        add(loginPanel);
    }

    private void createMenu() {

        menuBar = new JMenuBar();

        //<editor-fold defaultstate="collapsed" desc="Menú Principal">
        // Menú Principal
        menuPrincipal = new JMenu("Menú Principal");

        // Submenús
        JMenuItem IniciarSesión = new JMenuItem("Cambiar Sesión");
        IniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción "Iniciar Sesión"
                BibliotecaService bibliotecaService = new BibliotecaService();
                dispose();
                MenuPrincipal menuPrincipal = new MenuPrincipal(bibliotecaService);
            }
        });

        JMenuItem altaLlibreItem = new JMenuItem("Alta Llibre");
        altaLlibreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción "Alta Llibre"
                JOptionPane.showMessageDialog(MenuPrincipal.this, "Seleccionaste Alta Llibre", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem baixaLlibreItem = new JMenuItem("Baixa Llibre");
        baixaLlibreItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción "Baixa Llibre"
                JOptionPane.showMessageDialog(MenuPrincipal.this, "Seleccionaste Baixa Llibre", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem exitItem = new JMenuItem("Sortir");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                System.exit(0);
            }
        });

        menuPrincipal.add(IniciarSesión);
        menuPrincipal.add(altaLlibreItem);
        menuPrincipal.add(baixaLlibreItem);
        menuPrincipal.addSeparator();
        menuPrincipal.add(exitItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Llibres">
        // Menú Llibres
        llibresMenu = new JMenu("Llibres");
        JMenuItem altaLlibreMenuItem = new JMenuItem("Alta");
        altaLlibreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                FormulariLlibres fl = new FormulariLlibres(MenuPrincipal.this, bibliotecaService);
            }
        });

        JMenuItem baixaLlibreMenuItem = new JMenuItem("Modifica");
        baixaLlibreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                tipoConsulta = 6;
                cargarConsulta("Modificar Llibres");
            }
        });

        JMenuItem modificaLlibreMenuItem = new JMenuItem("Baixa");
        modificaLlibreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                tipoConsulta = 5;
                cargarConsulta("Baixa Llibres");
            }
        });

        JMenuItem consultaLlibreMenuItem = new JMenuItem("Consulta");
        consultaLlibreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                tipoConsulta = 4;
                cargarConsulta("Consulta de Llibres");
            }
        });

        llibresMenu.add(altaLlibreMenuItem);
        llibresMenu.add(baixaLlibreMenuItem);
        llibresMenu.add(modificaLlibreMenuItem);
        llibresMenu.add(consultaLlibreMenuItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Prestatges">
        // Menú Manteniment fons
        prestatgesMenu = new JMenu("Prestatges");

        JMenuItem altesPrestatgesMenuItem = new JMenuItem("Altes");
        altesPrestatgesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para Alta de Prestatges
                FormulariPrestatges fp = new FormulariPrestatges(MenuPrincipal.this, bibliotecaService);
            }
        });

        JMenuItem modificacionsPrestatgesMenuItem = new JMenuItem("Modificacions");
        modificacionsPrestatgesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar Prestatges
                tipoConsulta = 12;
                cargarConsulta("Modificar Prestatges");
            }
        });

        JMenuItem baixesPrestatgesMenuItem = new JMenuItem("Baixes");
        baixesPrestatgesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar Prestatges
                tipoConsulta = 11;
                cargarConsulta("Eliminar Prestatges");
            }
        });

        JMenuItem consultaPrestatgesMenuItem = new JMenuItem("Consulta");
        consultaPrestatgesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para conultar Prestatges
                tipoConsulta = 10;
                cargarConsulta("Consulta de Prestatges");
            }
        });

        prestatgesMenu.add(altesPrestatgesMenuItem);
        prestatgesMenu.add(modificacionsPrestatgesMenuItem);
        prestatgesMenu.add(baixesPrestatgesMenuItem);
        prestatgesMenu.add(consultaPrestatgesMenuItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Baldes">
        // Menú Manteniment fons
        BaldesMenu = new JMenu("Baldes");

        JMenuItem altesBaldesMenuItem = new JMenuItem("Altes");
        altesBaldesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para Alta de Baldes
                FormulariBaldes fb = new FormulariBaldes(MenuPrincipal.this, bibliotecaService);
            }
        });

        JMenuItem modificacionsBaldesMenuItem = new JMenuItem("Modificacions");
        modificacionsBaldesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar Baldes
                tipoConsulta = 15;
                cargarConsulta("Modificar Baldes");
            }
        });

        JMenuItem baixesBaldesMenuItem = new JMenuItem("Baixes");
        baixesBaldesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar Baldes
                tipoConsulta = 14;
                cargarConsulta("Eliminar Baldes");
            }
        });

        JMenuItem consultaBaldesMenuItem = new JMenuItem("Consulta");
        consultaBaldesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para conultar Baldes
                tipoConsulta = 13;
                cargarConsulta("Consulta de Baldes");
            }
        });

        BaldesMenu.add(altesBaldesMenuItem);
        BaldesMenu.add(modificacionsBaldesMenuItem);
        BaldesMenu.add(baixesBaldesMenuItem);
        BaldesMenu.add(consultaBaldesMenuItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Tipus fons">
        // Menú Manteniment fons
        TipusFonsMenu = new JMenu("Tipus fons");

        JMenuItem altesTipusFonsMenuItem = new JMenuItem("Altes");
        altesTipusFonsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para altesMantenimentFonsMenuItem
                FormulariTipusFons fmf = new FormulariTipusFons(MenuPrincipal.this, bibliotecaService);
            }
        });

        JMenuItem modificacionsTipusFonsMenuItem = new JMenuItem("Modificacions");
        modificacionsTipusFonsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para modificar tipus fons
                tipoConsulta = 9;
                cargarConsulta("Modificar tipus fons");
            }
        });

        JMenuItem baixesTipusFonsMenuItem = new JMenuItem("Baixes");
        baixesTipusFonsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar tipus fons
                tipoConsulta = 8;
                cargarConsulta("Eliminar tipus fons");
            }
        });

        JMenuItem consultaTipusFonsMenuItem = new JMenuItem("Consulta");
        consultaTipusFonsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para conultar tipus fons
                tipoConsulta = 7;
                cargarConsulta("Consulta de Llibres");
            }
        });

        TipusFonsMenu.add(altesTipusFonsMenuItem);
        TipusFonsMenu.add(modificacionsTipusFonsMenuItem);
        TipusFonsMenu.add(baixesTipusFonsMenuItem);
        TipusFonsMenu.add(consultaTipusFonsMenuItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Usuaris">
        // Menú Usuaris
        usuarisMenu = new JMenu("Usuaris");
        JMenuItem altaUsuariMenuItem = new JMenuItem("Alta");
        altaUsuariMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para dar de alta a un usuario
                FormulariUsuari fu = new Formular56iUsuari(MenuPrincipal.this, bibliotecaService);

            }
        });

        JMenuItem baixaUsuariMenuItem = new JMenuItem("Baixa");
        baixaUsuariMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción "Baixa Usuari"
                tipoConsulta = 2;
                cargarConsulta("Eliminar un usuari");

            }
        });

        JMenuItem modificaUsuariMenuItem = new JMenuItem("Modifica");
        modificaUsuariMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción "Modificar Usuario"
                tipoConsulta = 3;
                cargarConsulta("Modificar un usuari");
            }
        });

        JMenuItem consultaUsuariMenuItem = new JMenuItem("Consulta");
        consultaUsuariMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para salir de la aplicación
                tipoConsulta = 1;
                cargarConsulta("Consulta d'usuaris");
            }
        });

        usuarisMenu.add(altaUsuariMenuItem);
        usuarisMenu.add(baixaUsuariMenuItem);
        usuarisMenu.add(modificaUsuariMenuItem);
        usuarisMenu.add(consultaUsuariMenuItem);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Menú Ajuda">
        // Menú Ajuda
        menuAjuda = new JMenu("Ajuda");

        JMenuItem tutorialItem = new JMenuItem("Tutorial");
        tutorialItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar el tutorial
                JOptionPane.showMessageDialog(MenuPrincipal.this, "Bienvenido al tutorial", "Tutorial", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menuAjuda.add(tutorialItem);
        //</editor-fold>

        // Agregar menús a la barra de menú
        menuBar.add(usuarisMenu);
        //menuBar.add(Préstec);
        menuBar.add(menuAjuda);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);

        menuBar.setVisible(true);
    }

    private void cargarConsulta(String tipoConsulta) {
        if (this.tipoConsulta <= 3) {
            //<editor-fold defaultstate="collapsed" desc="Consulta Usuaris">
            ArrayList<Usuaris> usuaris = (ArrayList<Usuaris>) bibliotecaService.obtenirTotsElsUsuaris(); // Obtener ArrayList de usuarios

            Object[][] data = new Object[usuaris.size()][3];

            for (int i = 0; i < usuaris.size(); i++) {
                Usuaris usuari = usuaris.get(i);
                data[i][0] = usuari.getIdUsuari();
                data[i][1] = usuari.getNomUsuari();
                data[i][2] = usuari.getIdTipusUsuari();
            }
            String[] columnNames = {"ID", "Nom", "Tipus Usuari"};
            ConsultaUsuaris(columnNames, data, tipoConsulta);
            System.out.println("imprimir Usuaris");
            //</editor-fold>
        } else if (this.tipoConsulta >= 4 && this.tipoConsulta <= 6) {
            //<editor-fold defaultstate="collapsed" desc="Consulta Llibres">
            ArrayList<Llibre> llibres = (ArrayList<Llibre>) bibliotecaService.obtenirTotsElsLlibres(); // Obtener ArrayList de Llibres

            Object[][] data = new Object[llibres.size()][9];

            for (int i = 0; i < llibres.size(); i++) {
                Llibre llibre = llibres.get(i);
                data[i][0] = llibre.getIdLlibre();
                data[i][1] = llibre.getIdCodi();
                data[i][2] = llibre.getIdTipusfons();
                data[i][3] = llibre.getTitol();
                data[i][4] = llibre.getAutor();
                data[i][5] = llibre.getIsbn();
                data[i][6] = llibre.getQuantitatDisponible();
                data[i][7] = llibre.getIdPrestatge();
                data[i][8] = llibre.getIdBalda();
            }

            String[] columnNames = {"ID", "IdCodi", "IdTipusFons", "Titol", "Autor", "ISBN", "Quantitat Disponible", "idPrestatge", "idBalda"};
            ConsultaLlibres(columnNames, data, tipoConsulta);
            System.out.println("imprimir Llibres");
            //</editor-fold>
        } else if (this.tipoConsulta >= 7 && this.tipoConsulta <= 9) {
            //<editor-fold defaultstate="collapsed" desc="Consulta MantenimentFons">
            ArrayList<TipusFons> tipusFons = (ArrayList<TipusFons>) bibliotecaService.obtenirTotsElsTipusFons(); // Obtener ArrayList de tipusFons

            Object[][] data = new Object[tipusFons.size()][2];

            for (int i = 0; i < tipusFons.size(); i++) {
                TipusFons tipus = tipusFons.get(i);
                data[i][0] = tipus.getIdTipusFons();
                data[i][1] = tipus.getTipus();
            }
            String[] columnNames = {"ID", "Tipus"};
            ConsultaTipusFons(columnNames, data, tipoConsulta);
            System.out.println("imprimir TipusFons");
            //</editor-fold>
        } else if (this.tipoConsulta >= 10 && this.tipoConsulta <= 12) {
            //<editor-fold defaultstate="collapsed" desc="Consulta Prestatges">
            ArrayList<Prestatges> prestatges = (ArrayList<Prestatges>) bibliotecaService.obtenirTotsElsPrestatges(); // Obtener ArrayList de Prestatges

            Object[][] data = new Object[prestatges.size()][2];

            for (int i = 0; i < prestatges.size(); i++) {
                Prestatges prestatge = prestatges.get(i);
                data[i][0] = prestatge.getIdPrestatge();
                data[i][1] = prestatge.getNom();
            }
            String[] columnNames = {"ID", "Nom"};
            ConsultaPrestatges(columnNames, data, tipoConsulta);
            System.out.println("imprimir prestatges");
            //</editor-fold>
        } else if (this.tipoConsulta >= 13 && this.tipoConsulta <= 15) {
            //<editor-fold defaultstate="collapsed" desc="Consulta Baldes">
            ArrayList<Baldes> baldes = (ArrayList<Baldes>) bibliotecaService.obtenirTotsElsBaldes(); // Obtener ArrayList de Baldes

            Object[][] data = new Object[baldes.size()][3];

            for (int i = 0; i < baldes.size(); i++) {
                Baldes Balda = baldes.get(i);
                data[i][0] = Balda.getIdBalda();
                data[i][1] = Balda.getNom();
                data[i][2] = Balda.getIdPrestatge();
            }
            String[] columnNames = {"ID", "Nom", "ID Prestatge"};
            ConsultaBaldes(columnNames, data, tipoConsulta);
            System.out.println("imprimir baldes");
            //</editor-fold>
        }
    }

    private void ConsultaLlibres(String[] columnNames, Object[][] data, String tipoConsulta) {
        JDialog dialog = new JDialog(MenuPrincipal.this, tipoConsulta, true);
        dialog.setSize(700, 400);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón de Cancelar/Salir
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo al hacer clic en Cancelar
            }
        });

        if (this.tipoConsulta == 5) { // dar de baja Libros
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idLlibre = (int) table.getValueAt(selectedRow, 0);
                            String nomUsuari = (String) table.getValueAt(selectedRow, 1);//pol haz q devulva la id y la guardas arriba ------------------------------------------------------------------
                            //comprobar q no sea el usuario q se esta usando :D -------------------------------------------------------------------------------

                            // Preguntar al usuario si desea eliminar el Libro seleccionado
                            int option = JOptionPane.showConfirmDialog(dialog, "¿Desea eliminar este libro?", "Eliminar libro", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Libro utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.baixaLlibre(idLlibre);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog(dialog, "Libro eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // refrescar la tabla
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    cargarConsulta(tipoConsulta);

                                } else {
                                    JOptionPane.showMessageDialog(dialog, "Error al eliminar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            });
        } else if (this.tipoConsulta == 6) { // modificar Libros
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idLlibre = (int) table.getValueAt(selectedRow, 0);
                            int idCodi = (int) table.getValueAt(selectedRow, 1);
                            int idTipusFons = (int) table.getValueAt(selectedRow, 2);
                            String titol = (String) table.getValueAt(selectedRow, 3);
                            String autor = (String) table.getValueAt(selectedRow, 4);
                            String isbn = (String) table.getValueAt(selectedRow, 5);
                            int quantitatDisponible = (int) table.getValueAt(selectedRow, 6);
                            int idPrestatge = (int) table.getValueAt(selectedRow, 7);
                            int idBalda = (int) table.getValueAt(selectedRow, 8);
                            // Mostrar un formulario de modificación
                            FormulariLlibres modificarDialog = new FormulariLlibres(MenuPrincipal.this, idLlibre, idCodi, idTipusFons, titol, autor, isbn, quantitatDisponible, idPrestatge, idBalda, bibliotecaService);

                            // refrescar la tabla
                            dialog.setVisible(false);
                            dialog.dispose();
                            cargarConsulta(tipoConsulta);
                        }
                    }
                }
            });

        } else if (this.tipoConsulta == 4) { // consulta Libros
            cancelButton.setText("<- Atrás");
        }

        // Agregar el botón de Cancelar/Salir al panel del diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        // Establecer la operación al cerrar para que se cierre correctamente
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }

    
    private void ConsultaBaldes(String[] columnNames, Object[][] data, String tipoConsulta) {
        JDialog dialog = new JDialog(MenuPrincipal.this, tipoConsulta, true);
        dialog.setSize(400, 300);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón de Cancelar/Salir
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo al hacer clic en Cancelar
            }
        });

        if (this.tipoConsulta == 14) {//dar de baixa Baldes
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idBalda = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea eliminar el Baldes seleccionado
                            int option = JOptionPane.showConfirmDialog(dialog, "¿Desea eliminar esta balda?", "Eliminar balda", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Prestatges utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.baixaBaldes(idBalda);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog(dialog, "Balda eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    //refrescar la tabla 
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    cargarConsulta(tipoConsulta);

                                } else {
                                    JOptionPane.showMessageDialog(dialog, "Error al eliminar el balda", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            });
        } else if (this.tipoConsulta == 15) {//modificar Baldes 
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idBalda = (int) table.getValueAt(selectedRow, 0);
                            String nomBalda = (String) table.getValueAt(selectedRow, 1);
                            int idPrestatges = (int) table.getValueAt(selectedRow, 2);

                            // Mostrar un formulario de modificación
                            FormulariBaldes modificarDialog = new FormulariBaldes(MenuPrincipal.this, idBalda, nomBalda, idPrestatges, bibliotecaService);

                            //refrescar la tabla 
                            dialog.setVisible(false);
                            dialog.dispose();
                            cargarConsulta(tipoConsulta);
                        }
                    }
                }
            });

        } else if (this.tipoConsulta == 13) {//consulta Baldes
            cancelButton.setText("<- Enrrera");
        }

        // Agregar el botón de Cancelar/Salir al panel del diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        // Establecer la operación al cerrar para que se cierre correctamente
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }

    private void ConsultaPrestatges(String[] columnNames, Object[][] data, String tipoConsulta) {
        JDialog dialog = new JDialog(MenuPrincipal.this, tipoConsulta, true);
        dialog.setSize(400, 300);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón de Cancelar/Salir
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo al hacer clic en Cancelar
            }
        });

        if (this.tipoConsulta == 11) {//dar de baixa Prestatges
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idPrestatges = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea Prestatges el Prestatge seleccionado
                            int option = JOptionPane.showConfirmDialog(dialog, "¿Desea eliminar este prestatge?", "Eliminar prestatge", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Prestatges utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.baixaPrestatges(idPrestatges);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog(dialog, "Prestatge eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    //refrescar la tabla 
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    cargarConsulta(tipoConsulta);

                                } else {
                                    JOptionPane.showMessageDialog(dialog, "Error al eliminar el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            });
        } else if (this.tipoConsulta == 12) {//modificar Prestatges 
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idPrestatges = (int) table.getValueAt(selectedRow, 0);
                            String nomPrestatge = (String) table.getValueAt(selectedRow, 1);

                            // Mostrar un formulario de modificación
                            FormulariPrestatges modificarDialog = new FormulariPrestatges(MenuPrincipal.this, idPrestatges, nomPrestatge, bibliotecaService);

                            //refrescar la tabla 
                            dialog.setVisible(false);
                            dialog.dispose();
                            cargarConsulta(tipoConsulta);
                        }
                    }
                }
            });

        } else if (this.tipoConsulta == 10) {//consulta Prestatges
            cancelButton.setText("<- Enrrera");
        }

        // Agregar el botón de Cancelar/Salir al panel del diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        // Establecer la operación al cerrar para que se cierre correctamente
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }

    private void ConsultaTipusFons(String[] columnNames, Object[][] data, String tipoConsulta) {
        JDialog dialog = new JDialog(MenuPrincipal.this, tipoConsulta, true);
        dialog.setSize(400, 300);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón de Cancelar/Salir
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo al hacer clic en Cancelar
            }
        });

        if (this.tipoConsulta == 8) {//dar de baixa TipusFons
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idTipusFons = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea eliminar el TipusFons seleccionado
                            int option = JOptionPane.showConfirmDialog(dialog, "¿Desea eliminar este tipusFons?", "Eliminar tipusFons", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el TipusFons utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.baixaTipusFons(idTipusFons);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog(dialog, "TipusFons eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    //refrescar la tabla 
                                    dialog.setVisible(false);
                                    cargarConsulta(tipoConsulta);

                                } else {
                                    JOptionPane.showMessageDialog(dialog, "Error al eliminar el tipusFons", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            });
        } else if (this.tipoConsulta == 9) {//modificar TipusFons 
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idTipusFons = (int) table.getValueAt(selectedRow, 0);
                            String tipusFons = (String) table.getValueAt(selectedRow, 1);

                            // Mostrar un formulario de modificación
                            FormulariTipusFons modificarDialog = new FormulariTipusFons(MenuPrincipal.this, idTipusFons, tipusFons, bibliotecaService);

                            //refrescar la tabla 
                            dialog.setVisible(false);
                            cargarConsulta(tipoConsulta);
                        }
                    }
                }
            });

        } else if (this.tipoConsulta == 7) {//consulta TipusFons
            cancelButton.setText("<- Enrrera");
        }

        // Agregar el botón de Cancelar/Salir al panel del diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        // Establecer la operación al cerrar para que se cierre correctamente
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }

    private void ConsultaUsuaris(String[] columnNames, Object[][] data, String tipoConsulta) {
        JDialog dialog = new JDialog(MenuPrincipal.this, tipoConsulta, true);
        dialog.setSize(400, 300);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Botón de Cancelar/Salir
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo al hacer clic en Cancelar
            }
        });

        if (this.tipoConsulta == 2) {//dar de baixa usuari
            // Agregar un listener para manejar la selección del usuario en la tabla
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idUsuari = (int) table.getValueAt(selectedRow, 0);
                            String nomusuari = (String) table.getValueAt(selectedRow, 0);

                            if (!nomusuari.equals(Usuarilogin.getNomUsuari())) {
                                // Preguntar al usuario si desea eliminar el usuario seleccionado
                                int option = JOptionPane.showConfirmDialog(dialog, "¿Desea eliminar este usuario?", "Eliminar Usuario", JOptionPane.YES_NO_OPTION);

                                if (option == JOptionPane.YES_OPTION) {
                                    // Lógica para eliminar el usuario utilizando el servicio de la biblioteca
                                    boolean eliminacionExitosa = bibliotecaService.baixaUsuari(idUsuari);
                                    if (eliminacionExitosa) {
                                        JOptionPane.showMessageDialog(dialog, "Usuario eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                        //refrescar la tabla 
                                        dialog.setVisible(false);
                                        cargarConsulta(tipoConsulta);

                                    } else {
                                        JOptionPane.showMessageDialog(dialog, "Error al eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(dialog, "Error, no puedes eliminar un mismo usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                }
            });
        } else if (this.tipoConsulta == 3) {//modificar usuari
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int idUsuari = (int) table.getValueAt(selectedRow, 0);
                            String nomUsuari = (String) table.getValueAt(selectedRow, 1);
                            int tipusUsuari = (int) table.getValueAt(selectedRow, 2);

                            // Mostrar un formulario de modificación
                            FormulariUsuari modificarDialog = new FormulariUsuari(MenuPrincipal.this, idUsuari, nomUsuari, tipusUsuari, bibliotecaService);

                            //refrescar la tabla 
                            dialog.setVisible(false);
                            cargarConsulta(tipoConsulta);
                        }
                    }
                }
            });

        } else if (this.tipoConsulta == 1) {//consulta usuari
            cancelButton.setText("<- Enrrera");
        }

        // Agregar el botón de Cancelar/Salir al panel del diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        // Establecer la operación al cerrar para que se cierre correctamente
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
    }

    private void MostrarMenuSegunPermisos() {
        // Implementa la lógica para mostrar el menú principal aquí
        // Puedes crear y mostrar otros JDialog, JFrame o componentes según sea necesario
        //JOptionPane.showMessageDialog(this, "Bienvenido al Menú Principal", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        menuBar.remove(usuarisMenu);
        menuBar.remove(menuAjuda);

        menuBar.add(menuPrincipal);
        menuBar.add(usuarisMenu);
        menuBar.add(llibresMenu);
        menuBar.add(prestatgesMenu);
        menuBar.add(BaldesMenu);
        menuBar.add(TipusFonsMenu);
        menuBar.add(menuAjuda);
        menuBar.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Inicializa las capas y la interfaz
                BibliotecaService bibliotecaService = new BibliotecaService();
                MenuPrincipal menuPrincipal = new MenuPrincipal(bibliotecaService);
            }
        });
    }
}
