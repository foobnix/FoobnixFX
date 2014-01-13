/**
 * Created by ivan on 1/13/14.
 */

import junit.framework.TestCase
import kotlin.test.assertEquals

class DemoTest() : TestCase(){

    fun testMD5() {
        assertEquals("4a5bf45c9fe5c66d3afa73d8520fe46a",VkApi().mpd5("100172api_id=1854119fields=photo,sexformat=JSONmethod=getProfilesuids=100172v=3.0655df68ded"))
    }


}