import java.util.ArrayList;

/**
 * Created by Evgeniy on 21.11.2016.
 * Main method of My array list
 */
public class MainArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            arrayList.add(i);
        }

        System.out.println("Input ArrayList:");
        System.out.println(arrayList);
    }
}
