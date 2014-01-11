/**
 * Created by ivan on 1/11/14.
 */
import javafx.scene.layout.VBox
import javafx.scene.control.TabPane
import javafx.geometry.Side
import javafx.scene.control.Tab
import javafx.scene.control.Label
import javafx.scene.layout.Priority
import javafx.stage.Stage
import javafx.stage.DirectoryChooser

class LeftSide(stage: Stage?) : VBox(){
    {
        val addFolder = FxImageButton("star.png", "Add Music Folder")
        val fileChooser = DirectoryChooser()

        addFolder.setOnMouseClicked {
            val file = fileChooser.showDialog(stage)

            //file?.listFiles()?.forEach { println(it.path) }
            Core.updateList(file?.listFiles())

        }


        val tabPane = TabPane()
        tabPane.setSide(Side.LEFT)

        val tabAdd = Tab()
        tabAdd.setText("+")
        tabAdd.setClosable(false)

        tabAdd.setOnSelectionChanged {
            if (tabAdd.isSelected()) {
                val tab = Tab("Demo1")
                tab.setContent(FxTreeView())
                tabPane.getTabs()?.add(tab)
            }
        }

        val tab1 = Tab()

        tab1.setText("[Music Home]")



        tab1.setContent(FxTreeView())



        tabPane.getTabs()?.addAll(tabAdd, tab1)

        val mainTabs = TabPane()
        mainTabs.setSide(Side.BOTTOM)


        val mTab1 = Tab()

        val box = VBox()
        box.getChildren()?.addAll(Label("Muisc"), FxImageView("disk.png"))

        mTab1.setText("Music")
        mTab1.setGraphic(FxImageView("disk.png"))

        mTab1.setClosable(false)
        mTab1.setContent(tabPane)


        mainTabs.getTabs()?.addAll(mTab1)


        VBox.setVgrow(mainTabs, Priority.ALWAYS);



        getChildren()?.addAll(addFolder, mainTabs)
    }
}