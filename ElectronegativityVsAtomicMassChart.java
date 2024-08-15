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

public class ElectronegativityVsAtomicMassChart extends Application {

    @Override
    public void start(Stage stage) {

        Layouts.initializeElements();

        stage.setTitle("Electronegativity vs. Atomic Mass");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Atomic Mass");
        yAxis.setLabel("Electronegativity");

        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Electronegativity vs. Atomic Mass");
        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Elements");

        for (ArrayList<Element> period : Layouts.elements) {
            for (Element element : period) {
                if (element != null) {
                    XYChart.Data<Number, Number> data = new XYChart.Data<>(element.getMass(), element.getElectronegativity());

                    Tooltip tooltip = new Tooltip(
                            "Element: " + element.getSymbol() + "\nAtomic Mass: " + element.getMass() +
                                    "\nElectronegativity: " + element.getElectronegativity());
                    Tooltip.install(data.getNode(), tooltip);

                    series.getData().add(data);
                }
            }
        }

        scatterChart.getData().add(series);
        Scene scene = new Scene(scatterChart, 800, 600);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(Objects.requireNonNull(getClass().getResource("electro.jpeg")))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
