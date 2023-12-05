public class Cinema {
    private int earnings;

    private Line line1;
    private Line line2;
    private Line linep;
    private Theatre theatre1;
    private Theatre theatre2;
    
    private int lineOrder;

    public Cinema(int t1_width, int t1_height, int t2_width, int t2_height)
    {
        earnings = 0;
        lineOrder = 0;

        line1 = new Line();
        line2 = new Line();
        linep = new Line();
        theatre1 = new Theatre(t1_width, t1_height);
        theatre2 = new Theatre(t2_width, t2_height);
    }

    public void enterLine(Customer customer)
    {
        int s1 = line1.size();
        int s2 = line2.size();
        int s3 = linep.size();
        if(customer.hasChild() && s3<<1 < s1 && s3<<1 < s2)
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

    //We have many options for how to handle the first leave, I didn't add it
    public void buyTicket(int theater) //we can make this a string
    {
        Customer customer = null;
        while(customer == null) //have not tested it yet, also we have to make sure leave() method is not called when there are 0 customers in the lines
        {
            switch (lineOrder) {
                case 1:
                    if(!line1.empty())
                    {
                        customer = line1.leave();
                        break;
                    }
                case 2:
                    if(!line2.empty())
                    {
                        customer = line2.leave();
                        break;
                    }
                case 3:
                    if(!linep.empty())
                    {
                        customer = linep.leave();
                        break;
                    }
                default:
                    lineOrder = 0;
            }
            lineOrder = (lineOrder % 3) + 1;
        }
        switch(theater)
        {
            case 0:
                enterTheater(customer, theatre1);
                break;
            case 1:
                enterTheater(customer, theatre2);
                break;
        }
    }

    private void enterTheater(Customer customer, Theatre theater)
    {
        int size = customer.getSize();
        if(theater.remainingSeats() < size)
        {
            //code for when there is no remaining seats
        }
        else
        {
            for(int i = 0; i < size; i++)
            {
                customer.addSeat(i, theater.getSeat());
                theater.binaryAddLocation(customer);
                earnings++;
            }
        }
    }

    public int getEarnings()
    {
        return earnings;
    }
    //random change

    public void showTheater(int theaterNumber)
    {
        switch (theaterNumber) {
            case 1:
                System.out.println(theatre1);
                break;
            case 2:
                System.out.println(theatre2);
                break;
            default:
                break;
        }
    }
}
