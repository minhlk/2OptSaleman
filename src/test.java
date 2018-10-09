import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.remove(3);
        System.out.println(arr);
    }
}
