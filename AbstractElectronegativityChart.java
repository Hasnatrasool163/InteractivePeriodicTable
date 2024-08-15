package org.periodictable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractElectronegativityChart extends Application {

    protected abstract String getChartTitle();
    protected abstract String getXAxisLabel();
    protected abstract String getYAxisLabel();
    protected abstract Map<Integer, ArrayList<Double>> calculateAverages();

    @Override
    public void start(Stage stage) {

        Layouts.initializeElements();

        stage.setTitle(getChartTitle());
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel(getXAxisLabel());
        yAxis.setLabel(getYAxisLabel());

        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(getChartTitle());
        final XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Electronegativity");

        Map<Integer, ArrayList<Double>> averages = calculateAverages();

        for (Map.Entry<Integer, ArrayList<Double>> entry : averages.entrySet()) {
            int key = entry.getKey();
            ArrayList<Double> values = entry.getValue();
            double average = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            XYChart.Data<String, Number> data = new XYChart.Data<>(String.valueOf(key), average);

            Tooltip tooltip = new Tooltip("Group: " + key + "\nAverage Electronegativity: " + String.format("%.2f", average));
            Tooltip.install(data.getNode(), tooltip);

            series.getData().add(data);
        }

        barChart.getData().add(series);
        VBox vbox = new VBox(barChart);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
