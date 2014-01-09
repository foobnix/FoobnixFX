package sample;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

        HBox top = new HBox();
        Button menu = new Button("Menu");
        Button stop = new Button("Stop");
        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaListPlayer.pause();
            }
        });

        Button play = new Button("Play");

        play.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaListPlayer.play();
            }
        });

        Button pause = new Button("Pause");

        Button plus = new Button("+");
        Button minus = new Button("-");


        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(150);
        slider.setValue(40);
        slider.setShowTickMarks(true);
        slider.setValueChanging(true);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println("" + number.intValue());
                mediaListPlayer.setVolume(number.intValue());
            }
        });



        final ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.25f);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.setMinWidth(200);
        progressBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double offset = mouseEvent.getSceneX() - progressBar.getLayoutX();

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

        tabPane.getTabs().addAll(tab1, tab2);


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
        list.getItems().addAll(new Music("Music 1", "asdf"), new Music("Music 2", "asfsadf"));


        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(mainTabs, list);


        borderPane.setTop(top);
        borderPane.setCenter(splitPane);
        borderPane.setBottom(new Label("info | mp3 | 128 kbs"));


        MediaPlayerFactory factory = new MediaPlayerFactory();
        mediaListPlayer = factory.newEmbeddedMediaPlayer();

        String url = "/Users/ivanivanenko/Documents/Avicii feat. Aloe Blacc - Wake Me Up [pleer.com].mp3";
        String url1 = "http://relay.myradio.ua/newjazz128.mp3";
        mediaListPlayer.playMedia(url1);
        slider.setValue(mediaListPlayer.getVolume());


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
        String os = System.getProperty("os.name").toLowerCase();
        String libPath = "";
        if ("linux".equals(os)) {
            libPath = "/usr/lib/vlc";
        } else if ("mac".equals(os)) {
            libPath = "/Applications/VLC.app/Contents/MacOS/lib";
        } else if ("win".equals(os)) {
            libPath = "path to vlc";
        }else{
            throw new RuntimeException("Platform not supported");
        }
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), libPath);


        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        launch(args);
    }
}
