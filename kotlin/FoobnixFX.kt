/**
 * Created by ivan on 1/10/14.
 */
import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.control.SplitPane
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

class FoobnixFX() : Application(){


    override fun start(parentStage: Stage?) {


        val split = SplitPane()
        split.getItems()?.addAll(LeftSide(parentStage))
        split.getItems()?.addAll(CenterLayout())
        split.setDividerPositions(0.2, 0.8)

        val layout = BorderPane()
        layout.setTop(TopBar())
        layout.setBottom(InfoLine())
        layout.setCenter(split)



        parentStage?.setScene(Scene(layout))
        parentStage?.setTitle("FoobnixFX")
        parentStage?.setResizable(true)
        parentStage?.setWidth(1000.0)
        parentStage?.setHeight(600.0)
        parentStage?.show()

        parentStage?.setOnCloseRequest {
            VlcMediaPlayer.release()
        }


    }

    fun run() {
        Application.launch()
    }


}
fun main(args: Array<String>) {
   FoobnixFX().run();
}