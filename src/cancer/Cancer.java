/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cancer;
import java.util.Scanner;

/*
========================================================
WIP
- Based off BRIGHTSPACE lesson
TODO
- Fix intend spacing inconsistencies
- Add better comments / javadoc
- Finish
========================================================
*/

/**
 *
 * @author cogid4775
 */
public class Cancer {

    public static String grid[][];
    public static int blobSize;
    
    public static int rowSize;
    public static int colSize;
    
    public static int row;
    public static int col;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scanInt = new Scanner(System.in);
        
        System.out.println("Enter number of rows:");
        rowSize = scanInt.nextInt();
        System.out.println("Enter number of columns:");
        colSize = scanInt.nextInt();
        //Create 2D array size 12 x 12
        grid = new String[rowSize][colSize];

        //Fill the array with plus (+)
        for (row = 0; row < rowSize; row++) {
          for (col = 0; col < colSize; col++) {
            grid[row][col] = "+";
          }
        }
        
        System.out.println("Do you want to input cells manually or generate random cells automatically?");
        System.out.println("1 - Input cells manually");
        System.out.println("2 - Generate random cells automatically");
        
        Scanner scanString = new Scanner(System.in);
        
        String ans = scanString.nextLine();
        
        if (ans.equals("1")) {
            System.out.println("Enter either a plus (+) or minus (-)");
            System.out.println("in order from top left to bottom right");
            System.out.println("(excluding borders)");
            inputCells();
        } else if (ans.equals("2")) {
            System.out.println("Randomizing cells...");
            randomizeCells();
        }

//        //Fill 70 random elements in the array with an asterisk
//        //Do no choose an element along the border
//        //The border will always contain elements with spaces
//        //(blank border)
//        for (int i = 0; i < 40; i++) {
//          row = (int) (Math.random() * (rowSize - 2) + 1);
//          col = (int) (Math.random() * (colSize - 2) + 1);
//          grid[row][col] = "-";
//        }

        //Print out the current grid
        displayGrid();

        //variable to determine the size of the blob
        blobSize = 0;

        //Pick one random element in the array that is not along the
        //border and remove the blob at that location
        //NOTE: if a blank is chosen, the blob size is 0
        //and nothing is removed
        int blobRow = (int) (Math.random() * (rowSize - 2) + 1);
        int blobCol = (int) (Math.random() * (rowSize - 2) + 1);
        //blobRow = 2;
        //blobCol = 2;

        System.out.println("The blob at " + (blobRow+1) + "," + (blobCol+1)
          + " will be removed.");
        floodFill(blobRow, blobCol);
        System.out.println("The blob had " + blobSize +
          " items in it");
        System.out.println("The new grid is:");
        //Print out the new grid
        displayGrid();
    }
    
    public static void inputCells() {
        Scanner scanString = new Scanner(System.in);
        for (int row = 1; row < (rowSize-1); row++) {
            System.out.println("\nEntering cells on row " + (row+1) + " (excluding borders)");
          for (int col = 1; col < (colSize-1); col++) {
            String cellType = scanString.nextLine();
            if ( !(cellType.equals("+") || cellType.equals("-")) ) {
                cellType = "+";
            }
            grid[row][col] = cellType;
          }
        }
    }
    
    public static void randomizeCells() {
        //Fill 70 random elements in the array with an asterisk
        //Do no choose an element along the border
        //The border will always contain elements with spaces
        //(blank border)
        for (int i = 0; i < 40; i++) {
          row = (int) (Math.random() * (rowSize - 2) + 1);
          col = (int) (Math.random() * (colSize - 2) + 1);
          grid[row][col] = "-";
        }
    }
    
    public static void floodFill(int row, int col) {
        if (grid[row][col].equals("-")) {
          grid[row][col] = " ";
          blobSize++;
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

  public static void displayGrid() {
    String output = "\n";
    for (int row = 0; row < rowSize; row++) {
      for (int col = 0; col < colSize; col++) {
        output += grid[row][col];
      }
      output += "\n";
    }
    System.out.println(output);
  }
    
}
