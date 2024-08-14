package org.periodictable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.*;

public class CompoundFinder {

    private static final Map<List<String>,String> COMPOUNDS = new HashMap<>();
    static Text text ;


    public static Node findCompound(ArrayList<Element> elements) {

        COMPOUNDS.put(Arrays.asList("H", "H"), "Hydrogen Gas (H2)");
        COMPOUNDS.put(Arrays.asList("O", "O"), "Oxygen Gas (O2)");
        COMPOUNDS.put(Arrays.asList("N", "N"), "Nitrogen Gas (N2)");
        COMPOUNDS.put(Arrays.asList("H","H", "O"), "Water (H2O)");
        COMPOUNDS.put(Arrays.asList("C", "O"), "Carbon Monoxide (CO)");
        COMPOUNDS.put(Arrays.asList("C", "O", "O"), "Carbon Dioxide (CO2)");
        COMPOUNDS.put(Arrays.asList("Na", "Cl"), "Sodium Chloride (NaCl)");
        COMPOUNDS.put(Arrays.asList("H", "Cl"), "Hydrochloric Acid (HCl)");
        COMPOUNDS.put(Arrays.asList("H", "H", "O","O"), "Hydrogen Peroxide (H2O2)");
        COMPOUNDS.put(Arrays.asList("H", "N", "O", "O","O","O"), "Nitric Acid (HNO3)");
        COMPOUNDS.put(Arrays.asList("H","H", "S", "O","O","O","O"), "Sulfuric Acid (H2SO4)");

        List<String> symbols = elements.stream()
                .map(Element::getSymbol)
//                .sorted()
                .toList();
        text = new Text();
        for (Map.Entry<List<String>, String> entry : COMPOUNDS.entrySet()) {
            if (entry.getKey().equals(symbols)) {
                text.setText("\n\n"+"Last Compound : "+entry.getValue());
                text.setTextAlignment(TextAlignment.CENTER);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Combined elements form: " + entry.getValue(), ButtonType.OK);
                alert.showAndWait();
                return text;
            }
        }
            return null;
    }
}

