package org.periodictable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class DistributionChart extends Application {

    @Override
    public void start(Stage stage) {

        Layouts.initializeElements();

        stage.setTitle("Distribution of Elements Across Periods and Groups");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Group");
        yAxis.setLabel("Period");

        final ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setTitle("Distribution of Elements");

        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Elements");

        for (ArrayList<Element> period : Layouts.elements) {
            for (Element element : period) {
                if (element != null) {
                    XYChart.Data<Number, Number> data = new XYChart.Data<>(element.getGroup(), Layouts.elements.indexOf(period) + 1);

                    Tooltip tooltip = new Tooltip(
                            "Element: " + element.getSymbol() + "\nGroup: " + element.getGroup() +
                                    "\nPeriod: " + (Layouts.elements.indexOf(period) + 1));
                    Tooltip.install(data.getNode(), tooltip);


                    Circle circle = new Circle(5);
                    circle.setFill(Color.BLUE);
                    data.setNode(circle);

                    series.getData().add(data);
                }
            }
        }

        scatterChart.getData().add(series);
        Scene scene = new Scene(scatterChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
