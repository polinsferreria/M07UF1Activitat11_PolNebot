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
public class FormulariTipusFons extends JDialog {

    private int idTipusFons;
    private String tipus = null;

    private JTextField tipusField;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;

    public FormulariTipusFons(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de tipus fons", true);
        this.bibliotecaService = bibliotecaService;

        formulariTipusFons();
    }

    public FormulariTipusFons(JFrame parent, int idTipusFons, String tipus, BibliotecaService bibliotecaService) {
        super(parent, "Modificar tipus fons", true);
        this.bibliotecaService = bibliotecaService;
        this.idTipusFons = idTipusFons;
        this.tipus = tipus;
        
        formulariTipusFons();

    }

    private void formulariTipusFons() {
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
        inputPanel.add(new JLabel("Tipus:"), gbc);

        gbc.gridx = 1;
        tipusField = new JTextField(15);
        inputPanel.add(tipusField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        confirmButton = new JButton("Confirmar");

        inputPanel.add(confirmButton, gbc);

        if (tipus != null) {
            modificarTipusFons();
        } else {
            altaTipusFons();
        }

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void altaTipusFons() {
        titleLabel.setText("Introduir dades del nou TipusFons");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo TipusFons
                String nuevoTipus = tipusField.getText();

                // Lógica para dar de alta el TipusFons utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.altaTipusFons(nuevoTipus);

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariTipusFons.this, "TipusFons donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariTipusFons.this, "Error al donar d'alta el TipusFons", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarTipusFons() {
        titleLabel.setText("Modificar dades del TipusFons");
        tipusField.setText(tipus);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del TipusFons
                String nuevoTipus = tipusField.getText();

                // Lógica para modificar el TipusFons utilizando el servicio de la biblioteca
                boolean modificacionExitosa = bibliotecaService.modificarTipusFons(idTipusFons, nuevoTipus);

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariTipusFons.this, "TipusFons modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariTipusFons.this, "Error al modificar el TipusFons", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}