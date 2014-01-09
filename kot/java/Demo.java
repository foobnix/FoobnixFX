/**
 * Created by ivan on 1/10/14.
 */
public class Demo {
    public void do(){
        My my = new My(12);
        int value = my.getSomeValue();
        System.out.print("" + value);
    }

    public static void main(String args[]){
        new Demo().do();
    }
}
