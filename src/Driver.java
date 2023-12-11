import java.io.*;

/**
 * The driver class provides the menu interface to allow the user to test and apply
 * the movie theater simulation. The driver allows the user to modify and read the
 * movie theater.
 * 
 * @author Michael Greenbaum and Duwon ham
 * @version 12/11/2023
 */
public class Driver 
{
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * The main method prints the menu and repeadly prompts the user for input until
     * they quit.
     * 
     * @param args command line input, not used
     * @throws Exception thrown from reading input
     */
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Welcome to the Wonderful Movie Theater program!\n" + 
                             "\tTonight's features are:\n" + 
                             "\t\t\"Barbie\" and \"Oppenheimer\"\n");

        // barbie is theater1 and oppenheimer is theater2
        Cinema cinema = null;
        int rows1 = 0;
        int seats1 = 0;
        int rows2 = 0;
        int seats2 = 0;
        double pricePer = 0;

        System.out.print("Please specify the size of the Movie Theaters\n" +
                         "\t\tEnter information abot the Oppenheimer Movie Theater:\n" +
                         "\t\t\t>>Enter number of rows: ");
        rows1 = Integer.parseInt(stdin.readLine().trim());
        System.out.println(rows1);
        System.out.print("\t\t\t>>Enter number of seats in a row: ");
        seats1 = Integer.parseInt(stdin.readLine().trim());
        System.out.println(seats1);
        System.out.print("\t\tEnter information abot the Barbie Movie Theater:\n" +
                         "\t\t\t>>Enter number of rows: ");
        rows2 = Integer.parseInt(stdin.readLine().trim());
        System.out.println(rows2);
        System.out.print("\t\t\t>>Enter number of seats in a row: ");
        seats2 = Integer.parseInt(stdin.readLine().trim());
        System.out.println(seats2);
        System.out.print("\t\t\t>>Enter the price of a ticket: ");
        pricePer = Double.parseDouble(stdin.readLine().trim());
        System.out.println(pricePer);

        cinema = new Cinema(rows2, seats2, rows1, seats1, pricePer);
        boolean firstBuy = true;

        System.out.println("Select an operation from the following menu\n" +
                           "\t\t\t0. End the program.\n" +
                           "\t\t\t1. Customer(s) enter(s) Movie Theater.\n" +
                           "\t\t\t2. Customer buys ticket(s).\n" +
                           "\t\t\t3. Customer(s) leave(s) the theater.\n" +
                           "\t\t\t4. Display info about customers waiting for tickets.\n" +
                           "\t\t\t5. Display seating chart for Babrie Movie Theater\n" +
                           "\t\t\t6. Display the seating chart for the Oppenheimer Movie Theater.\n" +
                           "\t\t\t7. Display number of tickets sold and total earnings.\n");

        while(true){
            System.out.print(">>Make your menu selection now: ");
            int selection = Integer.parseInt(stdin.readLine().trim());
            System.out.println(selection);

            switch(selection){
            case 0:
                System.out.println("The Wonderful Movie Theater who earned $" + cinema.getEarnings() + " kicks out remaining customer and closes...\nGood Bye!");
                return;
            case 1:
                customerEnter(cinema);
                break;
            case 2:
                customerBuy(cinema, firstBuy);
                if(firstBuy){
                    firstBuy = false;
                }
                break;
            case 3:
                customerLeave(cinema);
                break;
            case 4:
                displayWaiting(cinema);
                break;
            case 5:
                displayBarbie(cinema);
                break;
            case 6:
                displayOppenheimer(cinema);
                break;
            case 7:
                displayTickets(cinema);
            }

            System.out.println();
        }
    }

    /**
     * Allows for a new customer to enter the cinema. Gathers information for the
     * customer then inserts them into a line at the cinema.
     * 
     * @param cinema the cinema for the customers to enter
     * @throws Exception thrown when reading input
     */
    private static void customerEnter(Cinema cinema) throws Exception
    {
        System.out.print(">>Enter customer name: ");
        String name = stdin.readLine().trim();
        System.out.println(name);
        while(cinema.hasCustomerName(name)){
            System.out.print("Customer " + name + " is already in the theater!\n" +
                               "Please specify a different name.\n" +
                               ">>Enter customer name: ");
            name = stdin.readLine().trim();
            System.out.println(name);
        }
        System.out.print(">>Enter party size: ");
        int size = Integer.parseInt(stdin.readLine().trim());
        System.out.println(size);
        System.out.print(">>Enter movie name: ");
        String movie = stdin.readLine().trim();
        System.out.println(movie);
        System.out.print(">>Is a child 11 or younger in this party(Y/N)? ");
        boolean child = (stdin.readLine().trim().equals("Y")) ? true : false;
        System.out.println((child) ? "Y" : "N");

        Customer customer = new Customer(size, name, movie, child);

        int lineEntered = cinema.enterLine(customer);

        System.out.println("Customer " + name + " is in " + ((lineEntered == 0) ? "express" : ((lineEntered == 1) ? "first" : "second")) + " ticket line.");
    }

    /**
     * Allows the customers at the cinema to purchase a ticket. If their desired 
     * theater is full they can choose to get to the other if open.
     * 
     * @param cinema the cinema the customers are entering
     * @param firstBuy whether this is the first tickets being purchased
     * @throws Exception thrown when reading input
     */
    private static void customerBuy(Cinema cinema, boolean firstBuy) throws Exception
    {
        if(firstBuy){
            System.out.print("Which line would you like to serve customers first? (Express/Reg1/Reg2): ");
            String first = stdin.readLine().trim();
            System.out.println(first);

            if(first.equals("Express")){
                cinema.setLineOrder(2);
            }else if(first.equals("Reg1")){
                cinema.setLineOrder(0);
            }else{
                cinema.setLineOrder(1);
            }
        }

        // case no customers
        if(cinema.linesEmpty()){
            System.out.println("There are no customers waiting in any line.");
            return;
        }

        // get customer
        Customer customer = cinema.getNextCustomer();

        System.out.println("Serving customer " + customer.getKey());

        // case full theaters
        if(customer.getSize() > cinema.getTheater1().remainingSeats() && customer.getSize() > cinema.getTheater2().remainingSeats())
        {
            System.out.println("Sorry. Both movies are sold out. Good bye!");
            System.out.println("Customer " + customer.getKey() + " has left the Movie Theater.");
            return;
        }

        // case desired theater full
        if((customer.getMovie().equals("Barbie") && customer.getSize() > cinema.getTheater1().remainingSeats()) || (customer.getMovie().equals("Oppenheimer") && customer.getSize() > cinema.getTheater2().remainingSeats())){
            System.out.print("Sorry. This movie is sold out.\n" +
                               "Would you like to see the other movie(Y/N)? ");
            boolean switchMovie = (stdin.readLine().trim().equals("Y")) ? true : false;
            System.out.println((switchMovie) ? "Y" : "N");

            // switch desired movie to open movie
            if(customer.getMovie().equals("Barbie") && switchMovie){
                customer.setMovie("Oppenheimer");
            }else if(customer.getMovie().equals("Oppenheimer") && switchMovie){
                customer.setMovie("Barbie");
            }else{
                System.out.println("Customer " + customer.getKey() + " has left the Movie Theater.");
                return;
            }
        }

        // seat customer at selected theater
        cinema.seatCustomer(customer);

        System.out.println(customer.getKey() + ", party of " + customer.getSize() + " has been seated in the " + customer.getMovie() + " Movie Theater.");
    }

    /**
     * Allows a customer to leave the input remvoignt their information from the
     * repective theater.
     * 
     * @param cinema the cinema the customers are leaving
     * @throws Exception thrown when reading input
     */
    private static void customerLeave(Cinema cinema) throws Exception
    {
        // case no customers
        if(cinema.theatersEmpty()){
            System.out.println("No customers are in the movie theater at this time.");
            return;
        }

        System.out.print(">>Enter customer name to leave Movie Theater: ");
        String name = stdin.readLine().trim();
        System.out.println(name);

        // case customer doesn't exist
        if(!cinema.hasCustomerName(name)){
            System.out.println("This customer is not in Movie Theater!");
            return;
        }

        // customer exists remove them
        cinema.exitCustomer(name);

        System.out.println("Customer " + name + " has left the Movie Theater.");
    }

    /**
     * Displays the customers waiting in line for a theater at the cinema.
     * 
     * @param cinema the cinema the customers are waiting at
     * @throws Exception thrown when reading input
     */
    private static void displayWaiting(Cinema cinema) throws Exception
    {
        System.out.print(cinema.lineDetails());
    }

    /**
     * Display information about the Barbie theater and the customers within it.
     * 
     * @param cinema the cinema with the Barbie theater
     * @throws Exception thrown when reading input
     */
    private static void displayBarbie(Cinema cinema) throws Exception
    {
        System.out.print(cinema.theaterDetails(0));
    }

    /**
     * Display information about the Oppenheimer theater and the customers within it.
     * 
     * @param cinema the cinema with the Oppenheimer theater
     * @throws Exception thrown when reading input
     */
    private static void displayOppenheimer(Cinema cinema) throws Exception
    {
        System.out.print(cinema.theaterDetails(1));
    }

    /**
     * Display information about the number of tickets sold for each movie and the 
     * total money made.
     * 
     * @param cinema the cinema where the tickets were sold at
     * @throws Exception thrown when reading input
     */
    private static void displayTickets(Cinema cinema) throws Exception
    {
        System.out.print(cinema.earningsDetails());
    }
}