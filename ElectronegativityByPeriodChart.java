package org.periodictable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElectronegativityByPeriodChart extends AbstractElectronegativityChart {

    @Override
    protected String getChartTitle() {
        return "Electronegativity by Period";
    }

    @Override
    protected String getXAxisLabel() {
        return "Period";
    }

    @Override
    protected String getYAxisLabel() {
        return "Average Electronegativity";
    }

    @Override
    protected Map<Integer, ArrayList<Double>> calculateAverages() {
        Map<Integer, ArrayList<Double>> periodElectronegativities = new HashMap<>();
        for (ArrayList<Element> period : Layouts.elements) {
            int periodNumber = Layouts.elements.indexOf(period) + 1;
            ArrayList<Double> electronegativities = new ArrayList<>();
            for (Element element : period) {
                if (element != null) {
                    electronegativities.add(element.getElectronegativity());
                }
            }
            periodElectronegativities.put(periodNumber, electronegativities);
        }
        return periodElectronegativities;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
