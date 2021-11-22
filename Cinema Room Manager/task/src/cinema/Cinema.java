package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsPerRow = scanner.nextInt();

        Room room1 = new Room(rows, seatsPerRow);



        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int menuType = scanner.nextInt();
            if(menuType == 0) {
                break;
            }

            System.out.println();
            performActionInput(room1, menuType);

        }
        while(true);


        /*



        */

    }

    public static void performActionInput(Room room, int menuType)
    {
        switch (menuType)
        {
            case 1:
                room.printStatusSummary();
                break;
            case 2:
                buyMenuTicket(room);
                break;
            case 3:
                room.getStatistics();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
    public static void buyMenuTicket(Room room)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number: ");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seatNumber = scanner.nextInt();


        if(!room.isSeatExisting(rowNumber, seatNumber))
        {
            System.out.println("\nwrong input!");
            buyMenuTicket(room);
            return;
        }

        if(!room.bookSeat(rowNumber, seatNumber)) {
            buyMenuTicket(room);
            return;
        }

        System.out.println();
        System.out.println("Ticket price: $" + room.getTicketPrice(rowNumber));
    }

}