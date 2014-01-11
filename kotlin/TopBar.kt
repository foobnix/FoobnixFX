/**
 * Created by ivan on 1/11/14.
 */
import javafx.scene.layout.HBox
import javafx.scene.control.Separator
import javafx.scene.control.Slider
import javafx.scene.layout.Priority
import javafx.scene.control.ProgressBar
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

class TopBar() : HBox(){
    {
        val menuButton = FxImageButton("menu2.png")

        val separator1 = Separator()

        val stop = FxImageButton("stop2.png")
        val play = FxImageButton("play3.png")
        val pause = FxImageButton("pause2.png")
        val prev = FxImageButton("backward2.png")
        val next = FxImageButton("forward3.png")

        val separator2 = Separator()

        val shuffle = FxImageButton("redo.png")
        val repeat = FxImageButton("forward.png")

        val separator3 = Separator()
        val min = FxImageButton("volume-decrease.png")
        val plus = FxImageButton("volume-increase.png")

        val slider = Slider()
        slider.setMin(0.0)
        slider.setMax(150.0)
        slider.setValue(50.0)
        slider.setShowTickMarks(true);
        slider.setMaxHeight(java.lang.Double.MAX_VALUE)

        slider.valueProperty()?.addListener(object:ChangeListener<Number>{
            override fun changed(p0: ObservableValue<out Number>?, p1: Number?, p2: Number?) {
                MediaPlayer.setVolume(slider?.getValue().toInt());
            }

        })


        val separator4 = Separator()

        val progressBar = ProgressBar()
        progressBar.setProgress(0.25)
        progressBar.setMaxWidth(java.lang.Double.MAX_VALUE)
        progressBar.setMinWidth(200.0)
        progressBar.setOnMouseClicked {(mouseEvent) ->
            var offset = mouseEvent?.getSceneX()!! - progressBar?.getLayoutX();
            progressBar.setProgress(offset / progressBar.getWidth());
        }

        progressBar.setMaxHeight(java.lang.Double.MAX_VALUE)

        HBox.setHgrow(progressBar, Priority.ALWAYS);


        getChildren()?.addAll(
                menuButton,
                separator1,
                stop,
                play,
                pause,
                prev,
                next,
                separator2,
                shuffle,
                repeat,
                separator3,
                min,
                slider,
                plus,
                separator4,
                progressBar)
    }

}