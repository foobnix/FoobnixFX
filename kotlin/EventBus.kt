/**
 * Created by ivan on 1/12/14.
 */
import java.util.HashMap
import java.util.ArrayList

class EventBus(){


    private val listeners = HashMap<String, MutableList<(Any?) -> Unit>>();

    fun on(event: String, listener: (Any?) -> Unit) {
        var l = listeners[event];
        if (l == null) {
            l = ArrayList<(Any?) -> Unit>();
            listeners.put(event, l!!);
        }
        l!!.add(listener)
    }

    fun emit(event: String, data: Any?) {
        var l = listeners[event];
        if (l != null) {
            for (listener in l!!.iterator()) {
                listener(data);
            }
        }
    }
}