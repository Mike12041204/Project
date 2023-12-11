import Structures.*;

/**
 * The customer class represents a single customer or a group of customers keyed by a
 * name. Beyond this, the class has other properties and a list to hold the position
 * of the groups memebers in a theater.
 * 
 * @author Michael Greenbaum and Duwon Ham
 * @version 12/11/2023
 */
public class Customer extends KeyedItem<String>
{
    // key is name

    private int size;
    private String movie;
    private boolean hasChild;
    
    private List<Integer> positions;

    /**
     * The constructor for the customer class initializes the variables of the object
     * by using parameters. It also initializes the empty positions collection.
     * 
     * @param groupSize the size of the group
     * @param name the name and key of the group
     * @param movie the movie the group is seeing
     * @param hasChild whether the group contains a child
     */
    public Customer(int groupSize, String name, String movie, boolean hasChild)
    {
        super(name);

        this.size = groupSize;
        this.movie = movie;
        this.hasChild = hasChild;

        positions = new List<>();
    }

    /**
     * Gets the size of customer, the number of people within the group.
     * 
     * @return the size of the group
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Get the movie the group wants to watch.
     * 
     * @return the intended movie
     */
    public String getMovie()
    {
        return movie;
    }

    /**
     * Sets the movie the group wants to watch.
     * 
     * @param movie that the group will consider
     */
    public void setMovie(String movie)
    {
        this.movie = movie;
    }

    /**
     * Gets whether the customer group has a child.
     * 
     * @return whether there is a child
     */
    public boolean getHasChild()
    {
        return hasChild;
    }

    /**
     * Adds the location of a seat a customer group member is sitting at.
     * 
     * @param index position in the array the seat will be added at
     * @param seat the number of the seat the member is sitting at
     */
    public void addSeat(int index, int seat)
    {
        positions.add(index, seat);
    }

    /**
     * Gets a seat a member of the group is sitting at.
     * 
     * @param index the postion in the array to get the seat from
     * @return the seat a member is sitting at
     */
    public int getSeat(int index)
    {
        return positions.get(index);
    }

    /**
     * Returns a formatted string displaying the customers group and some relevant
     * information about them.
     */
    public String toString()
    {
        return "\tCustomer " + getKey() + " party of " + size + " for " + movie + " movie.\n";
    }
}