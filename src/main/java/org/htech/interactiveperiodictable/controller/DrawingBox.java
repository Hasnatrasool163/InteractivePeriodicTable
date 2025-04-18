package org.htech.interactiveperiodictable.controller;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.PointLight;
import javafx.scene.AmbientLight;

public class DrawingBox extends Application {

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    @Override
    public void start(Stage stage) {
        Box baseCube = new Box(150, 150, 150);
        baseCube.setMaterial(new PhongMaterial(Color.LIGHTGRAY));

        Box front = new Box(150, 150, 1); front.setMaterial(new PhongMaterial(Color.RED)); front.setTranslateZ(-75);
        Box back = new Box(150, 150, 1); back.setMaterial(new PhongMaterial(Color.GREEN)); back.setTranslateZ(75);
        Box top = new Box(150, 1, 150); top.setMaterial(new PhongMaterial(Color.BLUE)); top.setTranslateY(-75);
        Box bottom = new Box(150, 1, 150); bottom.setMaterial(new PhongMaterial(Color.YELLOW)); bottom.setTranslateY(75);
        Box left = new Box(1, 150, 150); left.setMaterial(new PhongMaterial(Color.ORANGE)); left.setTranslateX(-75);
        Box right = new Box(1, 150, 150); right.setMaterial(new PhongMaterial(Color.PURPLE)); right.setTranslateX(75);

        Group cubeGroup = new Group(baseCube, front, back, top, bottom, left, right);
        cubeGroup.getTransforms().addAll(rotateX, rotateY);
        cubeGroup.setTranslateX(300.0);
        cubeGroup.setTranslateY(150.0);
        cubeGroup.setTranslateZ(150.0);

        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(400);
        pointLight.setTranslateY(-100);
        pointLight.setTranslateZ(-300);

        AmbientLight ambientLight = new AmbientLight(Color.rgb(80, 80, 80));

        PerspectiveCamera cam = new PerspectiveCamera();
        cam.setTranslateX(-150);
        cam.setTranslateY(25);
        cam.setTranslateZ(150);

        Group root = new Group(cubeGroup, pointLight, ambientLight);
        Scene scene = new Scene(root, 595, 300);
        scene.setFill(Color.LIGHTSLATEGRAY);
        scene.setCamera(cam);

        initMouseControl(cubeGroup, scene);

        stage.setTitle("3D Cube with Lighting & Mouse Control");
        stage.setScene(scene);
        stage.show();
    }

    private void initMouseControl(Group group, Scene scene) {
        scene.setOnMousePressed((MouseEvent event) -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });

        scene.setOnMouseDragged((MouseEvent event) -> {
            rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
            rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
