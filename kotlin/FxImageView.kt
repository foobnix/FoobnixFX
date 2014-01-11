/**
 * Created by ivan on 1/10/14.
 */
import javafx.scene.image.ImageView
import javafx.scene.image.Image

class FxImageView(res: String) : ImageView(){
    {
        val path = "res/32px/$res"
        val inputStream = getClass()?.getResourceAsStream(path)
        if (inputStream == null) {
            throw IllegalAccessException("Invalid file $path")
        }
        val image = Image(inputStream)
        setImage(image)
    }
}