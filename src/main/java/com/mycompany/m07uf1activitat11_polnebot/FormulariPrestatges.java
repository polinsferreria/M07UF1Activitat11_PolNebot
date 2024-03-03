/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pol^^
 */
public class FormulariPrestatges extends JDialog {

    private int idPrestatges;
    private String nomPrestatges = null;

    private JTextField prestatgesField;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;

    public FormulariPrestatges(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de prestatge", true);
        this.bibliotecaService = bibliotecaService;

        formulariPrestatges();
    }

    public FormulariPrestatges(JFrame parent, int idPrestatges, String nomPrestatges, BibliotecaService bibliotecaService) {
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
            modificarTipusFons();
        } else {
            altaTipusFons();
        }

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void altaTipusFons() {
        titleLabel.setText("Introduir dades del nou prestatge");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo prestatge
                String nuevoNomPrestatges = prestatgesField.getText();

                // Lógica para dar de alta el prestatge utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.altaPrestatges(nuevoNomPrestatges);

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariPrestatges.this, "Prestatge donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariPrestatges.this, "Error al donar d'alta el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarTipusFons() {
        titleLabel.setText("Modificar dades del prestatge");
        prestatgesField.setText(nomPrestatges);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del prestatge
                String nuevoNomPrestatges = prestatgesField.getText();

                // Lógica para modificar el prestatge utilizando el servicio de la biblioteca
                boolean modificacionExitosa = bibliotecaService.modificarPrestatges(idPrestatges, nuevoNomPrestatges);

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariPrestatges.this, "Prestatge modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariPrestatges.this, "Error al modificar el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}