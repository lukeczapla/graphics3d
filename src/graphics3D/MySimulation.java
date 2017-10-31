package graphics3D;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Function<Integer, Integer> myFunction = x -> x / 2;
        Function<Integer, Integer> myFunction2 = x -> x * 2;
        Function<Integer, Integer> result = myFunction.compose(myFunction2);
        System.out.println(myFunction2.andThen(myFunction).apply(5));
        Predicate<Integer> t = x -> x > 4;

        List <Integer> mylist = Stream.of(2,3,4,5).filter(x -> x % 3 == 0).collect(Collectors.toList());
        System.out.println(mylist);
        // Set up and show the scene
        MyScene2 simulation = new MyScene2();
        primaryStage.setScene(simulation.getScene());
        primaryStage.show();

    }

}
