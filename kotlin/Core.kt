/**
 * Created by ivan on 1/11/14.
 */
import java.util.ArrayList
import java.io.File

object Core{
    val musicList = ArrayList<FxMusic>()

    var onListUpdated: (list: List<FxMusic>) -> Unit = { }
    var onSeekChanged: (value: Float) -> Unit = { }

    fun updateList(list: Array<File>?) {
        musicList.clear()
        list?.toList()?.forEachWithIndex {
            (i, it) ->
            println(it.getPath())
            musicList.add(FxMusic(i, it.getPath(), it.getName()))
        }
        onListUpdated(musicList)
    }
    fun onSeek(value: Float) {
        onSeekChanged(value)
    }

    fun getAllRadios(): List<FxMusic> {
        val list = RadioParser().getAll()
        return list.map { it->FxMusic(0, it.second, it.first) }
    }
    fun getAllMusic(): List<FxMusic> {
        return File("/home/ivan/Desktop/test_music")
        ?.listFiles()
        ?.sortBy { it.name }
        ?.filter { it.getName().toLowerCase().endsWith(".mp3") }
        ?.withIndices()
        ?.map({ it -> FxMusic(it.first + 1, it.second.path, it.second.name) })
        ?.toList()!!
    }
}


