package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.dades.*;
import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author pol^^
 */
public class ConsultaGenerica<T> extends JDialog {

    private BibliotecaService bibliotecaService;
    private List<T> datos;
    private String[] columnNames;
    private int tipoConsulta;
    private JTable table;

    public ConsultaGenerica(JFrame parent, BibliotecaService bibliotecaService, List<T> datos, String[] columnNames, int tipoConsulta) {
        super(parent, "Consulta " + tipoConsulta, true);
        this.bibliotecaService = bibliotecaService;
        this.datos = datos;
        this.columnNames = columnNames;
        this.tipoConsulta = tipoConsulta;
        mostrarConsulta();
    }

    private void mostrarConsulta() {
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());

        table = new JTable(getDataMatrix(), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JButton cancelButton = new JButton("Atrás");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar el diálogo al hacer clic en Cancelar

            }
        });

        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Modificar el seleccionado
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    // Mostrar un formulario de modificación
                    switch (tipoConsulta) {
                        // un tipo
                        case 1://llibre
                            int idLlibre = (int) table.getValueAt(selectedRow, 0);
                            int idCodi = (int) table.getValueAt(selectedRow, 1);
                            int idTipusFonsLlibre = (int) table.getValueAt(selectedRow, 2);
                            String titol = (String) table.getValueAt(selectedRow, 3);
                            String autor = (String) table.getValueAt(selectedRow, 4);
                            String isbn = (String) table.getValueAt(selectedRow, 5);
                            int quantitatDisponible = (int) table.getValueAt(selectedRow, 6);
                            int idPrestatgeLlibre = (int) table.getValueAt(selectedRow, 7);
                            int idBaldaLlibre = (int) table.getValueAt(selectedRow, 8);

                            // Mostrar un formulario de modificación
                            FormulariLlibres modificarLlibres = new FormulariLlibres((JFrame) ConsultaGenerica.this.getParent(), idLlibre, idCodi, idTipusFonsLlibre, titol, autor, isbn, quantitatDisponible, idPrestatgeLlibre, idBaldaLlibre, bibliotecaService);
                            // refrescar la tabla
                            dispose();

                            break;
                        case 2://Prestatges
                            int idPrestatges = (int) table.getValueAt(selectedRow, 0);
                            String nomPrestatge = (String) table.getValueAt(selectedRow, 1);

                            // Mostrar un formulario de modificación
                            FormulariPrestatges modificarPrestatges = new FormulariPrestatges((JFrame) ConsultaGenerica.this.getParent(), idPrestatges, nomPrestatge, bibliotecaService);
                            break;

                        case 3://Baldes
                            int idBalda = (int) table.getValueAt(selectedRow, 0);
                            String nomBalda = (String) table.getValueAt(selectedRow, 1);
                            int idPrestatgeBalda = (int) table.getValueAt(selectedRow, 2);

                            // Mostrar un formulario de modificación
                            FormulariBaldes modificarBaldes = new FormulariBaldes((JFrame) ConsultaGenerica.this.getParent(), idBalda, nomBalda, idPrestatgeBalda, bibliotecaService);
                            break;

                        case 4://TipusFons
                            int idTipusFons = (int) table.getValueAt(selectedRow, 0);
                            String tipusFons = (String) table.getValueAt(selectedRow, 1);

                            // Mostrar un formulario de modificación
                            FormulariTipusFons modificarDialog = new FormulariTipusFons((JFrame) ConsultaGenerica.this.getParent(), idTipusFons, tipusFons, bibliotecaService);
                            break;

                        case 5://Usuaris
                            int idUsuari = (int) table.getValueAt(selectedRow, 0);
                            String nomUsuari = (String) table.getValueAt(selectedRow, 1);
                            String contraUsuari = (String) table.getValueAt(selectedRow,2);
                            int idTipuUsuari = (int) table.getValueAt(selectedRow, 3);

                            // Mostrar un formulario de modificación
                            FormulariUsuari modificarUsuari = new FormulariUsuari((JFrame) ConsultaGenerica.this.getParent(), idUsuari, nomUsuari, contraUsuari, idTipuUsuari, bibliotecaService);
                            break;

                        default:
                            // refrescar la tabla
                            System.err.println("mal tipoConsulta");
                            break;
                    }
                    recargar();
                } else {
                    System.err.println("SI");
                }

            }
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {

                    // Eliminar el seleccionado
                    switch (tipoConsulta) {
                        // un tipo
                        case 1://llibre
                            int idLlibre = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea eliminar el Libro seleccionado
                            int optionLlibre = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar este libro?", "Eliminar libro", JOptionPane.YES_NO_OPTION);

                            if (optionLlibre == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Libro utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.getLlibreDAO().bajaEntidad(idLlibre);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Libro eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;
                        case 2://Prestatges
                            int idPrestatges = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea Prestatges el Prestatge seleccionado
                            int optionPrestatges = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar este prestatge?", "Eliminar prestatge", JOptionPane.YES_NO_OPTION);

                            if (optionPrestatges == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Prestatges utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.getPrestatgesDAO().bajaEntidad(idPrestatges);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Prestatge eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);


                                } else {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el prestatge", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;

                        case 3://Baldes
                            int idBalda = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea eliminar el Baldes seleccionado
                            int optionBaldes = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar esta balda?", "Eliminar balda", JOptionPane.YES_NO_OPTION);
                            
                            if (optionBaldes == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Prestatges utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.getBaldesDAO().bajaEntidad(idBalda);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Balda eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                    
                                } else {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el balda", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;

                        case 4://TipusFons
                            int idTipusFons = (int) table.getValueAt(selectedRow, 0);

                            // Preguntar al usuario si desea eliminar el TipusFons seleccionado
                            int optionTipusFons = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar este tipusFons?", "Eliminar tipusFons", JOptionPane.YES_NO_OPTION);

                            if (optionTipusFons == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el TipusFons utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.getTipusFonsDAO().bajaEntidad(idTipusFons);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "TipusFons eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el tipusFons", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;

                        case 5://Usuaris
                            int idUsuari = (int) table.getValueAt(selectedRow, 0);
                            String nomUsuari = (String) table.getValueAt(selectedRow, 1);

                            if (!nomUsuari.equals(MenuPrincipal.Usuarilogin.getNomUsuari())) {
                                // Preguntar al usuario si desea eliminar el usuario seleccionado
                                int optionUsuario = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar este usuario?", "Eliminar Usuario", JOptionPane.YES_NO_OPTION);

                                if (optionUsuario == JOptionPane.YES_OPTION) {
                                    // Lógica para eliminar el usuario utilizando el servicio de la biblioteca
                                    boolean eliminacionExitosa = bibliotecaService.getUsuarisDAO().bajaEntidad(idUsuari);
                                    if (eliminacionExitosa) {
                                        JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Usuario eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                        
                                    } else {
                                        JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error, no puedes eliminar un mismo usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;

                        default:
                            // refrescar la tabla
                            System.err.println("mal tipoConsulta");
                            break;
                    }
                    recargar();
                } else {
                    System.err.println("SI");
                }
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(eliminarButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH); // Añadir panel de botones en la parte inferior
        setTitle("Consulta de " + tipoConsulta);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
   
    private Object[][] getDataMatrix() {
    Object[][] data = new Object[datos.size()][columnNames.length];

    // Populamos la matriz de datos
    for (int i = 0; i < datos.size(); i++) {
        T item = datos.get(i);
        Field[] fields = item.getClass().getDeclaredFields();

        for (int j = 0; j < columnNames.length; j++) {
            try {
                Field field = fields[j];
                field.setAccessible(true);

                // Verificar si el campo es una contraseÃ±a
                if (field.getName().toLowerCase().contains("password")) {
                    data[i][j] = "*****";
                } else {
                    data[i][j] = field.get(item);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    return data;
    }

    public void recargar() {

        // Lógica para reinicializar la consulta:
        setVisible(false);
        dispose();
        switch (tipoConsulta) {
            // un tipo
            case 1://Llibre
                ConsultaGenerica<Llibre> consultaLlibre = new ConsultaGenerica<>((JFrame) ConsultaGenerica.this.getParent(), bibliotecaService, bibliotecaService.getLlibreDAO().obtenerTodos(), columnNames, tipoConsulta);

                dispose();

                break;
            case 2://Prestatges
                ConsultaGenerica<Usuaris> consulta = new ConsultaGenerica<>((JFrame) ConsultaGenerica.this.getParent(), bibliotecaService, bibliotecaService.getUsuarisDAO().obtenerTodos(), columnNames, tipoConsulta);

                break;

            case 3://Baldes
                ConsultaGenerica<Baldes> consultaBaldes = new ConsultaGenerica<>((JFrame) ConsultaGenerica.this.getParent(), bibliotecaService, bibliotecaService.getBaldesDAO().obtenerTodos(), columnNames, tipoConsulta);

                break;

            case 4://TipusFons
                ConsultaGenerica<TipusFons> consultaTipusFons = new ConsultaGenerica<>((JFrame) ConsultaGenerica.this.getParent(), bibliotecaService, bibliotecaService.getTipusFonsDAO().obtenerTodos(), columnNames, tipoConsulta);

                break;

            case 5://Usuaris
                ConsultaGenerica<Usuaris> consultaUsuaris = new ConsultaGenerica<>((JFrame) ConsultaGenerica.this.getParent(), bibliotecaService, bibliotecaService.getUsuarisDAO().obtenerTodos(), columnNames, tipoConsulta);

                break;

            default:
                // refrescar la tabla
                dispose();
                //cargarConsulta(tipoConsulta);
                break;
        }
        //ConsultaGenerica<T> nuevaConsulta = new ConsultaGenerica<T>(parent, bibliotecaService, datos, columnNames, tipoConsulta);
        //nuevaConsulta.setVisible(true);
    }

}
