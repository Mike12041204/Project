import Structures.*;

public class Customer extends KeyedItem<String>
{
    // key is name

    private int size;
    private String movie;
    private boolean hasChild;
    
    private List<Integer> positions;

    public Customer(int groupSize, String name, String movie, boolean hasChild)
    {
        super(name);

        this.size = groupSize;
        this.movie = movie;
        this.hasChild = hasChild;

        positions = new List<>();
    }

    public int getSize()
    {
        return size;
    }

    public String getMovie()
    {
        return movie;
    }

    public void setMovie(String movie)
    {
        this.movie = movie;
    }

    public boolean getHasChild()
    {
        return hasChild;
    }

    public void addSeat(int index, int seat)
    {
        positions.add(index, seat);
    }

    public int getSeat(int index)
    {
        return positions.get(index);
    }

    public String toString()
    {
        return "\tCustomer " + getKey() + " party of " + size + " for " + movie + " movie.\n";
    }
}
