package graphics3D;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by luke on 4/22/17.
 */
public class MyScene2 {

    private int step = 0;
    private double oldX = 0, oldY = 0;

    private DoubleProperty angle1 = new SimpleDoubleProperty(0);
    private DoubleProperty angle2 = new SimpleDoubleProperty(0);
    private DoubleProperty angle3 = new SimpleDoubleProperty(0);
    private DoubleProperty angle4 = new SimpleDoubleProperty(0);
    private DoubleProperty angle5 = new SimpleDoubleProperty(0);
    private Rotate rotateA, rotateB, rotateC, rotateD, rotateE;
    private Group cycleModel;
    private final Scene scene;

    public Scene getScene() {
        return scene;
    }

    // this returns the surface material from an image file (filename)
    public PhongMaterial makeMaterial(String filename) {
        Image diffuseMap = new Image(MySimulation.class
                .getResource(filename)
                .toExternalForm());
        PhongMaterial myMaterial = new PhongMaterial();
        myMaterial.setDiffuseMap(diffuseMap);
        return myMaterial;
    }


    public MyScene2() {

        PhongMaterial earthMaterial = makeMaterial("earth.jpg");
        PhongMaterial moonMaterial = makeMaterial("moon.jpg");
        PhongMaterial sunMaterial = makeMaterial("sun.jpg");
        PhongMaterial jupiterMaterial = makeMaterial("jupiter.jpg");

        Sphere earth = new Sphere(80);
        earth.setTranslateX(0.0);
        earth.setTranslateY(0.0);
        earth.setTranslateZ(0.0);
        earth.setMaterial(earthMaterial);
        earth.setRotationAxis(new Point3D(0.1,1,0));

        Sphere moon = new Sphere(10);
        moon.setTranslateX(0.0);
        moon.setTranslateY(0.0);
        moon.setTranslateZ(120.0);
        moon.setMaterial(moonMaterial);
        moon.getTransforms().setAll(
                rotateA = new Rotate(0, 0.0, 0.0, -120.0, Rotate.Y_AXIS)
        );
        rotateA.angleProperty().bind(angle1);

        Group earthmoon = new Group(earth, moon);
        earthmoon.setTranslateX(400.0);
        earthmoon.getTransforms().addAll(
                rotateE = new Rotate(0, -400.0, 0.0, 0.0, Rotate.Y_AXIS)
        );
        rotateE.angleProperty().bind(angle5);

        Cylinder cylinder = new Cylinder(20.0, 200.0);
        cylinder.setRotate(90.0);
        cylinder.setTranslateX(150.0);

        /*
        Box box = new Box(70, 70, 30);
        box.setRotationAxis(new Point3D(1.0, 0.0, 0.0));
        box.setRotate(90.0);
        box.setTranslateY(700.00);
        */

        Sphere sun = new Sphere(50);
        sun.setTranslateX(0.0);
        sun.setTranslateY(0.0);
        sun.setTranslateZ(0.0);
        sun.setMaterial(sunMaterial);

        Sphere jupiter = new Sphere(100);
        jupiter.setTranslateX(0.0);
        jupiter.setTranslateY(600.0);
        jupiter.setTranslateZ(0);
        jupiter.setMaterial(jupiterMaterial);
        jupiter.getTransforms().addAll(
                rotateB = new Rotate(0, 0.0, -600.0, 0.0, Rotate.X_AXIS)
        );
        rotateB.angleProperty().bind(angle2);

        final Box xAxis = new Box(500.0, 5, 5);
        final Box yAxis = new Box(5, 500.0, 5);
        final Box zAxis = new Box(5, 5, 500.0);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKBLUE);
        material.setSpecularColor(Color.BLUE);
        xAxis.setMaterial(material);

        material = new PhongMaterial();
        material.setDiffuseColor(Color.DARKRED);
        material.setSpecularColor(Color.RED);
        yAxis.setMaterial(material);

        material = new PhongMaterial();
        material.setDiffuseColor(Color.BLACK);
        material.setSpecularColor(Color.SILVER);
        zAxis.setMaterial(material);


        Group root = new Group();

        Translate trans = new Translate(0, 0, 0.0);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("dreidel.fxml"));
//            MeshView mesh = fxmlLoader.load();
//            Group cycleModel = new Group();
//            cycleModel.getChildren().add(mesh);
            cycleModel = fxmlLoader.load();

//            cycleModel.setAutoSizeChildren(true);
//            cycleModel.setTranslateX(322.1522);
//            cycleModel.setTranslateY(289.706);
//            cycleModel.setTranslateZ(-100.0);

            cycleModel.setScaleX(1.0);
            cycleModel.setScaleY(1.0);
            cycleModel.setScaleZ(1.0);
            cycleModel.getTransforms().addAll(trans);

            //   cycleModel.setRotationAxis(Rotate.Z_AXIS);
            //   cycleModel.setRotate(180.0);
            //   cycleModel.setRotationAxis(Rotate.Y_AXIS);
            //   cycleModel.setRotate(180.0);
//            cycleModel.setTranslateY(50.0);

            cycleModel.setRotationAxis(new Point3D(0.0,0.0,1.0));

            MeshView wheel = (MeshView)cycleModel.lookup("#dreidel");
            if (wheel != null) {
                PhongMaterial mat = new PhongMaterial();
                mat.setDiffuseColor(Color.GHOSTWHITE);
                mat.setSpecularColor(Color.AZURE);
                wheel.setMaterial(mat);
            }
            root.getChildren().add(cycleModel);


        }
        catch (IOException e) {
            e.printStackTrace();
        }

        root.setStyle("-fx-background-color: transparent;");
        root.getChildren().addAll(earthmoon, sun, cylinder, jupiter);
        root.getChildren().addAll(xAxis, yAxis, zAxis);
        scene = new Scene(root, 1000, 700, true);
        final PerspectiveCamera camera = new PerspectiveCamera(true);


        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(-2000);
        camera.setRotationAxis(new Point3D(0.0,0.0,1.0));

        camera.getTransforms().addAll(
                rotateC = new Rotate(0, 0.0, 0.0, 2000.0, Rotate.X_AXIS),
                rotateD = new Rotate(0, 0.0, 0.0, 2000.0, Rotate.Y_AXIS)
        );
        rotateC.angleProperty().bind(angle3);
        rotateD.angleProperty().bind(angle4);

        scene.setCamera(camera);
        scene.setFill(Color.WHITE);

        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(0.0);
        pointLight.setTranslateY(1000.0);
        pointLight.setTranslateZ(-4000.0);
        root.getChildren().add(pointLight);
        PointLight pointLight2 = new PointLight(Color.WHITE);
        pointLight2.setTranslateX(0.0);
        pointLight2.setTranslateY(1000.0);
        pointLight2.setTranslateZ(4000.0);
        root.getChildren().add(pointLight2);
        // root.getChildren().add(new AmbientLight(Color.WHITE));


        // These are all the event handlers (EventHandler<Event> types) with lambdas for handle()

        scene.setOnMousePressed(event -> {
            oldX = event.getSceneX();
            oldY = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            angle4.setValue(angle4.doubleValue() - (oldX - event.getSceneX())/2.0);
            angle3.setValue(angle3.doubleValue() + (oldY - event.getSceneY())/2.0);
            oldX = event.getSceneX();
            oldY = event.getSceneY();
        });

        scene.setOnScroll(event -> {
            camera.setTranslateZ(camera.getTranslateZ()+event.getDeltaY());
            rotateC.setPivotZ(rotateD.getPivotZ()-event.getDeltaY());
            rotateD.setPivotZ(rotateD.getPivotZ()-event.getDeltaY());
        });

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                camera.setTranslateZ(camera.getTranslateZ()-5);
                rotateC.setPivotZ(rotateD.getPivotZ()+5);
                rotateD.setPivotZ(rotateD.getPivotZ()+5);
            }
            if (event.getCode() == KeyCode.UP) {
                camera.setTranslateZ(camera.getTranslateZ()+5);
                rotateC.setPivotZ(rotateD.getPivotZ()-5);
                rotateD.setPivotZ(rotateD.getPivotZ()-5);
            }
            if (event.getCode() == KeyCode.LEFT) {
                camera.setRotate(camera.getRotate()+2);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                camera.setRotate(camera.getRotate()-2);
            }
        });


        // this is the "Timer" for the animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            earth.setRotate(earth.getRotate()+6.0);
            angle1.setValue(angle1.getValue()+2.4);
            angle2.setValue(angle2.getValue()+0.8);
            angle5.setValue(angle5.getValue()+0.2);
            //sun.setRadius(sun.getRadius()+0.2);
            trans.setZ(trans.getZ()-step*0.0001);
            step++;
            // just for the plane:
            cycleModel.setRotate(cycleModel.getRotate()+3.0);

        }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

}
