/*
 * Cancer.java
 */
package cancer;

// Import Scanner to make scanning for user input possible.
import java.util.Scanner;

/**
 *
 * @author cogid4775
 */
public class Cancer {

    // 2D array to represent the grid of cells
    public static String grid[][];
    
    // ints to hold the current row number and column number. Used in some calculations.
    public static int row;
    public static int col;
    
    // ints to hold the row size and column size.
    // Useful for calculations that makes changing the rows/columns very easy,
    // just in case they ever need to be changed.
    public static int rowSize;
    public static int colSize;
    
    // int to count how many cancer blobs are found
    public static int blobsFound = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        // Create a Scanner to scan the rows and columns the user wants
        Scanner scanInt = new Scanner(System.in);
        
        // Ask the user how many rows they want and hold their answer
        // in the public static int rowSize.
        System.out.println("Enter number of rows:");
        rowSize = scanInt.nextInt();
        
        // Ask the user how many columns they want and hold their answer
        // in the public static int colSize.
        System.out.println("Enter number of columns:");
        colSize = scanInt.nextInt();
        */
        
        // Create a 2D array (grid) with 15 rows and 15 columns.
        // To allow the user to customize the amount of rows and columns,
        // comment out the two lines below and uncomment the above code.
        rowSize = 15;
        colSize = 15;
        grid = new String[rowSize][colSize];

        // Before adding minus (-) signs, fill the array with plus (+) signs.
        for (row = 0; row < rowSize; row++) {
            for (col = 0; col < colSize; col++) {
                grid[row][col] = "+";
            }
        }
        
        // Present the user with a qustion:
        // Input cells manually or
        // Generate them randomly.
        System.out.println("Do you want to input cells manually or generate random cells automatically?");
        System.out.println("1 - Input cells manually");
        System.out.println("2 - Generate random cells automatically");
        
        // Create a new Scanner to scan the user's answer to the question.
        // Answer will be stored as a String to fix errors caused by inputting
        // a String when Java is looking for an int.
        Scanner scanString = new Scanner(System.in);
        
        // String to store the user's answer to the question.
        String ans = scanString.nextLine();
        
        // If...
        if (ans.equals("1")) {
            // The user says 1, print instructions and call the
            // inputCells() method for the user to input their own cells.
            System.out.println("\nEnter either a plus (+) or minus (-)");
            System.out.println("in order from top left to bottom right");
            System.out.println("(excluding borders)");
            System.out.println("\nTip:\nPress \"enter\" to speed type a plus (+),");
            System.out.println("as it will be registered as a plus (+).");
            inputCells();
        } else {
            // The user says 2, or any other value, print the notice and call the
            // randomizeCells() method to randomly add minus (-) cells
            // (This is the default option - if the user doesn't input 1 or 2, this
            // will be called, hence the lack of a condition.
            System.out.println("\nRandomizing cells...");
            randomizeCells();
        }

        // Print the current grid, now containing plus (-) and minus (-) signs
        // (before the removal of minis (-) blobs)
        displayGrid();
        
        // Loop through each cell (excluding the borders)
        for (int row = 1; row < (rowSize-1); row++) {
            for (int col = 1; col < (colSize-1); col++) {
                // If the cell is bad, run the floodFill to remove the blob.
                // Else, do nothing and move on.
                if ( grid[row][col].equals("-") ) {
                    blobsFound++;
                    floodFill(row, col);
                }
            }
        }
        
        // Display how many blobs were found
        System.out.println("The file had " + blobsFound + " cancer spots in it");
        System.out.println("The new grid is");
        
        // Print the new grid
        displayGrid();
    }
    
    /**
     * Allows the user to input their own cells
     */
    public static void inputCells() {
        // Create a new Scanner to scan the user's input
        Scanner scanString = new Scanner(System.in);
        
        // loop through each cell.
        for (int row = 1; row < (rowSize-1); row++) {
            // On each new row, notify the user what row they are on with some instructions.
            System.out.println("\nEntering cells on row " + (row+1) + " (excluding borders)");
            System.out.println("Enter cell then press \"enter\" to move onto the next cell.");
            for (int col = 1; col < (colSize-1); col++) {
                // Hold the user's input.
                String cellType = scanString.nextLine();
                // If the user's input is not a plus (+) or a minus (-)
                // then default the cell to plus (+).
                if ( !(cellType.equals("+") || cellType.equals("-")) ) {
                    cellType = "+";
                }
                // Add the cell to the grid.
                grid[row][col] = cellType;
            }
        }
    }
    
    /**
     * Fills 40 random elements in the array (that are not on the border)
     * with a minus (-)
     */
    public static void randomizeCells() {
        // Replace 40 random cells (not on the border) with a minus (-) sign.
        for (int i = 0; i < 40; i++) {
            // Generate a random row (not on the border)
            row = (int) (Math.random() * (rowSize - 2) + 1);
            // Generate a random column (not on the border)
            col = (int) (Math.random() * (colSize - 2) + 1);
            // Replace the cell with a minus (-).
            grid[row][col] = "-";
        }
    }
    
    /**
     * Checks the inputted cell
     * If it contains a minus (-) sign, check all around it
     * and replace each instance of a minus (-) with a space
     */
    public static void floodFill(int row, int col) {
        // If the cell is a minus (-)...
        if (grid[row][col].equals("-")) {
            // Replace the cell with a space
            grid[row][col] = " ";
            // Check each cell around it using this very method (recursion).
            floodFill(row - 1, col - 1);
            floodFill(row - 1, col);
            floodFill(row - 1, col + 1);
            floodFill(row, col - 1);
            floodFill(row, col + 1);
            floodFill(row + 1, col - 1);
            floodFill(row + 1, col);
            floodFill(row + 1, col + 1);
        }
    }
    
    /**
     * Display the grid
     */
    public static void displayGrid() {
        // create a String output to hold the grid.
        String output = "\n";
        // For each cell, add it to the String output
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                output += grid[row][col];
            }
            // At the end of each row, add a line break.
            output += "\n";
        }
        // Print the grid
        System.out.println(output);
    }
    
}
