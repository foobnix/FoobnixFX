import javafx.scene.control.TableView
import javafx.scene.control.TableColumn
import javafx.scene.control.SelectionMode
import javafx.collections.ObservableList
import javafx.collections.FXCollections
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import java.util.List
import java.io.File
import java.util.ArrayList
import javafx.scene.control.TableCell
import javafx.scene.image.Image
import javafx.scene.image.ImageView

fun <T>TableColumn<FxMusic, T>.cellFactory(field: (FxMusic) -> T) {
    this.setCellValueFactory(object : Callback<TableColumn.CellDataFeatures<FxMusic, T>, ObservableValue<T>> {
        override fun call(p0: TableColumn.CellDataFeatures<FxMusic, T>?): ObservableValue<T>? {
            return ReadOnlyObjectWrapper<T>(field(p0?.getValue()!!))
        }
    })
}



class FxTableView() : TableView<FxMusic>(){
    {
        val playCol = TableColumn<FxMusic, String>(" ");
        val numCol = TableColumn<FxMusic, Int>("N");
        val imageCol = TableColumn<FxMusic, String>("Image");
        val pathCol = TableColumn<FxMusic, String>("Path");
        val textCol = TableColumn<FxMusic, String>("Text");

        numCol.cellFactory({ it.id })
        playCol.cellFactory({ it -> if (it.isActive) "▶" else "" })
        pathCol.cellFactory({ it.path })
        textCol.cellFactory({ it.text })

        imageCol.setCellFactory(object: Callback<javafx.scene.control.TableColumn<FxMusic,String>,TableCell<FxMusic,String>>{
            override fun call(p0: TableColumn<FxMusic, String>?): TableCell<FxMusic, String>? {
                var cell = TableCell<FxMusic,String>()
                var image = Image("http://www.migu-music.com/img/play.png")

                val imageView = ImageView(image)
                imageView.setFitHeight(20.0)
                imageView.setFitWidth(20.0)
                imageView.setCache(true)
                //cell.setGraphic(FxImageView("play.png"))
                cell.setGraphic(imageView)
                return cell
            }


        })



        val list: ObservableList<FxMusic>? = FXCollections.observableArrayList(Core.getAllRadios());



        Core.onListUpdated = {
            musicList ->
            list?.clear()
            list?.addAll(musicList)
            setItems(list)
        }

        setOnMouseClicked { event ->
            if (event?.getClickCount() == 2) {
                var path = getSelectionModel()?.getSelectedItem()?.path!!
                if (path.endsWith(".pls")) {
                    path = parsePls(path).get(0).second
                }
                VlcMediaPlayer.play(path)
            }
        }

        setItems(list)

        getSelectionModel()?.setSelectionMode(SelectionMode.SINGLE);
        getColumns()?.addAll(playCol, imageCol, numCol, pathCol, textCol)
    }


}