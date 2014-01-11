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

class FxTableView() : TableView<FxMusic>(){
    {
        val playCol = TableColumn<FxMusic, String>(" ");
        val numCol = TableColumn<FxMusic, Int>("N");
        val pathCol = TableColumn<FxMusic, String>("Path");
        val textCol = TableColumn<FxMusic, String>("Text");


        playCol.setCellValueFactory(object : Callback<TableColumn.CellDataFeatures<FxMusic, String>, ObservableValue<String>> {
            override fun call(p0: TableColumn.CellDataFeatures<FxMusic, String>?): ObservableValue<String>? {
                val playString = if (p0?.getValue()?.isActive!!) "▶" else ""
                return ReadOnlyObjectWrapper<String>(playString)
            }
        })
        numCol.setCellValueFactory(object : Callback<TableColumn.CellDataFeatures<FxMusic, Int>, ObservableValue<Int>> {
            override fun call(p0: TableColumn.CellDataFeatures<FxMusic, Int>?): ObservableValue<Int>? {
                return ReadOnlyObjectWrapper<Int>(p0?.getValue()?.id)
            }
        })
        pathCol.setCellValueFactory(object : Callback<TableColumn.CellDataFeatures<FxMusic, String>, ObservableValue<String>> {
            override fun call(p0: TableColumn.CellDataFeatures<FxMusic, String>?): ObservableValue<String>? {
                return ReadOnlyObjectWrapper<String>(p0?.getValue()?.path)
            }
        })
        textCol.setCellValueFactory(object : Callback<TableColumn.CellDataFeatures<FxMusic, String>, ObservableValue<String>> {
            override fun call(p0: TableColumn.CellDataFeatures<FxMusic, String>?): ObservableValue<String>? {
                return ReadOnlyObjectWrapper<String>(p0?.getValue()?.text)
            }
        })


        val list: ObservableList<FxMusic>? = FXCollections.observableArrayList(
                FxMusic(1, "path", "Name1"),
                FxMusic(2, "path", "Name2", true),
                FxMusic(3, "path", "Name3")
        );


        Core.resultListener = object:ResultListener<List<FxMusic>>{
            override fun getResult(result: List<FxMusic>) {
                list?.clear()
                list?.addAll(result)
                setItems(list)
            }

        }

        setOnMouseClicked(object:EventHandler<MouseEvent?> {
            override fun handle(p0: MouseEvent?) {
                if (p0?.getClickCount() == 2) {
                    MediaPlayer.play(getSelectionModel()?.getSelectedItem()?.path!!)
                }
            }
        });

        setItems(list)

        getSelectionModel()?.setSelectionMode(SelectionMode.SINGLE);
        getColumns()?.addAll(playCol, numCol, pathCol, textCol)
    }


}