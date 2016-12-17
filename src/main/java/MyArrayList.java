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
            if (newCapacity < minCapacity){
                newCapacity = minCapacity;
            }
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
    public T[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(arrayList, 0, result, 0, size);
        return (T[]) result;
    }

//    @Override
//    public <E> E[] toArray(E[] destinationArray) {
//        Objects.requireNonNull(destinationArray);
//        if (destinationArray.length < size) {
//            throw new IllegalArgumentException(
//                    String.format(DESTINATION_ARRAY_UNSUFFICIENT_CAPACITY, size, array.length));
//        }
//        System.arraycopy(array, 0, destinationArray, 0, size);
//        return destinationArray;
//    }

    @Override
    public boolean add(T element) {
        ensureCapacity(size + 1);
        arrayList[size++] = (T) element;
        modCount++;
        return true;
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
    public boolean containsAll(Collection<?> collection) {
        Objects.requireNonNull(collection);
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            if (!contains(iterator.next())){
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        int quantityToAdd = collection.size();
        ensureCapacity(size + quantityToAdd);
        collection.forEach(element -> arrayList[size++] = element);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index);
        boolean isModified = false;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(index++, (T)iterator.next());
            isModified = true;
        }
        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean isRemoved = false;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (collection.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        modCount++;
        int oldSize = size;
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return size != oldSize;
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
            int index = 0;
            int modCount = MyArrayList.this.modCount;

            @Override
            public boolean hasNext() {
                if (index < size - 1) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return (T)arrayList[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return (T)arrayList[--index];
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(index--);
                modCount++;
            }

            @Override
            public void set(T t) {
                MyArrayList.this.set(index, t);
                modCount++;
            }

            @Override
            public void add(T t) {
                MyArrayList.this.add(index, t);
                modCount++;
            }

            private void checkMods() {
                if (modCount != MyArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
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
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            for (int i = 0; i < size - 1; i++) {
                sb.append(arrayList[i]).append(", ");
            }
            sb.append(arrayList[size - 1]);
        }
        return sb.append("]").toString();
    }

    @Override
    public Object[] toArray(Object[] a) {
        T[] trimmedArr = (T[]) new Object[size];
        System.arraycopy(arrayList, 0, trimmedArr, 0, size);
        return trimmedArr;
    }
}
