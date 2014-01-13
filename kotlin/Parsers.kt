/**
 * Created by ivan on 1/13/14.
 */
import java.util.ArrayList
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.util.EntityUtils

fun parsePls(path: String): List<Pair<String, String>> {
    var res = ArrayList<Pair<String, String>>();
    val content = urlToString(path)
    for (line in content.split("\n")) {
        var path: String = "";
        var title: String = "";
        if (line.startsWith("File")) {
            path = line.split("=")[1].trim()
            res.add(Pair(title, path))
        }
        if (line.startsWith("Title")) {
            title = line.split("=")[1].trim()

        }


    }
    return res
}
fun urlToString(url: String): String {
    var client = DefaultHttpClient()
    val res = client.execute(HttpGet(url))
    return EntityUtils.toString(res?.getEntity())!!
}