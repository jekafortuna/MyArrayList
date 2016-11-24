import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Evgeniy on 21.11.2016.
 * Sample of My array list
 */
public class MyArrayList<T> implements List<T>{

    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size;
    private int modCount = 0;

    public MyArrayList(){
        this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity){
        int newCapacity = arrayList.length;

        if (minCapacity > newCapacity) {
            newCapacity = (newCapacity * 3) / 2 + 1;
            T[] arrayNew = (T[]) new Object[newCapacity];
            System.arraycopy(arrayList, 0, arrayNew, 0, arrayList.length);
            arrayList = arrayNew;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (arrayList[i].equals(o)) {
                    return true;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);
        arrayList[size++] = (T) element;
        modCount++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i].equals(o)){
                remove(i);
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
