package org.periodictable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupVsAverageElectronegativityChart extends AbstractElectronegativityChart {

    @Override
    protected String getChartTitle() {
        return "Group vs. Average Electronegativity";
    }

    @Override
    protected String getXAxisLabel() {
        return "Group";
    }

    @Override
    protected String getYAxisLabel() {
        return "Average Electronegativity";
    }

    @Override
    protected Map<Integer, ArrayList<Double>> calculateAverages() {
        Map<Integer, ArrayList<Double>> groupElectronegativities = new HashMap<>();
        for (ArrayList<Element> period : Layouts.elements) {
            for (Element element : period) {
                if (element != null) {
                    int group = element.getGroup();
                    groupElectronegativities.computeIfAbsent(group, k -> new ArrayList<>()).add(element.getElectronegativity());
                }
            }
        }
        return groupElectronegativities;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
