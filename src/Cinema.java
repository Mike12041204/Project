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
    }

    public void setLineOrder(int order)
    {
        lineOrder = order;
    }

    public void enterLine(Customer customer)
    {
        int s1 = line1.size();
        int s2 = line2.size();
        int s3 = linep.size();
        // UNSURE - ensure this condition is correct
        if(customer.getHasChild() && s3 >= s1<<1 && s3 >= s2<<1)
        {
            linep.enter(customer);
        }
        else
        {
            if(s2 < s1)
            {
                line2.enter(customer);
            }
            else
            {
                line1.enter(customer);
            }
        }
    }

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
            lineOrder = (lineOrder + 1) % 3;
        }

        return customer;
    }

    public void seatCustomer(Customer customer)
    {
        if(customer.getMovie().equals("Barbie")){
            theater1.enterTheater(customer);
            ticketCount1 += customer.getSize();
        }else{
            theater2.enterTheater(customer);
            ticketCount2 += customer.getSize();
        }
    }

    // returns 0 if name not in any theater, 1 if in theater 1, 2 if in theater 2
    public int hasCustomerName(String name)
    {
        if(theater1.hasName(name)){
            return 1;
        }else if(theater2.hasName(name)){
            return 2;
        }
        return 0;
    }

    public void exitCustomer(String customerName)
    {
        if(theater1.hasName(customerName)){
            theater1.exitTheater(customerName);
        }else{
            theater2.exitTheater(customerName);
        }
    }

    public String lineDetails()
    {
        StringBuilder sb = new StringBuilder();
        
        if(line1.size() == 0){
            sb.append("No customers in the first line!\n");
        }else{
            if(line1.size() == 1){
                sb.append("The following customer is in the first line:\n");
            }else{
                sb.append("The following " + line1.size() + " customers are in the first line\n");
            }
            sb.append(line1.toString());
        }
        if(line2.size() == 0){
            sb.append("No customers in the first line!\n");
        }else{
            if(line2.size() == 1){
                sb.append("The following customer is in the first line:\n");
            }else{
                sb.append("The following " + line2.size() + " customers are in the first line\n");
            }
            sb.append(line2.toString());
        }
        if(linep.size() == 0){
            sb.append("No customers in the first line!\n");
        }else{
            if(linep.size() == 1){
                sb.append("The following customer is in the first line:\n");
            }else{
                sb.append("The following " + linep.size() + " customers are in the first line\n");
            }
            sb.append(linep.toString());
        }

        return sb.toString();
    }

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

    public String earningsDetails()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(ticketCount1 + " tickets have been sold for the Barbie Movie.\n");
        sb.append(ticketCount2 + " tickets have been sold for the Oppenheimer Movie.\n");
        sb.append("Total earnings: " + getEarnings() + "\n");

        return sb.toString();
    }

    public double getEarnings()
    {
        return (ticketCount1 + ticketCount2) * pricerPerTicket;
    }

    public boolean linesEmpty()
    {
        return line1.empty() && line2.empty() && linep.empty();
    }

    public boolean theatersEmpty()
    {
        return theater1.isEmpty() && theater2.isEmpty();
    }

    public Theater getTheater1()
    {
        return theater1;
    }

    public Theater getTheater2()
    {
        return theater2;
    }
}
