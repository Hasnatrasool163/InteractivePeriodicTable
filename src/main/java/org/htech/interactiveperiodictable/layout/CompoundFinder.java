package org.htech.interactiveperiodictable.layout;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.htech.interactiveperiodictable.modal.Element;

import java.util.*;

public class CompoundFinder {

    private static final Map<List<String>, String> COMPOUNDS = new HashMap<>();
    static Text text;

    static {
        COMPOUNDS.put(List.of("H", "H"), "Hydrogen Gas (H2)");
        COMPOUNDS.put(List.of("O", "O"), "Oxygen Gas (O2)");
        COMPOUNDS.put(List.of("N", "N"), "Nitrogen Gas (N2)");

        COMPOUNDS.put(List.of("H", "H", "O"), "Water (H2O)");
        COMPOUNDS.put(List.of("H", "H", "O", "O"), "Hydrogen Peroxide (H2O2)");

        COMPOUNDS.put(List.of("C", "O"), "Carbon Monoxide (CO)");
        COMPOUNDS.put(List.of("C", "O", "O"), "Carbon Dioxide (CO2)");

        COMPOUNDS.put(List.of("H", "Cl"), "Hydrochloric Acid (HCl)");
        COMPOUNDS.put(List.of("H", "N", "O", "O", "O", "O"), "Nitric Acid (HNO3)");
        COMPOUNDS.put(List.of("H", "H", "S", "O", "O", "O", "O"), "Sulfuric Acid (H2SO4)");


        COMPOUNDS.put(List.of("Na", "Cl"), "Sodium Chloride (NaCl)");
        COMPOUNDS.put(List.of("Ca", "C", "O", "O", "O"), "Calcium Carbonate (CaCO3)");

        COMPOUNDS.put(List.of("C", "H", "H", "H", "H"), "Methane (CH4)");
        COMPOUNDS.put(List.of("N", "H", "H", "H"), "Ammonia (NH3)");
        COMPOUNDS.put(List.of("C", "H", "H", "O", "H"), "Ethanol (C2H5OH)");
        COMPOUNDS.put(List.of("C", "C", "H", "H", "H", "H", "H", "H"), "Ethane (C2H6)");
        COMPOUNDS.put(List.of("C", "H", "H", "H", "H", "O", "O", "Na"), "Sodium Bicarbonate (NaHCO3)");

        COMPOUNDS.put(List.of("C", "C", "C", "C", "C", "C", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "O", "O", "O", "O", "O", "O"), "Glucose (C6H12O6)");
    }

    public static Node findCompound(ArrayList<Element> elements) {
        List<String> symbols = elements.stream()
                .map(Element::getSymbol)
                .toList();

        text = new Text();
        for (Map.Entry<List<String>, String> entry : COMPOUNDS.entrySet()) {
            if (entry.getKey().equals(symbols)) {
                text.setText("\n\nLast Compound : " + entry.getValue());
                text.setTextAlignment(TextAlignment.CENTER);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Combined elements form: " + entry.getValue(), ButtonType.OK);
                alert.showAndWait();
                return text;
            }
        }

        return null;
    }
}
