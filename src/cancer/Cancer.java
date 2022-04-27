/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cancer;

/*
========================================================
WIP
- Based off BRIGHTSPACE lesson
========================================================
*/

/**
 *
 * @author cogid4775
 */
public class Cancer {

    public static String grid[][];
    public static int blobSize;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int row, col;
        //Create 2D array size 12 x 12
        grid = new String[12][12];

        //Fill the array with blanks
        for (row = 0; row < 12; row++) {
          for (col = 0; col < 12; col++) {
            grid[row][col] = "+";
          }
        }

        //Fill 70 random elements in the array with an asterisk
        //Do no choose an element along the border
        //The border will always contain elements with spaces
        //(blank border)
        for (int i = 0; i < 70; i++) {
          row = (int) (Math.random() * 10 + 1);
          col = (int) (Math.random() * 10 + 1);
          grid[row][col] = "-";
        }

        //Print out the current grid
        displayGrid();

        //variable to determine the size of the blob
        blobSize = 0;

        //Pick one random element in the array that is not along the
        //border and remove the blob at that location
        //NOTE: if a blank is chosen, the blob size is 0
        //and nothing is removed
        int blobRow = (int) (Math.random() * 10 + 1);
        int blobCol = (int) (Math.random() * 10 + 1);
        //blobRow = 2;
        //blobCol = 2;

        System.out.println("The blob at " + blobRow + "," + blobCol
          + " will be removed.");
        floodFill(blobRow, blobCol);
        System.out.println("The blob had " + blobSize +
          " items in it");
        System.out.println("The new grid is:");
        //Print out the new grid
        displayGrid();
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
    String output = " 01234567890\n";
    for (int row = 0; row <= 11; row++) {
      if ((row > -1) && (row < 11)) {
        output += (row % 10);
      } else {
        output += " ";
      }
      for (int col = 0; col <= 11; col++) {
        output += grid[row][col];
      }
      output += "\n";
    }
    System.out.println(output);
  }
    
}
