package com.rs.redsinergia.utilerias;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.rs.redsinergia.model.ReporteTransaccionesPorUsuarioHome;

public class PieChartExample {

    private JFreeChart chart;

    public PieChartExample(String title, List<Integer> transacciones) {
        chart = createChart(createDataset(transacciones), title);
    }

    private DefaultPieDataset createDataset(List<Integer> transacciones) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // Lógica para crear el conjunto de datos del gráfico de pastel
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset, String title) {
        return ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false
        );
    }

    public JFreeChart getChart() {
        return chart;
    }
}
