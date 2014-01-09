/**
 * Created by ivan on 1/9/14.
 */
class My(param:Int){
    val someValue = param;
}
fun main(args: Array<String>) {
    val my = My(3);
    print(my.someValue)
}