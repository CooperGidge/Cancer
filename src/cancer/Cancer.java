/*
 * Cancer.java
 * ICS4U
 */
package cancer;

// Imports required to scan a text file
import java.io.*;
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
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        // Create a 2D array (grid) with 15 rows and 15 columns.
        // To allow the user to customize the amount of rows and columns,
        // comment out the two lines below and uncomment the above method call.
        rowSize = 15;
        colSize = 15;
        grid = new String[rowSize][colSize];
        
        readTextFile();

        // Print the current grid, now containing plus (-) and minus (-) signs
        // (before the removal of minis (-) blobs)
        displayGrid();
        
        // Check for cancer cells
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
        
        // Create a String to contain a plural "s" if there are more than 1 blobs found
        // (for the printed message to be grammatically correct)
        // If there is not more than 1 blob found, keep the String empty so it doesn't
        // impact the message
        String blobsFoundPlural = "";
        if (blobsFound > 1) {
            blobsFoundPlural = "s";
        }
        
        // Display how many blobs were found
        System.out.println("The file had " + blobsFound + " cancer spot" + blobsFoundPlural + " in it");
        System.out.println("The new grid is");
        
        // Print the new grid
        displayGrid();
    }
    
    /**
     * Read a text file
     * @throws FileNotFoundException
     */
    public static void readTextFile() throws FileNotFoundException {
        File text = new File("cancer.txt");
        
        Scanner line = new Scanner(text);
        //Reading each line of the file using Scanner class
        int lineNum = 0;
        while(line.hasNextLine()){
            String thisRow = line.nextLine();
            for (int j = 0; j < colSize; j++) {
                grid[lineNum][j] = thisRow.substring(j, j+1);
            }
            lineNum++;
        }
        
        line.close();
    }
    
    /**
     * Method for setting the amount of rows and columns in the grid
     * By default, it is not used but it exists just in case
     */
    public static void setGridDimensions() {
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
     * @param row the row of the cell
     * @param col the column of the cell
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
        String output = "";
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
