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
        val pathCol = TableColumn<FxMusic, String>("Path");
        val textCol = TableColumn<FxMusic, String>("Text");

        numCol.cellFactory({ it.id })
        playCol.cellFactory({ it -> if (it.isActive) "▶" else "" })
        pathCol.cellFactory({ it.path })
        textCol.cellFactory({ it.text })


        val list: ObservableList<FxMusic>? = FXCollections.observableArrayList(
                FxMusic(1, "path1", "Name1"),
                FxMusic(2, "path2", "Name2", true),
                FxMusic(3, "path3", "Name3")
        );




        Core.onListUpdated = { musicList ->
            list?.clear()
            list?.addAll(musicList)
            setItems(list)
        }

        setOnMouseClicked { event ->
            if (event?.getClickCount() == 2) {
                MediaPlayer.play(getSelectionModel()?.getSelectedItem()?.path!!)
            }
        }

        setItems(list)

        getSelectionModel()?.setSelectionMode(SelectionMode.SINGLE);
        getColumns()?.addAll(playCol, numCol, pathCol, textCol)
    }


}