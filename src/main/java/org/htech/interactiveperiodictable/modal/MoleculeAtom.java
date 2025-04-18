package org.htech.interactiveperiodictable.modal;

import javafx.scene.paint.Color;

public class MoleculeAtom {
    private final String symbol;
    private final Color color;
    private final double x, y, z;

    public MoleculeAtom(String symbol, Color color, double x, double y, double z) {
        this.symbol = symbol;
        this.color = color;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getSymbol() { return symbol; }
    public Color getColor() { return color; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
}
