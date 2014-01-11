/**
 * Created by ivan on 1/10/14.
 */
import javafx.scene.control.Button

class FxImageButton(res: String,text: String = "", rotate: Int=0) : Button(){
    {
        setText(text)
        val fxImageView = FxImageView(res)
        if (rotate != 0) {
            fxImageView.setRotate(rotate.toDouble())
        }
        setGraphic(fxImageView)
        //setStyle("-fx-padding: 1 1 1 1;")
        getStylesheets()?.add(getClass().getResource("button.css")?.toExternalForm()!!)

    }

}