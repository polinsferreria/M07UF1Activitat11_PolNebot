package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.Baldes;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestatges;
import com.mycompany.m07uf1activitat11_polnebot.dades.TipusFons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormulariLlibres extends JDialog {

    private int idLlibre;
    private int idTipusFons;
    private String titol = null;
    private String autor = null;
    private String isbn = null;
    private int quantitatDisponible;
    private int idPrestatge;
    private int idBalda;

    private JTextField titolField;
    private JTextField autorField;
    private JTextField isbnField;
    private JTextField quantitatDisponibleField;
    private JComboBox<String> idTipusFonsComboBox;
    private JComboBox<String> idPrestatgeComboBox;
    private JComboBox<String> idBaldaComboBox;
    private JLabel titleLabel;
    private JButton confirmButton;
    private BibliotecaService bibliotecaService;

    public FormulariLlibres(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de Llibre", true);
        this.bibliotecaService = bibliotecaService;
        formulariLlibres();
    }

    public FormulariLlibres(JFrame parent, int idLlibre, int idCodi, int idTipusFons, String titol, String autor,
            String isbn, int quantitatDisponible, int idPrestatge, int idBalda, BibliotecaService bibliotecaService) {
        super(parent, "Modificar un Llibre", true);
        this.bibliotecaService = bibliotecaService;
        this.idLlibre = idLlibre;
        this.idTipusFons = idTipusFons;
        this.titol = titol;
        this.autor = autor;
        this.isbn = isbn;
        this.quantitatDisponible = quantitatDisponible;
        this.idPrestatge = idPrestatge;
        this.idBalda = idBalda;
        formulariLlibres();
    }

    private void formulariLlibres() {
        setSize(400, 500);
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
        inputPanel.add(new JLabel("Títol:"), gbc);

        gbc.gridx = 1;
        titolField = new JTextField(15);
        inputPanel.add(titolField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Autor:"), gbc);

        gbc.gridx = 1;
        autorField = new JTextField(15);
        inputPanel.add(autorField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("ISBN:"), gbc);

        gbc.gridx = 1;
        isbnField = new JTextField(15);
        inputPanel.add(isbnField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Quantitat Disponible:"), gbc);

        gbc.gridx = 1;
        quantitatDisponibleField = new JTextField(15);
        inputPanel.add(quantitatDisponibleField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Tipus Fons:"), gbc);

        gbc.gridx = 1;
        idTipusFonsComboBox = new JComboBox<>();
        cargarTipusFonsEnComboBox();
        inputPanel.add(idTipusFonsComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Prestatge:"), gbc);

        gbc.gridx = 1;
        idPrestatgeComboBox = new JComboBox<>();
        cargarPrestatgesEnComboBox();
        // Agrega un listener al JComboBox de Prestatges para manejar cambios de selección
        idPrestatgeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cada vez que cambia la selección de Prestatge, actualiza las opciones de Baldes
                cargarBaldesEnComboBox();
            }
        });
        inputPanel.add(idPrestatgeComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Balda:"), gbc);

        gbc.gridx = 1;
        idBaldaComboBox = new JComboBox<>();
        cargarBaldesEnComboBox();
        inputPanel.add(idBaldaComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        confirmButton = new JButton("Confirmar");
        inputPanel.add(confirmButton, gbc);

        if (titol != null) {
            modificarLlibres();
        } else {
            altaLlibres();
        }

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void cargarTipusFonsEnComboBox() {
        List<TipusFons> tipusFonsList = bibliotecaService.obtenirTotsElsTipusFons();

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Agregar representación descriptiva al JComboBox
        for (TipusFons tipusFons : tipusFonsList) {
            String item = tipusFons.getIdTipusFons() + ": " + tipusFons.getTipus();
            comboBoxModel.addElement(item);
        }

        idTipusFonsComboBox.setModel(comboBoxModel);
    }

    private void cargarPrestatgesEnComboBox() {
        List<Prestatges> prestatgesList = bibliotecaService.obtenirTotsElsPrestatges();

        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Agregar representación descriptiva al JComboBox
        for (Prestatges prestatge : prestatgesList) {
            String item = prestatge.getIdPrestatge() + ": " + prestatge.getNom();
            comboBoxModel.addElement(item);
        }

        idPrestatgeComboBox.setModel(comboBoxModel);
    }

    private void cargarBaldesEnComboBox() {
        List<Baldes> baldesList = bibliotecaService.obtenirTotsElsBaldes();
        if (!baldesList.isEmpty()) {
            // Obtener la ID seleccionada desde el JComboBox de Prestatges
            int selectedIdPrestatge = extraerIdDesdeItem(idPrestatgeComboBox.getSelectedItem().toString());

            // Obtener las Baldes en base al Prestatge seleccionado
            List<Baldes> baldesListIdPrestatge = bibliotecaService.obtenirBaldesPerPrestatge(selectedIdPrestatge);

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

            // Agregar representación descriptiva al JComboBox
            for (Baldes balda : baldesListIdPrestatge) {
                String item = balda.getIdBalda() + ": " + balda.getNom();
                comboBoxModel.addElement(item);
            }

            idBaldaComboBox.setModel(comboBoxModel);
        }
    }

    private int extraerIdDesdeItem(String selectedItem) {
        // Extraer la ID de la representación descriptiva del JComboBox
        String[] parts = selectedItem.split(": ");
        if (parts.length == 2) {
            return Integer.parseInt(parts[0]);
        } else {
            // Manejar el caso de un formato incorrecto
            return -1;
        }
    }

    private void altaLlibres() {
        titleLabel.setText("Introduir dades del nou llibre");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo libro
                String nuevoTitol = titolField.getText();
                String nuevoAutor = autorField.getText();
                String nuevoIsbn = isbnField.getText();
                int nuevaQuantitatDisponible = Integer.parseInt(quantitatDisponibleField.getText());

                // Obtener las IDs seleccionadas desde los JComboBox
                int selectedIdTipusFons = extraerIdDesdeItem(idTipusFonsComboBox.getSelectedItem().toString());
                int selectedIdPrestatge = extraerIdDesdeItem(idPrestatgeComboBox.getSelectedItem().toString());
                int selectedIdBalda = extraerIdDesdeItem(idBaldaComboBox.getSelectedItem().toString());

                // Lógica para dar de alta el libro utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.altaLlibre(selectedIdTipusFons, nuevoTitol, nuevoAutor,
                        nuevoIsbn, nuevaQuantitatDisponible, selectedIdPrestatge, selectedIdBalda);

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(FormulariLlibres.this, "Llibre donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariLlibres.this, "Error al donar d'alta el llibre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void modificarLlibres() {
        titleLabel.setText("Modificar dades del llibre");
        titolField.setText(titol);
        autorField.setText(autor);
        isbnField.setText(isbn);
        quantitatDisponibleField.setText(String.valueOf(quantitatDisponible));

        // Cargar los Tipus Fons, Prestatges y Baldes en los JComboBox
        cargarTipusFonsEnComboBox();
        cargarPrestatgesEnComboBox();
        cargarBaldesEnComboBox();

        // Seleccionar los elementos en los JComboBox basados en las IDs almacenadas previamente
        String tipusFonsItemToSelect = idTipusFons + ": " + bibliotecaService.obtenirNomTipusFonsPerId(idTipusFons);
        String prestatgeItemToSelect = idPrestatge + ": " + bibliotecaService.obtenirNomPrestatgePerId(idPrestatge);
        String baldaItemToSelect = idBalda + ": " + bibliotecaService.obtenirNomBaldaPerId(idBalda);

        idTipusFonsComboBox.setSelectedItem(tipusFonsItemToSelect);
        idPrestatgeComboBox.setSelectedItem(prestatgeItemToSelect);
        idBaldaComboBox.setSelectedItem(baldaItemToSelect);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del libro
                String nuevoTitol = titolField.getText();
                String nuevoAutor = autorField.getText();
                String nuevoIsbn = isbnField.getText();
                int nuevaQuantitatDisponible = Integer.parseInt(quantitatDisponibleField.getText());

                // Obtener las IDs seleccionadas desde los JComboBox
                int selectedIdTipusFons = extraerIdDesdeItem(idTipusFonsComboBox.getSelectedItem().toString());
                int selectedIdPrestatge = extraerIdDesdeItem(idPrestatgeComboBox.getSelectedItem().toString());
                int selectedIdBalda = extraerIdDesdeItem(idBaldaComboBox.getSelectedItem().toString());

                // Lógica para modificar el libro utilizando el servicio de la biblioteca
                boolean modificacionExitosa = bibliotecaService.modificarLlibre(idLlibre, selectedIdTipusFons, nuevoTitol,
                        nuevoAutor, nuevoIsbn, nuevaQuantitatDisponible, selectedIdPrestatge, selectedIdBalda);

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(FormulariLlibres.this, "Llibre modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FormulariLlibres.this, "Error al modificar el llibre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
