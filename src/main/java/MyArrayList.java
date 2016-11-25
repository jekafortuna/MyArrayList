import java.util.*;

/**
 * Created by Evgeniy on 21.11.2016.
 * Sample of My array list
 */
public class MyArrayList<T> implements List<T>{

    public static final String ILLEGAL_CAPACITY = "Illegal capacity: ";
    public static final String NO_SUCH_INDEX = "No such index in this list. Size: ";

    private static final int DEFAULT_CAPACITY = 10;
    private T[] arrayList;
    private int size = 0;
    private int modCount = 0;

    public MyArrayList(int initialCapacity) {

        if (initialCapacity >= DEFAULT_CAPACITY){
            this.arrayList = (T[]) new Object[initialCapacity];
        }
        else if (initialCapacity > 0){
            this.arrayList = (T[]) new Object[DEFAULT_CAPACITY];
        }
        else {
            throw new IllegalArgumentException(ILLEGAL_CAPACITY + initialCapacity);
        }
    }

    public void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException(NO_SUCH_INDEX + size);
        }
    }

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

//    @Override
//    public <T1> T1[] toArray(T1[] a) {
//        return null;
//    }

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
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            if (!contains(iterator.next())){
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isModified = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()){
            add((T) iterator.next());
            isModified = true;
        }
        return isModified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index);
        boolean isModified = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            add(index++, (T)iterator.next());
            isModified = true;
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            Object elem = iterator.next();
            if (contains(elem)) {
                remove(elem);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isModified = false;
        Iterator iterator = MyArrayList.this.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public void clear() {
        Iterator iterator = MyArrayList.this.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arrayList[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        arrayList[index] = element;
        modCount++;
        return (T) arrayList[index];
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        ensureCapacity(size + 1);

        int toAdd = size - index;

        System.arraycopy(arrayList, index, arrayList, index + 1, toAdd);
        arrayList[index] = (T) element;
        size ++;
        modCount ++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        int toRemove = size - index - 1;

        T removedElement = (T) arrayList[index];
        if (toRemove > 0) {
            System.arraycopy(arrayList, index + 1, arrayList, index, toRemove);
        }
        arrayList[--size] = null;
        modCount ++;
        return removedElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (arrayList[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (arrayList[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndex(index);
        return listIterator(-1);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        MyArrayList subList = new MyArrayList();

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        else if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            for (int i = 0; i < arrayList.length; i++) {
                if (i >= fromIndex && i < toIndex) {
                    subList.add(arrayList[i]);
                }
            }
        }
        return subList;
    }

    @Override
    public Object[] toArray(Object[] a) {
        T[] trimmedArr = (T[]) new Object[size];
        System.arraycopy(arrayList, 0, trimmedArr, 0, size);
        return trimmedArr;
    }
}
