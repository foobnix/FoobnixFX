/**
 * Created by ivan on 1/12/14.
 */


import java.io.File
import java.util.ArrayList

class RadioParser() : Object(){
    fun getAll(): List<Pair<String, String>> {
        var result = ArrayList<Pair<String, String>>()

        var root = File(getClass()?.getResource("radio")?.getPath().toString())
        root?.listFiles()?.forEach {
            result.addAll(printFile(it.getPath()))
        }
        return result
    }
    fun printFile(path: String): List<Pair<String, String>> {
        var result = ArrayList<Pair<String, String>>()
        File(path).forEachLine {
            line ->

            val sub = line.split("=")
            result.add(Pair(sub[0].trim(), sub[1].trim()));
        }
        return result
    }
}