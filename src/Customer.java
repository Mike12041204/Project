import Structures.*;

public class Customer 
{
    private int size;
    private String name;
    private String movie;
    private boolean hasChild;
    
    private List<Integer> positions;

    public Customer(int groupSize, String name, String movie, boolean child)
    {
        this.size = groupSize;
        this.name = name;
        this.movie = movie;
        positions = new List<>();
        hasChild = child;
    }

    public Customer(int size, String name, Boolean child)
    {
        this.size = size;
        this.name = name;
        positions = new List<>();
        hasChild = child;
    }

    public int getSize()
    {
        return size;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasChild()
    {
        return hasChild;
    }

    public void addSeat(int index, int seat)
    {
        positions.add(index, seat);
    }
}
