/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.Llibre;
import com.mycompany.m07uf1activitat11_polnebot.dades.Prestec;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pol^^
 */
public class ConsultaPrestec extends JDialog {

    private Llibre llibre = null;

    private String selectedItem;
    private JTextField buscarPrestec;
    private JButton nouPrestecButon;
    private JButton modificarPrestecButon;
    private JButton retornPrestecButon;
    private JTable table;
    private String[] columnNames = {"Titol", "Autor", "ISBN"};

    String[] items = {"Prestat","Incidències", "No prestat", "Tots"};
    private JComboBox<String> filtartPrestec;
    private BibliotecaService bibliotecaService;

    public ConsultaPrestec(JFrame parent, BibliotecaService bibliotecaService) {
        super(parent, "Alta de Prestec", true);
        this.bibliotecaService = bibliotecaService;

        consultaPrestec();
    }

    private void consultaPrestec() {
        setSize(600, 400); // Ajusta el tamaño de la ventana

        // Panel principal para la consulta de préstamos
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel superior para los botones y controles de filtrado
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Botón para crear un nuevo préstamo
        nouPrestecButon = new JButton("Nou Prestec");
        topPanel.add(nouPrestecButon);

        // Botón para modificar un préstamo existente
        modificarPrestecButon = new JButton("Modificar Prestec");
        topPanel.add(modificarPrestecButon);

        // Botón para devolver un préstamo
        retornPrestecButon = new JButton("Retorn Prestec");
        topPanel.add(retornPrestecButon);

        // ComboBox para filtrar los préstamos
        configComboBox();
        topPanel.add(filtartPrestec);

        // Campo de texto para buscar préstamos
        buscarPrestec = new JTextField(15);
        // Añadir un DocumentListener al JTextField buscarPrestec
        buscarPrestec.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                cargarConsulta();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cargarConsulta();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                cargarConsulta();
            }
        });
        topPanel.add(buscarPrestec);

        // Agregar el panel superior al panel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel para mostrar la tabla de préstamos
        JPanel tablePanel = new JPanel(new BorderLayout());

        // Cargar la tabla con los datos de los préstamos
        table = new JTable();
        cargarConsulta();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel de la tabla al panel principal
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        //hacer fitxa prestec @@@################################################################################
        
        // Agregar el panel principal a la ventana
        add(mainPanel);
        
        setVisible(true);
    }

    private void configComboBox() {
        filtartPrestec = new JComboBox<>(items);
        
        
        
        //esto hace q aparezcan todos los libros como predeterminado 
        selectedItem = items[2];
        filtartPrestec.setSelectedItem(selectedItem);

        filtartPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedItem = (String) filtartPrestec.getSelectedItem();
                System.out.println(selectedItem);
                cargarConsulta();//table
            }
        });
    }

    private void cargarConsulta() {
        ArrayList<Llibre> llibres = (ArrayList<Llibre>) bibliotecaService.getLlibreDAO().obtenerTodos(); // Obtener ArrayList de tipusFons
        String textoBusqueda = buscarPrestec.getText().toLowerCase(); // Convertir el texto a minúsculas para la comparación

        if (textoBusqueda != null && !textoBusqueda.equals("")) {
            ArrayList<Llibre> librosFiltrados = new ArrayList<>();

            for (Llibre libro : llibres) {
                if (libro.getTitol().toLowerCase().contains(textoBusqueda)
                        || libro.getAutor().toLowerCase().contains(textoBusqueda)
                        || libro.getIsbn().toLowerCase().contains(textoBusqueda)) {

                    librosFiltrados.add(libro);
                }
            }
            llibres = librosFiltrados;
        }

        Object[][] data = null;
         if (selectedItem.equals(items[1])) {// No perstat
            llibres = cargarConsultaNoPerstat(llibres);

        } else if (selectedItem.equals(items[0])) {//Perstat
            llibres = cargarConsultaPerstat(llibres);

        } else if (selectedItem.equals(items[1])) { // Incidències
            llibres = cargarConsultaIncidencies(llibres);
        }
        data = cargarConsulta(llibres);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    private ArrayList<Prestec> TodosLosPrestamos() {
        return (ArrayList<Prestec>) bibliotecaService.getPrestecDAO().obtenerTodos(); // Obtener ArrayList de tipusFons

    }

    private Object[][] cargarConsulta(ArrayList<Llibre> llibres) {

        Object[][] data = new Object[llibres.size()][3];

        for (int i = 0; i < llibres.size(); i++) {
            Llibre libro = llibres.get(i);
            data[i][0] = libro.getTitol();
            data[i][1] = libro.getAutor();
            data[i][2] = libro.getIsbn();

        }

        return data;

    }
    
    private ArrayList<Llibre> cargarConsultaIncidencies(ArrayList<Llibre> llibres) {//se paso el plazo de entrega
        ArrayList<Llibre> librosFiltrados = new ArrayList<>();
        for (Llibre libro : llibres) {
            if (bibliotecaService.getPrestecDAO().estaLibroEnPrestamo(libro) == 4) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }
    
    private ArrayList<Llibre> cargarConsultaNoPerstat(ArrayList<Llibre> llibres) {
        ArrayList<Llibre> librosFiltrados = new ArrayList<>();
        for (Llibre libro : llibres) {
            if (bibliotecaService.getPrestecDAO().estaLibroEnPrestamo(libro) == 0) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }

    private ArrayList<Llibre> cargarConsultaPerstat(ArrayList<Llibre> llibres) {
        ArrayList<Llibre> librosFiltrados = new ArrayList<>();
        for (Llibre libro : llibres) {
            if (bibliotecaService.getPrestecDAO().estaLibroEnPrestamo(libro) >= 1) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }

    /*
    private void modificarPrestatge() {

        nouPrestecButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información modificada del prestatge
                String nuevoNomPrestatges = buscarPrestec.getText();

                // Lógica para modificar el prestatge utilizando el servicio de la biblioteca
                boolean modificacionExitosa = false;//bibliotecaService.getPrestatgesDAO().modificarEntidad(new Prestatges(idPrestatges, nuevoNomPrestatges));

                if (modificacionExitosa) {
                    JOptionPane.showMessageDialog(ConsultaPrestec.this, "Prestatge modificat amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ConsultaPrestec.this, "Error al modificar el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void altaPrestatge() {

        nouPrestecButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la información del nuevo prestatge
                String nuevoNomPrestatges = buscarPrestec.getText();

                // Lógica para dar de alta el prestatge utilizando el servicio de la biblioteca
                boolean altaExitosa = bibliotecaService.getPrestatgesDAO().altaEntidad(new Prestatges(nuevoNomPrestatges));

                if (altaExitosa) {
                    JOptionPane.showMessageDialog(ConsultaPrestec.this, "Prestatge donat d'alta amb èxit", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(ConsultaPrestec.this, "Error al donar d'alta el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }*/
}
