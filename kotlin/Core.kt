/**
 * Created by ivan on 1/11/14.
 */
import java.util.ArrayList
import java.io.File

object Core{
    val musicList = ArrayList<FxMusic>()
    public var resultListener: ResultListener<List<FxMusic>> = object:ResultListener<List<FxMusic>>{
        override fun getResult(result: List<FxMusic>) {
            println("gogogo")
        }
    }

    fun updateList(list: Array<File>?) {
        musicList.clear()
        list?.toList()?.forEachWithIndex {
            (i, it) ->
            println(it.getPath())
            musicList.add(FxMusic(i, it.getPath(),it.getName()))
        }
        if (resultListener != null) {
            resultListener.getResult(musicList)
        }

    }



}

trait ResultListener<T> {
    public open fun getResult(resutl: T): Unit
}
