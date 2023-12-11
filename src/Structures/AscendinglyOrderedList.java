package Structures;
/**
 * The AscendinglyOrderedList class mainatains a list of elements that are comparable in an ascending order.
 * The class supports numerous operations on this list like adding a key whihc will be put into the correct
 * position in the ordering. There are also methods to cler the list, get the size, and remove an item, amongst
 * others.
 *
 * @author Michael Greenbaum
 * @verison 11.7.2023
 */
public class AscendinglyOrderedList<T extends KeyedItem<KT>, KT extends Comparable <? super KT>> implements AscendinglyOrderedListInterface<T, KT>
{
    private T[] items;
    private int numItems;

    /**
     * The constructor for the list initializes the number of items of 0, and allocates the array for 3 elements.
     */
    public AscendinglyOrderedList()
    {
        numItems = 0;
        items = (T[]) new KeyedItem[3];
    }

    /*
     * The isEmpty method indicates whether the list is empty or not.
     *
     * @return a boolean indicating whether the collection is empty
     */
    public boolean isEmpty()
    {
        return numItems == 0;
    }

    /**
     * The size method returns the size of the collection.
     *
     * @return the size of the collection
     */
    public int size()
    {
        return numItems;
    }

    /**
     * Add an item to the collection. Method will resize the collection if the collection is full when adding.
     * The method will also call the search method to find the proper place in the collection for the item to be
     * inserted that maintains the ordering.
     *
     * @param the item to be inserted into the collection
     */
    public void add(T item) throws ListIndexOutOfBoundsException
    {
        if(numItems == items.length) {
            resize();
        }

        if (numItems >= items.length)
        {
            throw new ListException("ListException on add");
        }  // end if

        int index = search(item.getKey());
        if(index < 0) {
            index = (index + 1) * (-1);
        } else {
            System.out.println("Item already in list!");
            return;
        }


        // make room for new element by shifting all items at
        // positions >= index toward the end of the
        // list (no shift if index == numItems+1)
        for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
        {
            items[pos+1] = items[pos];
        } // end for
        // insert new item
        items[index] = item;
        numItems++;
    }

    /**
     * The get method gets an item in the list at a certain index.
     *
     * @param the index of the item to get
     * @return the item gotten at the index
     * @throws an exception if the index is invalid for the collection
     */
    public T get(int index) throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    }

    /**
     * The remove method removes an item from the collection an returns the item that was removed, it takes an
     * index as the location of the item to be removed.
     *
     * @param the index of the item to be rmeoved
     * @return the item that was removed from the collection
     * @throws an esception if the index was invalid
     */
    public T remove(int index) throws ListIndexOutOfBoundsException
    {
        T result;
        if (index >= 0 && index < numItems)
        {
            result = items[index];
            for (int pos = index+1; pos < numItems; pos++) {
                items[pos-1] = items[pos];
            }
            items[numItems-1] = null;
            numItems--;
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }
        return result;
    }

    /**
     * The toString method returns a String representation of the collection which is every element in the collection
     * seperated by a space.
     *
     * @return the String representation of the collection
     */
    public String toString()
    {
        String string = "";

        for(int i = 0; i < numItems; i++) {
            string += items[i] + " ";
        }

        return string.trim();
    }

    /**
     * The resize method is a helper method which double the size of the underlying array of the list while
     * maintaining the elements within it.
     */
    private void resize()
    {
        T[] tmp = (T[]) new KeyedItem[items.length << 1];
        for(int i = 0; i < numItems; i++) {
            tmp[i] = items[i];
        }
        items = tmp;
    }

    /**
     * The search method finds the index of the provided key within the list. Beyond this the method will also return
     * an encoded position the key should be inserted into the collection if it is not already present. Any
     * encoded index will be negative and can be decoded by taking the key k and: (-1 * k) -1.
     *
     * @param the key to searched for in the list
     * @return the index of the key or the position it should be inserted into in the encoded manner
     */
    public int search(KT key)
    {
        if(numItems == 0) {
            return -1;
        }

        int low = 0;
        int high = numItems - 1;

        while(low < high) {
            int mid = (low + high) / 2;

            if(key.compareTo(((T)items[mid]).getKey()) > 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if(key.compareTo(((T)items[low]).getKey()) == 0) {
            return low;
        } else if(low == numItems - 1) {
            if(key.compareTo(((T)items[low]).getKey()) > 0) {
                return (numItems + 1) * -1;
            } else {
                return numItems * -1;
            }
        } else {
            return (low + 1) * -1;
        }
    }

    /**
     * Clear the list rmeoving all elements and returning the list the state it was in when it was first constructed.
     */
    public void clear()
    {
        numItems = 0;
        items = (T[]) new KeyedItem[3];
    }
}
