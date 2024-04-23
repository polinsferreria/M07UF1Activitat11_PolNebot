/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.Prestatges;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pol^^
 */

public class FormulariPrestec extends JDialog {

    private int idPrestatges;
    private String nomPrestatges = null;

    private JTextField prestatgesField;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;
    

    public FormulariPrestec(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de prestatge", true);
        this.bibliotecaService = bibliotecaService;

        formulariPrestatges();
    }

    public FormulariPrestec(JFrame parent, int idPrestatges, String nomPrestatges, BibliotecaService bibliotecaService) {
        super(parent, "Modificar un prestatge", true);
        this.bibliotecaService = bibliotecaService;
        this.idPrestatges = idPrestatges;
        this.nomPrestatges = nomPrestatges;
        
        formulariPrestatges();

    }

    private void formulariPrestatges() {
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
        inputPanel.add(new JLabel("NomPrestatge:"), gbc);

        gbc.gridx = 1;
        prestatgesField = new JTextField(15);
        inputPanel.add(prestatgesField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        confirmButton = new JButton("Confirmar");

        inputPanel.add(confirmButton, gbc);

        if (nomPrestatges != null) {
            modificarPresec();
        } else {
            altaPresec();
        }
        
        
        
        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void altaPresec() {
        titleLabel.setText("Introduir dades del nou prestatge");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo prestatge
                String nuevoNomPrestatges = prestatgesField.getText();

                // Lógica para dar de alta el prestatge utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.getPrestatgesDAO().altaEntidad(new Prestatges(nuevoNomPrestatges));

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariPrestec.this, "Prestatge donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariPrestec.this, "Error al donar d'alta el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarPresec() {
        titleLabel.setText("Modificar dades del prestatge");
        prestatgesField.setText(nomPrestatges);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del prestatge
                String nuevoNomPrestatges = prestatgesField.getText();

                // Lógica para modificar el prestatge utilizando el servicio de la biblioteca
                boolean modificacionExitosa = bibliotecaService.getPrestatgesDAO().modificarEntidad(new Prestatges(idPrestatges, nuevoNomPrestatges));

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariPrestec.this, "Prestatge modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariPrestec.this, "Error al modificar el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}