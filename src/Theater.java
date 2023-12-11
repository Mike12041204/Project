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
            seats.remove(seat);
            seats.add(seat, customer.getKey());
            customer.addSeat(i, seat);
            remainingSeats--;
        }
        locations.add(customer);
    }

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

    //check if there is an empty seat, returning boolean might help but for groups coming in, returning int is better
    public int remainingSeats()
    {
        return remainingSeats;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < seats.size(); i++){
            sb.append("Row " + (i/height+1) + " seat " + (i%height+1) + ((seats.get(i) == null) ? " is free." : " used by " + seats.get(i) + "'s party.") + "\n");
        }

        return sb.toString();
    }

    public String getName()
    {
        return name;
    }

    public boolean isEmpty()
    {
        return remainingSeats == size;
    }
}
