package org.htech.interactiveperiodictable.layout;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import org.htech.interactiveperiodictable.modal.Element;

public class Layouts {
    public static final ArrayList<ArrayList<Element>> elements = new ArrayList<>();
    private static final Color[] groupColors;

    public Layouts() {
    }

    public static void initializeElements() {
        for(int i = 0; i < 9; ++i) {
            elements.add(new ArrayList<>());
        }

        elements.get(0).add(new Element(1, "H", "Hydrogen", 1.008, 1, 1, groupColors[0], 2.2));

        for(int i = 0; i < 16; ++i) {
            elements.get(0).add(null);
        }

        elements.get(0).add(new Element(2, "He", "Helium", 4.0026, 1, 18, groupColors[1], (double)-1.0F));
        elements.get(1).add(new Element(3, "Li", "Lithium", 6.94, 1, 2, groupColors[2], 0.98));
        elements.get(1).add(new Element(4, "Be", "Beryllium", 9.0122, 1, 2, groupColors[2], 1.57));

        for(int i = 0; i < 10; ++i) {
            elements.get(1).add(null);
        }

        ((ArrayList)elements.get(1)).add(new Element(5, "B", "Boron", 10.81, 13, 2, groupColors[3], 2.04));
        ((ArrayList)elements.get(1)).add(new Element(6, "C", "Carbon", 12.01, 14, 2, groupColors[3], 2.55));
        ((ArrayList)elements.get(1)).add(new Element(7, "N", "Nitrogen", 14.01, 15, 2, groupColors[3], 3.04));
        ((ArrayList)elements.get(1)).add(new Element(8, "O", "Oxygen", (double)16.0F, 16, 2, groupColors[3], 3.44));
        ((ArrayList)elements.get(1)).add(new Element(9, "F", "Fluorine", (double)19.0F, 17, 2, groupColors[3], 3.98));
        ((ArrayList)elements.get(1)).add(new Element(10, "Ne", "Neon", 20.18, 18, 2, groupColors[1], (double)-1.0F));
        ((ArrayList)elements.get(2)).add(new Element(11, "Na", "Sodium", 22.99, 2, 3, groupColors[2], 0.93));
        ((ArrayList)elements.get(2)).add(new Element(12, "Mg", "Magnesium", 24.31, 2, 3, groupColors[2], 1.31));

        for(int i = 0; i < 10; ++i) {
            ((ArrayList)elements.get(2)).add((Object)null);
        }

        ((ArrayList)elements.get(2)).add(new Element(13, "Al", "Aluminium", 26.98, 13, 3, groupColors[3], 1.61));
        ((ArrayList)elements.get(2)).add(new Element(14, "Si", "Silicon", 28.09, 14, 3, groupColors[3], 1.9));
        ((ArrayList)elements.get(2)).add(new Element(15, "P", "Phosphorus", 30.97, 15, 3, groupColors[3], 2.19));
        ((ArrayList)elements.get(2)).add(new Element(16, "S", "Sulfur", 32.07, 16, 3, groupColors[3], 2.58));
        ((ArrayList)elements.get(2)).add(new Element(17, "Cl", "Chlorine", 35.45, 17, 3, groupColors[3], 3.16));
        ((ArrayList)elements.get(2)).add(new Element(18, "Ar", "Argon", 39.95, 18, 3, groupColors[1], (double)-1.0F));
        ((ArrayList)elements.get(3)).add(new Element(19, "K", "Potassium", 39.1, 1, 4, groupColors[2], 0.82));
        ((ArrayList)elements.get(3)).add(new Element(20, "Ca", "Calcium", 40.08, 2, 4, groupColors[2], (double)1.0F));
        ((ArrayList)elements.get(3)).add(new Element(21, "Sc", "Scandium", 44.96, 3, 4, groupColors[4], 1.36));
        ((ArrayList)elements.get(3)).add(new Element(22, "Ti", "Titanium", 47.87, 4, 4, groupColors[4], 1.54));
        ((ArrayList)elements.get(3)).add(new Element(23, "V", "Vanadium", 50.94, 5, 4, groupColors[4], 1.63));
        ((ArrayList)elements.get(3)).add(new Element(24, "Cr", "Chromium", 51.96, 6, 4, groupColors[4], 1.66));
        ((ArrayList)elements.get(3)).add(new Element(25, "Mn", "Manganese", 54.94, 7, 4, groupColors[4], 1.55));
        ((ArrayList)elements.get(3)).add(new Element(26, "Fe", "Iron", 55.85, 8, 4, groupColors[4], 1.83));
        ((ArrayList)elements.get(3)).add(new Element(27, "Co", "Cobalt", 58.93, 9, 4, groupColors[4], 1.88));
        ((ArrayList)elements.get(3)).add(new Element(28, "Ni", "Nickel", 58.69, 10, 4, groupColors[4], 1.91));
        ((ArrayList)elements.get(3)).add(new Element(29, "Cu", "Copper", 63.55, 11, 4, groupColors[4], 1.9));
        ((ArrayList)elements.get(3)).add(new Element(30, "Zn", "Zinc", 65.38, 12, 4, groupColors[4], 1.65));
        ((ArrayList)elements.get(3)).add(new Element(31, "Ga", "Gallium", 69.72, 13, 4, groupColors[3], 1.81));
        ((ArrayList)elements.get(3)).add(new Element(32, "Ge", "Germanium", 72.63, 14, 4, groupColors[3], 1.81));
        ((ArrayList)elements.get(3)).add(new Element(33, "As", "Arsenic", 74.92, 15, 4, groupColors[3], 2.18));
        ((ArrayList)elements.get(3)).add(new Element(34, "Se", "Selenium", 78.97, 16, 4, groupColors[3], 2.55));
        ((ArrayList)elements.get(3)).add(new Element(35, "Br", "Bromine", 79.9, 17, 4, groupColors[3], 2.96));
        ((ArrayList)elements.get(3)).add(new Element(36, "Kr", "Krypton", 83.8, 18, 4, groupColors[1], (double)-1.0F));
        ((ArrayList)elements.get(4)).add(new Element(37, "Rb", "Rubidium", 85.47, 1, 5, groupColors[2], 0.82));
        ((ArrayList)elements.get(4)).add(new Element(38, "Sr", "Strontium", 87.62, 2, 5, groupColors[2], 0.95));
        ((ArrayList)elements.get(4)).add(new Element(39, "Y", "Yttrium", 88.91, 3, 5, groupColors[4], 1.22));
        ((ArrayList)elements.get(4)).add(new Element(40, "Zr", "Zirconium", 91.22, 4, 5, groupColors[4], 1.33));
        ((ArrayList)elements.get(4)).add(new Element(41, "Nb", "Niobium", 92.91, 5, 5, groupColors[4], 1.6));
        ((ArrayList)elements.get(4)).add(new Element(42, "Mo", "Molybdenum", 95.94, 6, 5, groupColors[4], 2.16));
        ((ArrayList)elements.get(4)).add(new Element(43, "Tc", "Technetium", (double)98.0F, 7, 5, groupColors[4], 1.9));
        ((ArrayList)elements.get(4)).add(new Element(44, "Ru", "Ruthenium", 101.07, 8, 5, groupColors[4], 2.2));
        ((ArrayList)elements.get(4)).add(new Element(45, "Rh", "Rhodium", 102.91, 9, 5, groupColors[4], 2.28));
        ((ArrayList)elements.get(4)).add(new Element(46, "Pd", "Palladium", 106.42, 10, 5, groupColors[4], 2.2));
        ((ArrayList)elements.get(4)).add(new Element(47, "Ag", "Silver", 107.87, 11, 5, groupColors[4], 1.93));
        ((ArrayList)elements.get(4)).add(new Element(48, "Cd", "Cadmium", 112.41, 12, 5, groupColors[4], 1.69));
        ((ArrayList)elements.get(4)).add(new Element(49, "In", "Indium", 114.82, 13, 5, groupColors[3], 1.78));
        ((ArrayList)elements.get(4)).add(new Element(50, "Sn", "Tin", 118.71, 14, 5, groupColors[3], 1.96));
        ((ArrayList)elements.get(4)).add(new Element(51, "Sb", "Antimony", 121.76, 15, 5, groupColors[3], 2.05));
        ((ArrayList)elements.get(4)).add(new Element(52, "Te", "Tellurium", 127.6, 16, 5, groupColors[3], 2.1));
        ((ArrayList)elements.get(4)).add(new Element(53, "I", "Iodine", 126.9, 17, 5, groupColors[3], 2.66));
        ((ArrayList)elements.get(4)).add(new Element(54, "Xe", "Xenon", 131.29, 18, 5, groupColors[1], (double)-1.0F));
        ((ArrayList)elements.get(5)).add(new Element(55, "Cs", "Cesium", 132.91, 1, 6, groupColors[2], 0.79));
        ((ArrayList)elements.get(5)).add(new Element(56, "Ba", "Barium", 137.33, 2, 6, groupColors[2], 0.89));
        ((ArrayList)elements.get(5)).add(new Element(57, "La", "Lanthanum", 138.91, 3, 6, groupColors[6], 1.1));
        ((ArrayList)elements.get(5)).add(new Element(72, "Hf", "Hafnium", 178.49, 18, 6, groupColors[4], 1.3));
        ((ArrayList)elements.get(5)).add(new Element(73, "Ta", "Tantalum", 180.95, 5, 7, groupColors[4], (double)1.5F));
        ((ArrayList)elements.get(5)).add(new Element(74, "W", "Tungsten", 183.84, 6, 7, groupColors[4], 2.36));
        ((ArrayList)elements.get(5)).add(new Element(75, "Re", "Rhenium", 186.21, 7, 7, groupColors[4], 1.9));
        ((ArrayList)elements.get(5)).add(new Element(76, "Os", "Osmium", 190.23, 8, 7, groupColors[4], 2.2));
        ((ArrayList)elements.get(5)).add(new Element(77, "Ir", "Iridium", 192.22, 9, 7, groupColors[4], 2.2));
        ((ArrayList)elements.get(5)).add(new Element(78, "Pt", "Platinum", 195.08, 10, 7, groupColors[4], 2.28));
        ((ArrayList)elements.get(5)).add(new Element(79, "Au", "Gold", 196.97, 11, 7, groupColors[4], 2.54));
        ((ArrayList)elements.get(5)).add(new Element(80, "Hg", "Mercury", 200.59, 12, 7, groupColors[4], (double)2.0F));
        ((ArrayList)elements.get(5)).add(new Element(81, "Tl", "Thallium", 204.38, 13, 7, groupColors[3], 1.62));
        ((ArrayList)elements.get(5)).add(new Element(82, "Pb", "Lead", 207.2, 14, 7, groupColors[3], (double)1.75F));
        ((ArrayList)elements.get(5)).add(new Element(83, "Bi", "Bismuth", 209.98, 15, 7, groupColors[3], 1.45));
        ((ArrayList)elements.get(5)).add(new Element(84, "Po", "Polonium", 209.98, 16, 7, groupColors[3], (double)2.0F));
        ((ArrayList)elements.get(5)).add(new Element(85, "At", "Astatine", (double)210.0F, 17, 7, groupColors[3], 2.2));
        ((ArrayList)elements.get(5)).add(new Element(86, "Rn", "Radon", (double)222.0F, 18, 7, groupColors[1], (double)-1.0F));
        ((ArrayList)elements.get(6)).add(new Element(87, "Fr", "Francium", (double)223.0F, 1, 7, groupColors[2], 0.7));
        ((ArrayList)elements.get(6)).add(new Element(88, "Ra", "Radium", (double)226.0F, 2, 7, groupColors[2], 0.9));
        ((ArrayList)elements.get(6)).add(new Element(89, "Ac", "Actinium", (double)227.0F, 3, 7, groupColors[7], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(104, "Rf", "Rutherfordium", (double)267.0F, 4, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(105, "Db", "Dubnium", (double)270.0F, 5, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(106, "Sg", "Seaborgium", (double)271.0F, 6, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(107, "Bh", "Bohrium", (double)270.0F, 7, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(108, "Hs", "Hassium", (double)277.0F, 8, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(109, "Mt", "Meitnerium", (double)278.0F, 9, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(110, "Ds", "Darmstadtium", (double)281.0F, 10, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(111, "Rg", "Roentgenium", (double)282.0F, 11, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(112, "Cn", "Copernicium", (double)285.0F, 12, 7, groupColors[4], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(113, "Nh", "Nihonium", (double)286.0F, 13, 7, groupColors[3], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(114, "Fl", "Flerovium", (double)289.0F, 14, 7, groupColors[3], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(115, "Mc", "Moscovium", (double)290.0F, 15, 7, groupColors[3], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(116, "Lv", "Livermorium", (double)293.0F, 16, 7, groupColors[3], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(117, "Ts", "Tennessine", (double)294.0F, 17, 7, groupColors[3], 1.1));
        ((ArrayList)elements.get(6)).add(new Element(118, "Og", "Oganesson", (double)294.0F, 18, 7, groupColors[1], 1.1));

        for(int i = 0; i < 3; ++i) {
            ((ArrayList)elements.get(7)).add((Object)null);
        }

        ((ArrayList)elements.get(7)).add(new Element(58, "Ce", "Cerium", 140.12, 3, 6, groupColors[6], 1.12));
        ((ArrayList)elements.get(7)).add(new Element(59, "Pr", "Praseodymium", 140.12, 3, 6, groupColors[6], 1.13));
        ((ArrayList)elements.get(7)).add(new Element(60, "Nd", "Neodymium", 144.24, 3, 6, groupColors[6], 1.14));
        ((ArrayList)elements.get(7)).add(new Element(61, "Pm", "Promethium", (double)145.0F, 3, 6, groupColors[6], 1.13));
        ((ArrayList)elements.get(7)).add(new Element(62, "Sm", "Samarium", 150.36, 3, 6, groupColors[6], 1.17));
        ((ArrayList)elements.get(7)).add(new Element(63, "Eu", "Europium", 151.96, 3, 6, groupColors[6], 1.2));
        ((ArrayList)elements.get(7)).add(new Element(64, "Gd", "Gadolinium", (double)157.25F, 3, 6, groupColors[6], 1.2));
        ((ArrayList)elements.get(7)).add(new Element(65, "Tb", "Terbium", 158.93, 3, 6, groupColors[6], 1.1));
        ((ArrayList)elements.get(7)).add(new Element(66, "Dy", "Dysprosium", (double)162.5F, 3, 6, groupColors[6], 1.22));
        ((ArrayList)elements.get(7)).add(new Element(67, "Ho", "Holmium", 164.93, 3, 6, groupColors[6], 1.23));
        ((ArrayList)elements.get(7)).add(new Element(68, "Er", "Erbium", 167.26, 3, 6, groupColors[6], 1.24));
        ((ArrayList)elements.get(7)).add(new Element(69, "Tm", "Thulium", 168.93, 3, 6, groupColors[6], (double)1.25F));
        ((ArrayList)elements.get(7)).add(new Element(70, "Yb", "Ytterbium", 173.04, 3, 6, groupColors[6], 1.1));
        ((ArrayList)elements.get(7)).add(new Element(71, "Lu", "Lutetium", 174.97, 3, 6, groupColors[6], 1.27));
        ((ArrayList)elements.get(7)).add((Object)null);

        for(int i = 0; i < 3; ++i) {
            ((ArrayList)elements.get(8)).add((Object)null);
        }

        ((ArrayList)elements.get(8)).add(new Element(90, "Th", "Thorium", 232.04, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(91, "Pa", "Protactinium", 231.04, 3, 7, groupColors[7], (double)1.5F));
        ((ArrayList)elements.get(8)).add(new Element(92, "U", "Uranium", 238.03, 3, 7, groupColors[7], 1.38));
        ((ArrayList)elements.get(8)).add(new Element(93, "Np", "Neptunium", 237.04, 3, 7, groupColors[7], 1.36));
        ((ArrayList)elements.get(8)).add(new Element(94, "Pu", "Plutonium", 244.06, 3, 7, groupColors[7], 1.28));
        ((ArrayList)elements.get(8)).add(new Element(95, "Am", "Americium", 243.06, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(96, "Cm", "Curium", 247.07, 3, 7, groupColors[7], 1.28));
        ((ArrayList)elements.get(8)).add(new Element(97, "Bk", "Berkelium", 247.07, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(98, "Cf", "Californium", 251.08, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(99, "Es", "Einsteinium", 252.08, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(100, "Fm", "Fermium", 257.1, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(101, "Md", "Mendelevium", 258.1, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(102, "No", "Nobelium", 259.1, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add(new Element(103, "Lr", "Lawrencium", 262.11, 3, 7, groupColors[7], 1.3));
        ((ArrayList)elements.get(8)).add((Object)null);
    }

    static {
        groupColors = new Color[]{Color.LIGHTBLUE, Color.LIGHTGREEN, Color.LIGHTPINK, Color.LIGHTYELLOW, Color.LIGHTCORAL, Color.LIGHTGRAY, Color.LIGHTSALMON, Color.LIGHTSTEELBLUE};
    }
}
