package org.htech.interactiveperiodictable.controller;

import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.htech.interactiveperiodictable.modal.Element;
import org.htech.interactiveperiodictable.modal.MoleculeAtom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoleculeViewer {

    private static final Map<String, Color> ELEMENT_COLORS = new HashMap<>();

    static {
        ELEMENT_COLORS.put("H", Color.web("#FFFFFF"));
        ELEMENT_COLORS.put("He", Color.web("#FF6F00"));
        ELEMENT_COLORS.put("Li", Color.web("#C4A300"));
        ELEMENT_COLORS.put("Be", Color.web("#007A3D"));
        ELEMENT_COLORS.put("B", Color.web("#FF5C8D"));
        ELEMENT_COLORS.put("C", Color.web("#616161"));
        ELEMENT_COLORS.put("N", Color.web("#D50000"));
        ELEMENT_COLORS.put("O", Color.web("#FF9100"));
        ELEMENT_COLORS.put("F", Color.web("#76FF03"));
        ELEMENT_COLORS.put("Ne", Color.web("#00E5FF"));
    }

    private static Color getColorByElement(String symbol) {
        return ELEMENT_COLORS.getOrDefault(symbol, Color.GRAY);
    }

    public static void showFromElements(List<Element> elements) {
        List<MoleculeAtom> atoms = new ArrayList<>();

        for (Element e : elements) {
            double x = e.getGroup() * 60;
            double y = e.getPeriod() * 60;
            double z = 0;

            Color atomColor = getColorByElement(e.getSymbol());

            atoms.add(new MoleculeAtom(
                    e.getSymbol(),
                    atomColor,
                    x, y, z
            ));
        }

        show(atoms);
    }

    public static void show(List<MoleculeAtom> atoms) {
        Platform.runLater(() -> {
            Group root = new Group();
            SubScene subScene = new SubScene(root, 600, 600);
            subScene.setFill(Color.web("#202020"));

            PerspectiveCamera camera = new PerspectiveCamera();
            camera.getTransforms().addAll(
                    new Rotate(-20, Rotate.X_AXIS),
                    new Rotate(-20, Rotate.Y_AXIS),
                    new Translate(0, 0, -400)
            );
            subScene.setCamera(camera);

            Rotate rotationX = new Rotate(-20, Rotate.X_AXIS);
            Rotate rotationY = new Rotate(-20, Rotate.Y_AXIS);
            camera.getTransforms().addAll(rotationX, rotationY);

            PointLight light = new PointLight(Color.WHITE);
            light.setTranslateX(0);
            light.setTranslateY(-100);
            light.setTranslateZ(-200);
            root.getChildren().add(light);
            root.getChildren().add(new AmbientLight(Color.rgb(50, 50, 50)));

            List<Sphere> spheres = new ArrayList<>();
            for (MoleculeAtom atom : atoms) {
                Sphere sphere = new Sphere(25);
                sphere.setMaterial(new PhongMaterial(atom.getColor()));
                sphere.setTranslateX(atom.getX());
                sphere.setTranslateY(atom.getY());
                sphere.setTranslateZ(atom.getZ());
                spheres.add(sphere);
                root.getChildren().add(sphere);
            }

            for (int i = 0; i < spheres.size(); i++) {
                for (int j = i + 1; j < spheres.size(); j++) {
                    double dist = distance(spheres.get(i), spheres.get(j));
                    if (dist <= 130) {
                        Cylinder bond = createBond(spheres.get(i), spheres.get(j));
                        root.getChildren().add(bond);
                    }
                }
            }

            updateAtomPositions(atoms, spheres, rotationX, rotationY);

            Group container = new Group(subScene);
            Scene scene = new Scene(container, 600, 600);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(scene);
            stage.setTitle("Molecule Viewer 3D");
            stage.show();
        });
    }

    private static void updateAtomPositions(List<MoleculeAtom> atoms, List<Sphere> spheres, Rotate rotationX, Rotate rotationY) {
        for (int i = 0; i < atoms.size(); i++) {
            MoleculeAtom atom = atoms.get(i);
            spheres.get(i).setTranslateX(atom.getX());
            spheres.get(i).setTranslateY(atom.getY());
            spheres.get(i).setTranslateZ(atom.getZ());
        }

        rotationX.setAngle(rotationX.getAngle() + 1);
        rotationY.setAngle(rotationY.getAngle() + 1);
    }

    private static Cylinder createBond(Sphere start, Sphere end) {
        double dx = end.getTranslateX() - start.getTranslateX();
        double dy = end.getTranslateY() - start.getTranslateY();
        double dz = end.getTranslateZ() - start.getTranslateZ();
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        Cylinder bond = new Cylinder(6, distance);
        bond.setMaterial(new PhongMaterial(Color.LIGHTGRAY));

        bond.setTranslateX((start.getTranslateX() + end.getTranslateX()) / 2);
        bond.setTranslateY((start.getTranslateY() + end.getTranslateY()) / 2);
        bond.setTranslateZ((start.getTranslateZ() + end.getTranslateZ()) / 2);

        Rotate rotate = getRotation(dx, dy, dz);
        bond.getTransforms().add(rotate);

        return bond;
    }

    private static Rotate getRotation(double dx, double dy, double dz) {
        double angle = Math.toDegrees(Math.acos(dz / Math.sqrt(dx * dx + dy * dy + dz * dz)));
        Point3D axis = new Point3D(-dy, dx, 0);
        return new Rotate(angle, axis);
    }

    private static double distance(Sphere s1, Sphere s2) {
        double dx = s2.getTranslateX() - s1.getTranslateX();
        double dy = s2.getTranslateY() - s1.getTranslateY();
        double dz = s2.getTranslateZ() - s1.getTranslateZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
