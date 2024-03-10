package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.Baldes;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestatges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormulariBaldes extends JDialog {

    private int idBalda;
    private String nomBalda = null;
    private int idPrestatges;

    private JTextField baldaField;
    private JComboBox<String> idPrestatgesComboBox;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;

    public FormulariBaldes(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de Balda", true);
        this.bibliotecaService = bibliotecaService;
        formulariBaldes();
    }

    public FormulariBaldes(JFrame parent, int idBalda, String nomBalda, int idPrestatges, BibliotecaService bibliotecaService) {
        super(parent, "Modificar una Balda", true);
        this.bibliotecaService = bibliotecaService;
        this.idBalda = idBalda;
        this.nomBalda = nomBalda;
        this.idPrestatges = idPrestatges;
        formulariBaldes();
    }

    private void formulariBaldes() {
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
        inputPanel.add(new JLabel("Nom Balda:"), gbc);

        gbc.gridx = 1;
        baldaField = new JTextField(15);
        inputPanel.add(baldaField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Prestatges:"), gbc);

        gbc.gridx = 1;
        idPrestatgesComboBox = new JComboBox<>();
        cargarPrestatgesEnComboBox();
        inputPanel.add(idPrestatgesComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        confirmButton = new JButton("Confirmar");
        inputPanel.add(confirmButton, gbc);

        if (nomBalda != null) {
            modificarBaldes();
        } else {
            altaBaldes();
        }

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void cargarPrestatgesEnComboBox() {
        List<Prestatges> prestatges = bibliotecaService.getPrestatgesDAO().obtenerTodos();

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Agregar representación descriptiva al JComboBox
        for (Prestatges prestatge : prestatges) {
            String item = prestatge.getIdPrestatge() + ": " + prestatge.getNom();
            comboBoxModel.addElement(item);
        }

        idPrestatgesComboBox.setModel(comboBoxModel);
    }

    private int extraerIdPrestatgeDesdeItem(String selectedItem) {
        // Extraer la ID de la representación descriptiva del JComboBox
        String[] parts = selectedItem.split(": ");
        if (parts.length == 2) {
            return Integer.parseInt(parts[0]);
        } else {
            // Manejar el caso de un formato incorrecto
            return -1;
        }
    }

    private void altaBaldes() {
        titleLabel.setText("Introduir dades de la nova balda");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información de la nueva balda
                String nuevoNomBalda = baldaField.getText();

                // Obtener la ID seleccionada desde el JComboBox
                int selectedIdPrestatge = extraerIdPrestatgeDesdeItem(idPrestatgesComboBox.getSelectedItem().toString());

                // Lógica para dar de alta la balda utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.getBaldesDAO().altaEntidad(new Baldes(nuevoNomBalda, selectedIdPrestatge));

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariBaldes.this, "Balda donada d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariBaldes.this, "Error al donar d'alta la balda", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarBaldes() {
        titleLabel.setText("Modificar dades de la balda");
        baldaField.setText(nomBalda);

        // Cargar los Prestatges en el JComboBox
        cargarPrestatgesEnComboBox();

        // Seleccionar el elemento en el JComboBox basado en la ID almacenada previamente
        String itemToSelect = idPrestatges + ": " + bibliotecaService.getPrestatgesDAO().obtenirNomPrestatgePerId(idPrestatges);
        idPrestatgesComboBox.setSelectedItem(itemToSelect);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada de la balda
                String nuevoNomBalda = baldaField.getText();

                // Obtener la ID seleccionada desde el JComboBox
                int selectedIdPrestatge = extraerIdPrestatgeDesdeItem(idPrestatgesComboBox.getSelectedItem().toString());
                
                // Lógica para modificar la balda utilizando el servicio de la biblioteca
                boolean modificacionExitosa = bibliotecaService.getBaldesDAO().modificarEntidad(new Baldes(idBalda, nuevoNomBalda, selectedIdPrestatge));

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariBaldes.this, "Balda modificada amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariBaldes.this, "Error al modificar la balda", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
