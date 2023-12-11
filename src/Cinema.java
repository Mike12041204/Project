import Structures.AscendinglyOrderedList;

/**
 * The Cinema class is the encapsulating class for the movie theater representation.
 * It is a class that contains all the subelements of the cinema like the theaters
 * and the waiting lists. This class also provides methods for the driver to interact
 * with the theater.
 * 
 * @author Michael Greenbaum and Duwon Ham
 * @version 12/11/2023
 */
public class Cinema {
    private double pricerPerTicket;
    private int ticketCount1;
    private int ticketCount2;

    private Line line1;
    private Line line2;
    private Line linep;
    private Theater theater1;
    private Theater theater2;
    
    // line order is 1 -> 2 -> P
    private int lineOrder;

    // sorted list of all customers in cinema used to determine duplicate keys
    private AscendinglyOrderedList<Customer, String> existingCustomers;

    /**
     * Constrcutor for the cinema class initializes all the collection and sets the
     * dimensions of the two theaters using parameters.
     * 
     * @param t1_width first theaters width
     * @param t1_height first theaters height
     * @param t2_width second theaters width
     * @param t2_height second theaters height
     * @param pricePerTicket cost per ticket
     */
    public Cinema(int t1_width, int t1_height, int t2_width, int t2_height, double pricePerTicket)
    {
        ticketCount1 = 0;
        ticketCount1 = 0;
        this.pricerPerTicket = pricePerTicket;

        line1 = new Line();
        line2 = new Line();
        linep = new Line();
        theater1 = new Theater(t1_width, t1_height, "Barbie");
        theater2 = new Theater(t2_width, t2_height, "Oppenheimer");

        lineOrder = 0;

        existingCustomers = new AscendinglyOrderedList<>();
    }

    /**
     * Sets the starting list from the three options.
     * 
     * @param order value 0 - 2 which represenents which list to start getting 
     *              customers from
     */
    public void setLineOrder(int order)
    {
        lineOrder = order;
    }

    // returns 0 if in p, 1 if in l1, 2 if in l2
    /**
     * Enters a customer into one of the lines, determines which of the lines to 
     * insert the customer into based on whether the customers group has a child and
     * the number of people in each list.
     * 
     * @param customer the customer to be inserted into a list
     * @return the number of the line the customer was inserted into
     */
    public int enterLine(Customer customer)
    {
        int result;

        int s1 = line1.size();
        int s2 = line2.size();
        int s3 = linep.size();
        // UNSURE - ensure this condition is correct
        if(customer.getHasChild() && (s1 > s3 / 2 || s2 > s3 / 2)) 
        {
            linep.enter(customer);
            result = 0;
        }
        else
        {
            if(s2 < s1)
            {
                line2.enter(customer);
                result = 2;
            }
            else
            {
                line1.enter(customer);
                result = 1;
            }
        }

        existingCustomers.add(customer);

        return result;
    }

    /**
     * Gets the next customer in from one of the lists, gets from each list in a
     * cyclical order.
     * 
     * @return the next customer in the order
     */
    public Customer getNextCustomer()
    {
        Customer customer = null;

        while(customer == null)
        {
            switch (lineOrder) {
            case 0:
                if(!line1.empty())
                {
                    customer = line1.leave();
                    break;
                }
            case 1:
                if(!line2.empty())
                {
                    customer = line2.leave();
                    break;
                }
            case 2:
                if(!linep.empty())
                {
                    customer = linep.leave();
                    break;
                }
            default:
                lineOrder = 0;
            }
        }
        lineOrder = (lineOrder + 1) % 3;

        existingCustomers.remove(existingCustomers.search(customer.getKey()));

        return customer;
    }

    /**
     * Seats a customer into a theater and increases count of the tickets sold.
     * 
     * @param customer the customer who is being inserted into a theater
     */
    public void seatCustomer(Customer customer)
    {
        if(customer.getMovie().equals("Barbie")){
            theater1.enterTheater(customer);
            ticketCount1 += customer.getSize();
        }else{
            theater2.enterTheater(customer);
            ticketCount2 += customer.getSize();
        }

        existingCustomers.add(customer);
    }

    /**
     * Returns whether the cinema already contains a customer of this name.
     * 
     * @param name of the customer being searched for
     * @return whether the name was found or not
     */
    public boolean hasCustomerName(String name)
    {
        return existingCustomers.search(name) > -1;
    }

    /**
     * Removes a customer from a theater.
     * 
     * @param customerName the name of the customer to be removed
     */
    public void exitCustomer(String customerName)
    {
        if(theater1.hasName(customerName)){
            theater1.exitTheater(customerName);
        }else{
            theater2.exitTheater(customerName);
        }

        existingCustomers.remove(existingCustomers.search(customerName));
    }

    /**
     * Returns a formatted string of the customers contained within all lines.
     * 
     * @return the string of the lien information
     */
    public String lineDetails()
    {
        StringBuilder sb = new StringBuilder();
        
        if(line1.size() == 0){
            sb.append("\tNo customers in the first line!\n");
        }else{
            if(line1.size() == 1){
                sb.append("\tThe following customer is in the first line:\n");
            }else{
                sb.append("\tThe following " + line1.size() + " customers are in the first line\n");
            }
            sb.append(line1.toString());
        }
        if(line2.size() == 0){
            sb.append("\tNo customers in the second line!\n");
        }else{
            if(line2.size() == 1){
                sb.append("\tThe following customer is in the second line:\n");
            }else{
                sb.append("\tThe following " + line2.size() + " customers are in the second line\n");
            }
            sb.append(line2.toString());
        }
        if(linep.size() == 0){
            sb.append("\tNo customers in the express line!\n");
        }else{
            if(linep.size() == 1){
                sb.append("\tThe following customer is in the express line:\n");
            }else{
                sb.append("\tThe following " + linep.size() + " customers are in the express line\n");
            }
            sb.append(linep.toString());
        }

        return sb.toString();
    }

    /**
     * Returns a formatted string with the information about customers contained
     * within a certain theater, and the seating locations.
     * 
     * @param theater the theater which seats are to be inspected
     * @return the string of the theaters seats and respective customers
     */
    public String theaterDetails(int theater)
    {
        StringBuilder sb = new StringBuilder();

        if(theater == 0){
            sb.append("Here's the seating chart for the Barbie Movie Theater:\n");
            sb.append(theater1.toString());
        }else{
            sb.append("Here's the seating chart for the Oppenheimer Movie Theater:\n");
            sb.append(theater2.toString());
        }

        return sb.toString();
    }

    /**
     * Returns a formatted string of the sales and income of the theater.
     * 
     * @return the string of sales information
     */
    public String earningsDetails()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(ticketCount1 + " tickets have been sold for the Barbie Movie.\n");
        sb.append(ticketCount2 + " tickets have been sold for the Oppenheimer Movie.\n");
        sb.append("Total earnings: " + getEarnings() + "\n");

        return sb.toString();
    }

    /**
     * Gets the total earnings of the cinema.
     * 
     * @return the overall earnings
     */
    public double getEarnings()
    {
        return (ticketCount1 + ticketCount2) * pricerPerTicket;
    }

    /**
     * Determines whether all of the cinemas lines are empty.
     * 
     * @return whether all lines are empty
     */
    public boolean linesEmpty()
    {
        return line1.empty() && line2.empty() && linep.empty();
    }

    /**
     * Gets whether all cinema theaters are empty.
     * 
     * @return whether the theaters are empty
     */
    public boolean theatersEmpty()
    {
        return theater1.isEmpty() && theater2.isEmpty();
    }

    /**
     * Gets the first theater.
     * 
     * @return the first theater
     */
    public Theater getTheater1()
    {
        return theater1;
    }

    /**
     * Gets the second theater.
     * 
     * @return the second theater
     */
    public Theater getTheater2()
    {
        return theater2;
    }
}