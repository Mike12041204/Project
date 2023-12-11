import Structures.*;

/**
 * The line class represents a line of customers waiting to enter a theater at the
 * cinema. The line class contains a queue of these people as well as methods to interact
 * with this structure.
 * 
 * @author Michael Greenbaum and Duwon Ham
 * @version 12/11/2023
 */
public class Line {
    private Queue<Customer> train;
    private int size; //We can also make size method in queue class which I think we should, but Im not sure if we can change structure class in this project

    /**
     * The constructor for the line class initializes the queue and sets the size to
     * 0.
     */
    public Line()
    {
        train = new Queue<>();
        size = 0;
    }

    /**
     * Removes a customer from the line and returns them.
     * 
     * @return the customer who left the line
     */
    public Customer leave()
    {
        size--;
        return train.dequeue();
    }

    /**
     * Adds a customer to the queue.
     * 
     * @param customer the customer who has been added to line
     */
    public void enter(Customer customer)
    {
        train.enqueue(customer);
        size++;
    }

    /**
     * Gets whether the line is empty or not.
     * 
     * @return whether the line is empty
     */
    public boolean empty()
    {
        return train.isEmpty();
    }

    /**
     * Gets the size of the line.
     * 
     * @return the number of customers in the line
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns a formatted string of the lines contents, including the customers
     * within it.
     * 
     * @return the string representing the line
     */
    public String toString()
    {
        return train.toString();
    }
}