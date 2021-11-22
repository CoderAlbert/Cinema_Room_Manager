package cinema;

import java.util.Arrays;

public class Room {
    private char[][] room;
    private final int seats;
    private final int rows;
    private final int seatsPerRow;
    private final boolean isSmallRoom;
    private int soldTickets;
    private int currentIncome;

    public Room(int rows, int seatsPerRow)
    {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = rows * seatsPerRow;
        this.isSmallRoom = seats <= 60;
        this.room = new char[rows][seatsPerRow];
        this.initialize();
        currentIncome = 0;
    }

    private void initialize()
    {
        for(int rows = 0; rows < this.rows; rows++)
        {
            Arrays.fill(room[rows], 'S');
        }
    }

    public boolean bookSeat(int row, int column)
    {
        if(room[row - 1][column - 1] == 'B')
        {
            System.out.println("\nThat ticket has already been purchased!\n");
            return false;
        }
        currentIncome += getTicketPrice(row);
        room[row - 1][column - 1] = 'B';
        soldTickets++;

        return true;
    }

    public boolean isSeatExisting(int row, int column)
    {
        return this.room.length >= row && this.room[0].length >= column;
    }

    public void getStatistics()
    {
        float percentage = soldTickets == 0 ? 0 : (float) this.soldTickets / this.seats * 100;
        System.out.println("Number of purchased tickets: " + this.soldTickets);
        System.out.format("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + this.getProfit());
    }

    public int getProfit()
    {
        if(this.isSmallRoom)
        {
            return 10 * this.seats;
        }

        int firstHalfRows = this.rows / 2;
        int secondHalfRow = this.rows - firstHalfRows;

        return firstHalfRows * this.seatsPerRow * 10 + secondHalfRow * this.seatsPerRow * 8;
    }

    public int getTicketPrice(int row)
    {
        if(this.isSmallRoom)
        {
            return 10;
        }

        int firstHalfRows = this.rows / 2;

        return row <= firstHalfRows ? 10 : 8;
    }


    public void printStatusSummary()
    {
        int rowCounter = 1;
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 1; i <= this.seatsPerRow; i++)
        {
            if(i == this.seatsPerRow)
            {
                System.out.println(i);
                break;
            }

            System.out.print(i + " ");
        }

        for(int row = 1; row <= this.rows; row++)
        {
            boolean newRow = true;

            for(int column = 1; column <= this.seatsPerRow; column++)
            {
                if(newRow)
                {
                    System.out.print(rowCounter + " ");
                    rowCounter++;
                    newRow = false;
                }
                System.out.print(room[row - 1][column -1] + " ");
            }

            System.out.println();
        }
    }

}
