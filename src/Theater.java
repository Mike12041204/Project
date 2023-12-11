import Structures.*;

/**
 * The theater class represents one of the screens and seating used to view a movie
 * and contain its audience. The theater has a simple list of names to indicate who
 * is sitting where in the theater. Beyond this it has a sorted list of customers
 * which can be used to retrieve customers leaving the theater adn get their locations
 * quickly. The theater also has a stack containing the index of all empty seats.
 * 
 * @author Michael Greenbaum and Duwon Ham
 * @version 12/11/2023
 */
public class Theater 
{
    private String name;
    private int size;
    private int width;
    private int height;
    private int remainingSeats;

    private List<String> seats;
    private AscendinglyOrderedList<Customer, String> locations;
    private Stack<Integer> remaining;

    /**
     * The constructor for the theater class initializes the size of the theater using
     * parameters. It also allocates all of the data structures used by the theater.
     * The data structures are also initialized to contain default values.
     * 
     * @param width the width of the theater
     * @param height the height of the theater
     * @param name the name of the theater
     */
    public Theater(int width, int height, String name)
    {
        this.width = width;
        this.height = height;
        this.name  = name;
        size = width * height;
        remainingSeats = size;

        seats = new List<>();
        locations = new AscendinglyOrderedList<>();
        remaining = new Stack<>();

        // populate seats with empty
        initialize();
    }

    /**
     * Helper method used by the constructor to initialize the data structures of
     * the class to contain default values.
     */
    private void initialize()
    {
        for(int i = 0; i < size; i++)
        {
            remaining.push(size - i - 1);
            seats.add(i, null);
        }
    }

    /**
     * Gets whether the theater contains a certain customer name.
     * 
     * @param name the name to be searched for
     * @return whether the name was found or not
     */
    public boolean hasName(String name)
    {
        return locations.search(name) > -1;
    }

    /**
     * Adds a customer to the theater and updates the data structures to reprsenent
     * this. Also initializes the positions list in the customer to keep track of its
     * groups position within the theater.
     * 
     * @param customer the customer who is entering the theater
     */
    public void enterTheater(Customer customer)
    {
        for(int i = 0; i < customer.getSize(); i++){
            int seat = remaining.pop();
            seats.remove(seat);
            seats.add(seat, customer.getKey());
            customer.addSeat(i, seat);
            remainingSeats--;
        }
        locations.add(customer);
    }

    /**
     * Removes a customer from the theater and updates the data structures to represent
     * this.
     * 
     * @param customerName the name of the customer who is leaving the theater
     */
    public void exitTheater(String customerName)
    {
        int location = locations.search(customerName);
        Customer customer = locations.get(location);

        for(int i = 0; i < customer.getSize(); i++){
            int seat = customer.getSeat(i);
            remaining.push(seat);
            seats.remove(seat);
            seats.add(seat, null);
            remainingSeats++;
        }
    }

    /**
     * Gets the number of empty seats in the theater.
     * 
     * @return the number of available seats
     */
    public int remainingSeats()
    {
        return remainingSeats;
    }

    /**
     * Returns a formatted string indicating the seating arrangement of the theater.
     * 
     * @return the string of the seating positions
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < seats.size(); i++){
            sb.append("Row " + (i/height+1) + " seat " + (i%height+1) + ((seats.get(i) == null) ? " is free." : " used by " + seats.get(i) + "'s party.") + "\n");
        }

        return sb.toString();
    }

    /**
     * Gets the name of the movie being played at the theater.
     * 
     * @return the name of the movie
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets whether the theater is empty or not.
     * 
     * @return whether the theater is empty
     */
    public boolean isEmpty()
    {
        return remainingSeats == size;
    }
}