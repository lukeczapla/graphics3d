package graphics3D;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Created by luke on 4/18/17.
 */
public class MySimulation extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        // Play an MP3 file code below
        /*
        Media media = new Media(this.getClass().getResource("example.mp3").toExternalForm());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        */

        // Set up and show the scene
        MyScene simulation = new MyScene();
        primaryStage.setScene(simulation.getScene());
        primaryStage.show();

    }

}
