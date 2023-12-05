import Structures.*;

public class Line {
    private Queue<Customer> train;
    private int size; //We can also make size method in queue class which I think we should, but Im not sure if we can change structure class in this project

    public Line()
    {
        train = new Queue<>();
        size = 0;
    }

    public Customer leave()
    {
        size--;
        return train.dequeue();
    }

    public void enter(Customer customer)
    {
        train.enqueue(customer);
        size++;
    }

    public boolean empty()
    {
        return train.isEmpty();
    }

    public int size()
    {
        return size;
    }
}
