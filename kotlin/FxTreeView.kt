/**
 * Created by ivan on 1/11/14.
 */

import javafx.scene.control.TreeView
import javafx.scene.control.TreeItem

class FxTreeView() : TreeView<String>(){
    {
        val root = TreeItem<String>()

        for (i in 1..10) {
            val folder = TreeItem<String>("[Album name $i]")
            folder.setExpanded(false)
            for (j in 1..100) {
                val item = TreeItem<String>("Song $j")
                folder.getChildren()?.add(item)

            }
            root.getChildren()?.add(folder)
        }

        setShowRoot(false)
        setRoot(root)




    }

}