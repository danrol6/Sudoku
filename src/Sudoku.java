import java.util.*;
import java.util.Arrays;

public class Sudoku {

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        int[][] orig = new int[9][9];

        Scanner kb = new Scanner(System.in);
        initializeBoard(board);
        initializeBoard(orig);

        printBoard(board);
        boolean check = checkWin(board);
        boolean check2 = checkWinThree(board);
        // 1. get user input, and mark the game board
        while (check == false && check2 == false) {
            int row, col, val;
            System.out.print("Enter row#:");
            row = kb.nextInt();
            System.out.print("Enter col#:");
            col = kb.nextInt();
            System.out.print("Enter value:");
            val = kb.nextInt();

            if (orig[row][col] == 0) { // was this spot a blank in the beginning
                board[row][col] = val;
            }
            printBoard(board);
        }

        System.out.println("Winner, Winner!");

    }

    // checks to see if it wins, accepts the current state of the board and returns a boolean. true if game won false otherwise
    //the method checks every 3x3. So, it checks the first row from column 0-2, then the second row from column 0-2 then third from
    //column 0-2 etc... if at any point it finds that there was no winnner, returns false, otherwise it finds a winner and returns true.
    public static boolean checkWinThree(int[][] board) {
        boolean win = true; 

        int counter = 0; // serves as the index of the array checkthree
        int checkthree[] = new int[9]; // stores the numbers of the 3x3

        for (int x = 0; x < board.length; x += 3) { // this loops through every 3 row and column. so the program runs at 0 at 3, and at 6
            for (int i = 0; i < board.length; i++) {
                for (int j = x; j < x + 3; j++) { 
                   //runs in 3s. every third column, every third row.
                    //Ex:    0 0, 0 1, 0 2
                    //       1 0, 1 1, 1 2
                    //       2 0, 2 1, 2 2  
                    checkthree[counter] = board[i][j];
                    counter++; // increases the index of the array
                }
                if ((i + 1) % 3 == 0) { //every 3rd loop checks the result because it means it finished with the 3x3 grid
                    Arrays.sort(checkthree); //sorts the array
                    for (int y = 0; y < checkthree.length; y++) { 
                        if (checkthree[y] != y + 1) {
                            win = false;
                            return win;
                        }
                    }
                    checkthree = new int[9]; //resets the array to get the next 3x3
                    counter = 0; //resets the counter
                }
            }
        }
        return win;
    }

    // checks to see if it wins, accepts the current state of the board and returns a boolean. true if game won false otherwise
    //the method checks one row and one column at a time. So, it checks the first row and the first column, then the second row and second
    // column, etc... if at any point it finds that there was no winnner, returns false, otherwise it finds a winner and returns true.
    public static boolean checkWin(int[][] board) {
        boolean win = true;

        //loops through rows
        for (int i = 0; i < board.length; i++) {
            int checkrow[] = new int[9];
            int checkcol[] = new int[9];

            //loops through columns and stores the numbers in that row and in that column
            for (int j = 0; j < board.length; j++) {
                checkrow[j] = board[i][j];
                checkcol[j] = board[j][i];
            }

            Arrays.sort(checkrow);//sorts the numbers
            Arrays.sort(checkcol);

            //checks the sorted numbers to see if there is a winnner by checking the values of the
            //sorted array. first number in the array with 1, second with 2, third with 3 etc...
            // if the numbers dont match at any point then there was not a winner and the program returns
            //false.
            for (int x = 1; x < board.length; x++) {
                if (checkrow[x] != x + 1 || checkcol[x] != x + 1) {
                    win = false;
                    return win;
                }
            }
        }

        return win;
    }

    //--------------------------------------------- ------------------------------


    public static void printBoard(int[][] gb) {
        int i, j;
        for (i = 0; i < gb.length; i++) { // rows
            System.out.println("-------------------");
            for (j = 0; j < gb[i].length; j++) { // columns
                if (gb[i][j] == 0) {  // it's a blank
                    System.out.print("| ");
                } else {
                    System.out.print("|" + gb[i][j]);
                }
            }
            System.out.println("|");

        }

        System.out.println("-------------------");
    }

    public static void initializeBoard(int[][] gb) {
        gb[0][0] = 2;
        gb[0][1] = 4;
        gb[0][2] = 3;
        gb[0][3] = 7;
        gb[0][4] = 0;
        gb[0][5] = 1;
        gb[0][6] = 8;
        gb[0][7] = 6;
        gb[0][8] = 9;

        gb[1][0] = 5;
        gb[1][1] = 0;
        gb[1][2] = 7;
        gb[1][3] = 9;
        gb[1][4] = 0;
        gb[1][5] = 0;
        gb[1][6] = 0;
        gb[1][7] = 3;
        gb[1][8] = 0;

        gb[2][0] = 0;
        gb[2][1] = 1;
        gb[2][2] = 0;
        gb[2][3] = 0;
        gb[2][4] = 4;
        gb[2][5] = 0;
        gb[2][6] = 0;
        gb[2][7] = 0;
        gb[2][8] = 5;

        gb[3][0] = 0;
        gb[3][1] = 2;
        gb[3][2] = 0;
        gb[3][3] = 0;
        gb[3][4] = 7;
        gb[3][5] = 0;
        gb[3][6] = 5;
        gb[3][7] = 0;
        gb[3][8] = 0;

        gb[4][0] = 3;
        gb[4][1] = 0;
        gb[4][2] = 5;
        gb[4][3] = 0;
        gb[4][4] = 0;
        gb[4][5] = 0;
        gb[4][6] = 2;
        gb[4][7] = 0;
        gb[4][8] = 7;

        gb[5][0] = 0;
        gb[5][1] = 0;
        gb[5][2] = 1;
        gb[5][3] = 0;
        gb[5][4] = 3;
        gb[5][5] = 0;
        gb[5][6] = 0;
        gb[5][7] = 4;
        gb[5][8] = 0;

        gb[6][0] = 1;
        gb[6][1] = 0;
        gb[6][2] = 0;
        gb[6][3] = 0;
        gb[6][4] = 9;
        gb[6][5] = 0;
        gb[6][6] = 0;
        gb[6][7] = 7;
        gb[6][8] = 0;

        gb[7][0] = 0;
        gb[7][1] = 6;
        gb[7][2] = 0;
        gb[7][3] = 0;
        gb[7][4] = 0;
        gb[7][5] = 7;
        gb[7][6] = 4;
        gb[7][7] = 0;
        gb[7][8] = 8;

        gb[8][0] = 7;
        gb[8][1] = 3;
        gb[8][2] = 4;
        gb[8][3] = 5;
        gb[8][4] = 0;
        gb[8][5] = 8;
        gb[8][6] = 6;
        gb[8][7] = 9;
        gb[8][8] = 1;
    }
}