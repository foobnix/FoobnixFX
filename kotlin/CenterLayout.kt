/**
 * Created by ivan on 1/11/14.
 */
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.scene.control.ChoiceBox
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import javafx.scene.control.Button
import javafx.scene.layout.Priority
import javafx.scene.web.WebView
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.Scene
import javafx.beans.value.ChangeListener
import javafx.concurrent.Worker.State
import javafx.concurrent.Worker
import javafx.beans.value.ObservableValue

class CenterLayout() : VBox(){
    {
        val searchLayout = HBox()
        val choose = ChoiceBox(FXCollections.observableArrayList("Search", "Vkontakte", "Genre"))
        choose.getSelectionModel()?.selectFirst()

        val searchLine = TextField()
        searchLine.setPromptText("Search ")
        searchLine.setMinWidth(100.0)
        val go = Button("Search")
        HBox.setHgrow(searchLine, Priority.ALWAYS)

        val browser = WebView();
        val webEngine = browser.getEngine();

        webEngine?.setJavaScriptEnabled(true)
        webEngine?.getLoadWorker()?.stateProperty()?.addListener(object:ChangeListener<State>{
            override fun changed(p0: ObservableValue<out Worker.State>?, p1: Worker.State?, p2: Worker.State?) {
                val rUrl: String = webEngine?.getLocation()!!
                val isRedirectUrl = rUrl.startsWith("http://vk.com/api/login_success.html#session=")!!

                if (p2 == Worker.State.SUCCEEDED && isRedirectUrl) {
                    VkApi().parserSesstion(rUrl)
                }
            }

        }

        )


        go.setOnMouseClicked {
            val url = VkApi().loginURL
            println("load url $url")
            webEngine?.load(url)

            val dialog = Stage();
            dialog.initStyle(StageStyle.UTILITY);
            val scene = Scene(browser);
            dialog.setScene(scene);
            dialog.show();


        }


        searchLayout.getChildren()?.addAll(choose, searchLine, go)

        val fxTreeView = FxTableView()
        VBox.setVgrow(fxTreeView, Priority.ALWAYS)

        getChildren()?.addAll(searchLayout, fxTreeView)

    }
}