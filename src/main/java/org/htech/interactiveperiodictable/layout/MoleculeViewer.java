package org.htech.interactiveperiodictable.layout;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MoleculeViewer extends Application {

    @Override
    public void start(Stage stage) {
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

        // LIGHTING
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(0);
        light.setTranslateY(-100);
        light.setTranslateZ(-200);
        root.getChildren().add(light);

        AmbientLight ambientLight = new AmbientLight(Color.rgb(50, 50, 50));
        root.getChildren().add(ambientLight);

        // BUILD H2O
        List<Sphere> atoms = new ArrayList<>();
        Sphere oxygen = createAtom(Color.RED, 0, 0, 0);
        Sphere hydrogen1 = createAtom(Color.WHITE, -60, 40, 0);
        Sphere hydrogen2 = createAtom(Color.WHITE, 60, 40, 0);

        atoms.add(oxygen);
        atoms.add(hydrogen1);
        atoms.add(hydrogen2);

        Cylinder bond1 = createBond(oxygen, hydrogen1);
        Cylinder bond2 = createBond(oxygen, hydrogen2);

        root.getChildren().addAll(atoms);
        root.getChildren().addAll(bond1, bond2);

        Group container = new Group(subScene);
        Scene scene = new Scene(container, 600, 600);
        stage.setScene(scene);
        stage.setTitle("H2O Molecule 3D");
        stage.show();
    }

    private Sphere createAtom(Color color, double x, double y, double z) {
        Sphere sphere = new Sphere(25);
        sphere.setMaterial(new PhongMaterial(color));
        sphere.setTranslateX(x);
        sphere.setTranslateY(y);
        sphere.setTranslateZ(z);
        return sphere;
    }

    private Cylinder createBond(Sphere start, Sphere end) {
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

    private Rotate getRotation(double dx, double dy, double dz) {
        double angle = Math.toDegrees(Math.acos(dz / Math.sqrt(dx * dx + dy * dy + dz * dz)));
        Point3D axis = new Point3D(-dy, dx, 0);
        return new Rotate(angle, axis);
    }

    public static void main(String[] args) {
        launch(args);
    }
}