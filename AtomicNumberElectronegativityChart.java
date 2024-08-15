package org.periodictable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class AtomicNumberElectronegativityChart extends Application {

    @Override
    public void start(Stage stage) {

        Layouts.initializeElements();

        stage.setTitle("Atomic Number vs. Electronegativity");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Atomic Number");
        yAxis.setLabel("Electronegativity");

        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Atomic Number vs. Electronegativity");
        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Elements");

        for (ArrayList<Element> period : Layouts.elements) {
            for (Element element : period) {
                if (element != null) {
                    XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(element.getAtomicNumber(), element.getElectronegativity());

                    Tooltip tooltip = new Tooltip(
                            String.format("Name: %s\nSymbol: %s\nAtomic Number: %d\nElectronegativity: %.2f",
                                    element.getName(), element.getSymbol(), element.getAtomicNumber(), element.getElectronegativity())
                    );
                    Tooltip.install(dataPoint.getNode(), tooltip);

                    series.getData().add(dataPoint);
                }
            }
        }

        scatterChart.getData().add(series);
        Scene scene = new Scene(scatterChart, 800, 600);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("electro.png")))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
