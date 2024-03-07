package com.mycompany.m07uf1activitat11_polnebot;

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
                            int idTipusFons = (int) table.getValueAt(selectedRow, 2);
                            String titol = (String) table.getValueAt(selectedRow, 3);
                            String autor = (String) table.getValueAt(selectedRow, 4);
                            String isbn = (String) table.getValueAt(selectedRow, 5);
                            int quantitatDisponible = (int) table.getValueAt(selectedRow, 6);
                            int idPrestatge = (int) table.getValueAt(selectedRow, 7);
                            int idBalda = (int) table.getValueAt(selectedRow, 8);

                            // Mostrar un formulario de modificación
                            FormulariLlibres modificarDialog = new FormulariLlibres((JFrame) ConsultaGenerica.this.getParent(), idLlibre, idCodi, idTipusFons, titol, autor, isbn, quantitatDisponible, idPrestatge, idBalda, bibliotecaService);
                            // refrescar la tabla
                            dispose();

                            break;
                        case 2://Prestatges

                            break;

                        case 3://Baldes

                            break;

                        case 4://TipusFons

                            break;

                        case 5://Usuaris

                            break;

                        default:
                            // refrescar la tabla
                            dispose();
                            //cargarConsulta(tipoConsulta);
                            break;
                    }
                } else {
                    System.out.println("SI");
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
                            int option = JOptionPane.showConfirmDialog((JFrame) ConsultaGenerica.this.getParent(), "¿Desea eliminar este libro?", "Eliminar libro", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {
                                // Lógica para eliminar el Libro utilizando el servicio de la biblioteca
                                boolean eliminacionExitosa = bibliotecaService.baixaLlibre(idLlibre);
                                if (eliminacionExitosa) {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Libro eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                                    // refrescar la tabla
                                    dispose();
                                    //cargarConsulta(tipoConsulta);

                                } else {
                                    JOptionPane.showMessageDialog((JFrame) ConsultaGenerica.this.getParent(), "Error al eliminar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;
                        case 2://Prestatges

                            break;

                        case 3://Baldes

                            break;

                        case 4://TipusFons

                            break;

                        case 5://Usuaris

                            break;

                        default:
                            // refrescar la tabla
                            setVisible(false);
                            dispose();
                            //cargarConsulta(tipoConsulta);
                            break;
                    }
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
                    data[i][j] = field.get(item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }

    public void recargar() {
        // Lógica para reinicializar la consulta, por ejemplo:
        dispose();
        ConsultaGenerica<T> nuevaConsulta = new ConsultaGenerica<>(parent, bibliotecaService, datos, columnNames, tipoConsulta);
        nuevaConsulta.setVisible(true);
    }
}
