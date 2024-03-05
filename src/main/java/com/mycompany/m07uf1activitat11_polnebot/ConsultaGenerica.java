package com.mycompany.m07uf1activitat11_polnebot;

import com.mycompany.m07uf1activitat11_polnebot.logica.BibliotecaService;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author pol^^
 */

public class ConsultaGenerica<T> extends JDialog {

    private BibliotecaService bibliotecaService;
    private List<T> datos;
    private String[] columnNames;
    private String tipoConsulta;

    public ConsultaGenerica(JFrame parent, BibliotecaService bibliotecaService, List<T> datos, String[] columnNames, String tipoConsulta) {
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

        JTable table = new JTable(getDataMatrix(), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
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
}