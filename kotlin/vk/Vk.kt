/**
 * Created by ivan on 1/13/14.
 */

import org.json.JSONObject
import java.net.URLDecoder
import java.security.MessageDigest
import java.nio.charset.Charset
import org.apache.commons.codec.binary.Hex

class VkApi{
    val url = "http://vk.com/login.php"
    val app = "2234333"
    val layout = "popup"
    val settings = ""

    public val loginURL: String = "http://vk.com/login.php?app=$app&layout=$layout&type=browser&settings=$settings"

    //"sid":"ae0885e41cc80794d6775a37efafb77ba2e6b6d1d9aa65708b4df42bb62aa4666232b2b1a421d9b0ce7fc"
    // http://vk.com/api/login_success.html#session=%7B%22mid%22%3A6851750%2C%22secret%22%3A%225d5303417d%22%2C%22sid%22%3A%22513b0e85dbf35bb7843556d547974aa21451bf45db4201f00d3b33e36e3411b402a005c4afcff502b3b99%22%2C%22expire%22%3A1389623058%7D
    fun parserSesstion(path: String): String {
        println(path)
        var json: String? = path.replace("http://vk.com/api/login_success.html#session=", "")
        json = URLDecoder.decode(json.toString(), "UTF-8")

        println("json= $json")

        val jsonObject = JSONObject(json)
        jsonObject.getString("mid")
        jsonObject.getString("sid")
        val secret = jsonObject.getString("secret")!!
        jsonObject.getString("expire")
        jsonObject.getString("sig")

        println(secret)
        return secret
    }
    fun mpd5(item: String): String {
        var messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(item.getBytes(Charset.forName("UTF8")));
        var resultByte = messageDigest.digest();
        return String(Hex.encodeHex(resultByte)!!);

    }
}