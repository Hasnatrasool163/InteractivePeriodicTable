package org.htech.interactiveperiodictable.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import org.htech.interactiveperiodictable.modal.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UtilityMethods {

    public static int[] getElectronConfiguration(int atomicNumber) {
        int[] configuration = new int[6];
        int[] shells = new int[]{2, 8, 18, 32, 32, 18};
        int remainingElectrons = atomicNumber;

        for(int i = 0; i < configuration.length && remainingElectrons > 0; ++i) {
            configuration[i] = Math.min(remainingElectrons, shells[i]);
            remainingElectrons -= configuration[i];
        }

        return configuration;
    }


        private static final Map<Integer, String> elementStates = new HashMap<>();

        static {
            initializeElementStates();
        }

        private static void initializeElementStates() {
            for (int i = 1; i <= 118; i++) {
                if (i >= 104) {
                    elementStates.put(i, "unknown");
                    continue;
                }
                elementStates.put(i, "solid");
            }

            int[] gases = {1, 2, 7, 8, 9, 10, 17, 18, 36, 54, 86};
            for (int gas : gases) {
                elementStates.put(gas, "gas");
            }

            int[] liquids = {35, 80};
            for (int liquid : liquids) {
                elementStates.put(liquid, "liquid");
            }

            elementStates.put(55, "solid");
            elementStates.put(87, "solid");
            elementStates.put(114, "unknown");
            elementStates.put(118, "unknown");
        }

        public static String getElementState(int atomicNumber) {
            return elementStates.getOrDefault(atomicNumber, "unknown");
        }

        public static Color getStateColor(String state) {
            return switch (state.toLowerCase()) {
                case "solid" -> Color.rgb(150, 150, 200);
                case "liquid" -> Color.rgb(100, 200, 200);
                case "gas" -> Color.rgb(200, 150, 150);
                default -> Color.rgb(180, 180, 180);
            };
        }

        public static String combineElements(ArrayList<Element> elements) {
            if (elements.isEmpty()) {
                return "No elements selected";
            }

            StringBuilder result = new StringBuilder();
            Map<String, Integer> counts = new HashMap<>();

            for (Element element : elements) {
                String symbol = element.getSymbol();
                counts.put(symbol, counts.getOrDefault(symbol, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                result.append(entry.getKey());
                if (entry.getValue() > 1) {
                    result.append(entry.getValue());
                }
            }

            return result.toString();
        }

//    public static String combineElements(ArrayList<Element> elements) {
//        StringBuilder result = new StringBuilder();
//
//        for(Element symbol : elements) {
//            result.append(symbol.getSymbol()).append(" ");
//        }
//
//        return result.toString().trim();
//    }

    static public void showAbout() {
        String text = "            Welcome to Interactive Periodic Table World\n Features:\n #Visualize all elements\n #check details of each element by right click element and press show details\n #compare elements \n #make compounds by elements ( drag and drop element on right side)\n #check electronic shell configuration by drag and drop on right side.\n #view states of matter (solid, liquid, gas) of elements at room temperature\n            Thank you for using the application.\n ";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.setHeaderText("Guide");
        alert.showAndWait();
    }

    static public String toRgbString(Color color) {
        return String.format("#%02X%02X%02X", (int)(color.getRed() * (double)255.0F), (int)(color.getGreen() * (double)255.0F), (int)(color.getBlue() * (double)255.0F));
    }
}
