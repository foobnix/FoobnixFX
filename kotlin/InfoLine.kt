/**
 * Created by ivan on 1/11/14.
 */
import javafx.scene.layout.HBox
import javafx.scene.control.Label

class InfoLine():HBox(){
    {
        getChildren()?.addAll(Label("Info | mp3 | 128 kb/s"));
    }
}