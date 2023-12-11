// NOTES
// - do we need Cinema class?
// - do we need Line class?
// - should we have CustomerPositions class?

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver 
{
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Welcome to the Wonderful Movie Theatre program!\n" + 
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
                System.out.println("Exiting program...");
                return;
            case 1:
                customerEnter(cinema);
                break;
            case 2:
                customerBuy(cinema);
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

    private static void customerEnter(Cinema cinema) throws Exception
    {
        System.out.print(">>Enter customer name: ");
        String name = stdin.readLine().trim();
        System.out.println(name);
        System.out.print(">>Enter party size: ");
        int size = Integer.parseInt(stdin.readLine().trim());
        System.out.println(size);
        System.out.print(">>Enter movie name: ");
        int movie = Integer.parseInt(stdin.readLine().trim());
        System.out.println(movie);
        System.out.print(">>Is a child 11 or younger in this party(Y/N)? ");
        boolean child = Boolean.parseBoolean(stdin.readLine().trim());
        System.out.println((child) ? "Y" : "N");
    }

    private static void customerBuy(Cinema cinema) throws Exception
    {

    }

    private static void customerLeave(Cinema cinema) throws Exception
    {

    }

    private static void displayWaiting(Cinema cinema) throws Exception
    {

    }

    private static void displayBarbie(Cinema cinema) throws Exception
    {

    }

    private static void displayOppenheimer(Cinema cinema) throws Exception
    {

    }

    private static void displayTickets(Cinema cinema) throws Exception
    {

    }
}
