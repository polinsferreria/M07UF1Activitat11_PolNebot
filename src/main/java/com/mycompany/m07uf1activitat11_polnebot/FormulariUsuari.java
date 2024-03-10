/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.TipusFons;
import com.mycompany.m07uf1activitat11_polnebot.dades.TipusUsuari;
import com.mycompany.m07uf1activitat11_polnebot.dades.Usuaris;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pol^^
 */
public class FormulariUsuari extends JDialog {

    private int idUsuario;
    private String nombreUsuario = null;
    private String contraUsuario = null;
    private int tipoUsuario = -1;

    private JTextField usernameField;
    private JPasswordField passwordField;
    //private JSpinner tipusUsuari;
    private JComboBox<String> tipusUsuari;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;

    public FormulariUsuari(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta d'usuari", true);
        this.bibliotecaService = bibliotecaService;

        formulariUsuari();
    }

    public FormulariUsuari(JFrame parent, int idUsuario, String nombreUsuario, String contraUsuario, int tipoUsuario, BibliotecaService bibliotecaService) {
        super(parent, "Modificar Usuari", true);
        this.idUsuario = idUsuario;
        this.bibliotecaService = bibliotecaService;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.contraUsuario = contraUsuario;

        formulariUsuari();

    }

    private boolean nomExiste(String nomUser) {
        boolean validar = false;
        java.util.List<Usuaris> usuarios = bibliotecaService.getUsuarisDAO().obtenerTodos();
        for (Usuaris user : usuarios) {
            if (nomUser.equals(user.getNomUsuari())) {
                validar = true;
                break;
            }
        }

        return validar;
    }

    private void formulariUsuari() {
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel para la entrada de datos
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        inputPanel.add(new JLabel("Nom d'usuari:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);

        inputPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Nova contrasenya:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        inputPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        inputPanel.add(new JLabel("Tipus Usuari [1-4]:"), gbc);

        gbc.gridx = 1;
        tipusUsuari = new JComboBox<>();
        cargarTipusUsuariEnComboBox();
        //tipusUsuari = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        inputPanel.add(tipusUsuari, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        confirmButton = new JButton("Confirmar");

        inputPanel.add(confirmButton, gbc);

        if (nombreUsuario != null && tipoUsuario != -1) {
            modificarUsuari();
        } else {
            AltaUsuari();
        }

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void AltaUsuari() {
        titleLabel.setText("Introduir dades del nou usuari");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo usuario
                String nuevoUsername = usernameField.getText();
                char[] nuevoPassword = passwordField.getPassword();
                //int tipoUsuario = (int) tipusUsuari.getValue();
                int tipoUsuario = extraerIdDesdeItem(tipusUsuari.getSelectedItem().toString());
                
                TipusUsuari tu = bibliotecaService.getTipusUsuariDAO().obtenirTipusUsuariPerId(tipoUsuario);
                boolean altaExitosa = false;
                if (!nomExiste(nuevoUsername)) {
                    // Lógica para dar de alta al usuario utilizando el servicio de la biblioteca
                    altaExitosa = bibliotecaService.getUsuarisDAO().altaEntidad(new Usuaris(nuevoUsername, new String(nuevoPassword), tu));
                } else {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Error al donar d'alta. El nom de l'usuari ja existeix", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Usuari donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Error al donar d'alta l'usuari", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarUsuari() {
        titleLabel.setText("Modificar dades de l'usuari");
        
                
        TipusUsuari u  =bibliotecaService.getTipusUsuariDAO().obtenirTipusUsuariPerId(tipoUsuario);
        String tipusUsuariItemToSelect = tipoUsuario+ ": "+ u.getTipus() + ": " +u.getIdPrivilegis() ;
        
        tipusUsuari.setSelectedItem(tipusUsuariItemToSelect);
        //tipusUsuari = new JSpinner(new SpinnerNumberModel(tipoUsuario, 1, 4, 1));
        
        usernameField.setText(nombreUsuario);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del usuario
                String nuevoUsername = usernameField.getText();
                char[] nuevaPassword = passwordField.getPassword();
                int nuevoTipoUsuario = extraerIdDesdeItem(tipusUsuari.getSelectedItem().toString());
                boolean modificacionExitosa = false;
                TipusUsuari tsc;
                tsc = bibliotecaService.getTipusUsuariDAO().obtenirTipusUsuariPerId(nuevoTipoUsuario);
                Usuaris u = new Usuaris(idUsuario, nuevoUsername, new String(nuevaPassword), tsc);
                if (!nomExiste(nuevoUsername)) {
                    // Lógica para modificar al usuario utilizando el servicio de la biblioteca
                    modificacionExitosa = bibliotecaService.getUsuarisDAO().modificarEntidad(new Usuaris(idUsuario, nuevoUsername, new String(nuevaPassword), tsc));
                            //modificarUsuari(idUsuario, nuevoUsername, new String(nuevaPassword), nuevoTipoUsuario);
                } else {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Error al modificar. El nom de l'usuari ja existeix", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Usuari modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariUsuari.this, "Error al modificar l'usuari", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
     private void cargarTipusUsuariEnComboBox() {
        java.util.List<TipusUsuari> tipusUsuariList = bibliotecaService.getTipusUsuariDAO().obtenerTodos();

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Agregar representación descriptiva al JComboBox
        for (TipusUsuari tipusUsuari : tipusUsuariList) {
            String item = tipusUsuari.getIdTipusUsuari()+ ": " + tipusUsuari.getTipus() +" : "+tipusUsuari.getIdPrivilegis();
            comboBoxModel.addElement(item);
        }

        tipusUsuari.setModel(comboBoxModel);
    }
    private int extraerIdDesdeItem(String selectedItem) {
        // Extraer la ID de la representación descriptiva del JComboBox
        String[] parts = selectedItem.split(": ");
        if (parts.length == 3) {
            return Integer.parseInt(parts[0]);
        } else {
            // Manejar el caso de un formato incorrecto
            return -1;
        }
    }
}
