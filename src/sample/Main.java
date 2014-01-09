package sample;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Main extends Application {

    private EmbeddedMediaPlayer mediaListPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();

        HBox top = new HBox();
        Button menu =  new Button("Menu");
        Button stop =  new Button("Stop");
        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaListPlayer.pause();
            }
        });


        Button play =  new Button("Play");

        play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaListPlayer.play();
            }
        });

        Button pause =  new Button("Pause");

        Button plus =  new Button("+");
        Button minus =  new Button("-");


        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setShowTickMarks(true);


        final ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.25f);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.setMinWidth(200);
        progressBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double offset  = mouseEvent.getSceneX() - progressBar.getLayoutX();

                progressBar.setProgress(offset / progressBar.getWidth());
            }
        });



        top.getChildren().addAll(menu, stop, play, pause, minus, slider, plus, progressBar);


        VBox left = new VBox();






        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);

        Tab tab1 = new Tab();
        tab1.setText("Music Home");
        tab1.setContent(new Button("hello1"));

        Tab tab2 = new Tab();
        tab2.setText("Music ivan");
        tab2.setContent(new Button("hello2"));

        tabPane.getTabs().addAll(tab1,tab2);


        TabPane mainTabs = new TabPane();
        mainTabs.setSide(Side.BOTTOM);

        Tab mTab1 = new Tab();
        mTab1.setText("Music");
        mTab1.setClosable(false);
        mTab1.setContent(tabPane);

        Tab mTab2 = new Tab();
        mTab2.setClosable(false);
        mTab2.setText("Radio");
        mTab2.setContent(new Label("Radio"));

        mainTabs.getTabs().addAll(mTab1, mTab2);



        ListView<Music> list = new ListView<Music>();
        list.getItems().addAll(new Music("Music 1","asdf"),new Music("Music 2","asfsadf"));


        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(mainTabs, list);


        borderPane.setTop(top);
        borderPane.setCenter(splitPane);
        borderPane.setBottom(new Label("info | mp3 | 128 kbs"));



        MediaPlayerFactory factory = new MediaPlayerFactory();
        mediaListPlayer = factory.newEmbeddedMediaPlayer();

        String url = "/Users/ivanivanenko/Documents/Avicii feat. Aloe Blacc - Wake Me Up [pleer.com].mp3";
        String url1 = "http://s8-2.pleer.com/0157f8bee630579b762ec9e3cb04b43d90b2a23a31a19b8bb3005b85ec5ac505864f9b336777e2875f79d19d72549d065dad58702fd21a0a8d27ac5ab34d94631038b7701a395f4946a8b253850cc105371c999a7783e238171503f4042ce5b3df3cbd00/708e454222.mp3";
        mediaListPlayer.playMedia(url1);

        //borderPane.setRight(new JFXPanel(player));

        primaryStage.setScene(new Scene(borderPane));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                mediaListPlayer.stop();
                mediaListPlayer.release();
                factory.release();

            }
        });

        primaryStage.show();




    }


    public static void main(String[] args) {
        NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "/Applications/VLC.app/Contents/MacOS/lib"
        );
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        launch(args);
    }
}
