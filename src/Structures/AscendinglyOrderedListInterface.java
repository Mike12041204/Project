package Structures;
public interface AscendinglyOrderedListInterface<T, KT>
{
    public boolean isEmpty();
    public void add(T item) throws ListIndexOutOfBoundsException;
    public T get(int index) throws ListIndexOutOfBoundsException;
    public T remove(int index) throws ListIndexOutOfBoundsException;
    public int search(KT key);
    public void clear();
}
