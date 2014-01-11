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


        searchLayout.getChildren()?.addAll(choose, searchLine, go)

        val fxTreeView = FxTableView()
        VBox.setVgrow(fxTreeView, Priority.ALWAYS)

        getChildren()?.addAll(searchLayout, fxTreeView)

    }
}