import Structures.*;

public class Theatre {
    private int size;
    private int width;
    private int height;

    private List<String> seats;
    private List<Customer> locations;
    private Stack<Integer> remaining;

    public Theatre(int width, int height)
    {
        this.width = width;
        this.height = height;
        size = width * height;

        seats = new List<>();
        locations = new List<>();
        remaining = new Stack<>();

        // populate seats with empty
        initialize();
    }

    private void initialize()
    {
        for(int i = 0; i < size; i++)
        {
            remaining.push(i);
            seats.add(i, null);
        }
    }

    //returns a remaining seat
    public int getSeat()
    {
        return remaining.pop();
    }

    //check if there is an empty seat, returning boolean might help but for groups coming in, returning int is better
    public int remainingSeats()
    {
        //code to get remaining seats
        return -1; //place holder
    }

    public void binaryAddLocation(Customer customer)
    {
        //code to find the index
        locations.add(-1, customer); //replace -1 with the found index
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        //code for tostring method

        return sb.toString();
    }
}
