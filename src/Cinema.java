public class Cinema {
    private int earnings;
    private double pricerPerTicket;

    private Line line1;
    private Line line2;
    private Line linep;
    private Theater theater1;
    private Theater theater2;
    
    // line order is 1 -> 2 -> P
    private int lineOrder;

    public Cinema(int t1_width, int t1_height, int t2_width, int t2_height, double pricePerTicket)
    {
        earnings = 0;
        lineOrder = 0;
        this.pricerPerTicket = pricePerTicket;

        line1 = new Line();
        line2 = new Line();
        linep = new Line();
        theater1 = new Theater(t1_width, t1_height, "Barbie");
        theater2 = new Theater(t2_width, t2_height, "Oppenheimer");
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
        }else{
            theater2.enterTheater(customer);
        }
    }

    public boolean hasCustomerName(String name)
    {
        return theater1.hasName(name) || theater2.hasName(name);
    }

    public int getEarnings()
    {
        return earnings;
    }

    public boolean linesEmpty()
    {
        return line1.empty() && line2.empty() && linep.empty();
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
