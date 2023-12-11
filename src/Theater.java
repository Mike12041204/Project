import java.lang.invoke.StringConcatFactory;

import Structures.*;

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

    private void initialize()
    {
        for(int i = 0; i < size; i++)
        {
            remaining.push(i);
            seats.add(i, null);
        }
    }

    public boolean hasName(String name)
    {
        return locations.search(name) > -1;
    }

    public void enterTheater(Customer customer)
    {
        for(int i = 0; i < customer.getSize(); i++){
            int seat = remaining.pop();
            seats.add(seat, customer.getKey());
            customer.addSeat(i, seat);
            remainingSeats--;
        }
        locations.add(customer);
    }

    //check if there is an empty seat, returning boolean might help but for groups coming in, returning int is better
    public int remainingSeats()
    {
        return remainingSeats;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        //code for tostring method

        return sb.toString();
    }

    public String getName()
    {
        return name;
    }
}
