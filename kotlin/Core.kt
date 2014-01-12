/**
 * Created by ivan on 1/11/14.
 */
import java.util.ArrayList
import java.io.File

object Core{
    val musicList = ArrayList<FxMusic>()
    var onListUpdated: (list: List<FxMusic>) -> Unit = { }

    fun updateList(list: Array<File>?) {
        musicList.clear()
        list?.toList()?.forEachWithIndex {
            (i, it) ->
            println(it.getPath())
            musicList.add(FxMusic(i, it.getPath(), it.getName()))
        }
        onListUpdated(musicList)
    }
}


