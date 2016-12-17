import java.util.ArrayList;
import java.util.Iterator;

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

        arrayList.add(5,100);
        System.out.println("After addition 100: \n" + arrayList);

        System.out.println("After deletion 100:\n" + arrayList.remove(5));
        System.out.println(arrayList);

        System.out.println("LastIndexOf '0' : " + arrayList.lastIndexOf(0));
        System.out.println("LastIndexOf '7' : " + arrayList.lastIndexOf(7));
        System.out.println("Set: " + arrayList.set(5,100));
        System.out.println(arrayList);

        System.out.println("Remove (object) '10' (boolean) - " + arrayList.remove( new Integer("10")));
        System.out.println(arrayList);
        System.out.println("Contains Object '10': " + arrayList.contains(10));

        Iterator iterator = arrayList.iterator();
        System.out.print("Iterator: ");

        while (iterator.hasNext()){
            System.out.print(iterator.next() + "; ");
//            iterator.remove();
        }

        System.out.println();
        System.out.println("==============================================================");

        System.out.println(arrayList);
        System.out.println("==============================================================");
    }
}
