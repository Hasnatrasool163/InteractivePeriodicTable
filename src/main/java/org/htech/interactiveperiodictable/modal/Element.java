package org.htech.interactiveperiodictable.modal;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;

public class Element {
    public static final DataFormat ELEMENT_DATA_FORMAT = new DataFormat("element-data");
    private final int atomicNumber;
    private final String symbol;
    private final String name;
    private final double mass;
    private final Color color;
    private final int Group;
    private final int Period;
    private final double electronegativity;
    private final Map<DataFormat, Object> elementData = new HashMap();

    public Element(int atomicNumber, String symbol, String name, double mass, int group, int period, Color color, double electronegativity) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.mass = mass;
        this.Group = group;
        this.Period = period;
        this.color = color;
        this.electronegativity = electronegativity;
        this.elementData.put(ELEMENT_DATA_FORMAT, symbol);
    }

    public int getAtomicNumber() {
        return this.atomicNumber;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public double getMass() {
        return this.mass;
    }

    public Color getColor() {
        return this.color;
    }

    public int getGroup() {
        return this.Group;
    }

    public int getPeriod() {
        return this.Period;
    }

    public Map<DataFormat, Object> getElementData() {
        return this.elementData;
    }

    public double getElectronegativity() {
        return this.electronegativity;
    }

    public String toString() {
        return MessageFormat.format("Element'{'atomicNumber={0}, symbol=''{1}'', name=''{2}'', mass={3}, Group={4}, Period={5}' ,Electronegativity{6}}'", this.atomicNumber, this.symbol, this.name, this.mass, this.Group, this.Period, this.electronegativity);
    }
}
