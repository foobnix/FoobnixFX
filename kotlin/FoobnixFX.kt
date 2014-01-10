/**
 * Created by ivan on 1/10/14.
 */
import javafx.application.Application
import javafx.stage.Stage

class FoobnixFX():Application(){


    override fun start(parentStage: Stage?) {
       parentStage?.setTitle("FoobnixFX")
       parentStage?.show();
    }

    fun run() {
        Application.launch();
    }


}
fun main(args: Array<String>) {
   FoobnixFX().run();
}