import java.util.Scanner;

/**
 * @author Peyton Terry terrypn@mail.uc.edu
 * 06 November 2024
 */

public class TicTacToe {
    private static final int ROW = 3; //Board Size
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // Player X or Player O set to X to start game

    /**
     * playGame to start game
     * clear board to start off new game fresh
     */
    public static void main(String[] args) {
        boolean playGame;

        do {
            clearBoard();
            playGame = playRound(); //playRound to see if player wants to play again
        }while (playGame);

        System.out.println("Thanks for playing!");
    }

    private static void clearBoard() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                board[r][c] = " "; //initialize a new board
            }
        }
    }

    /**
     * Actually really proud of this table. It took a lot of time and trial and error to get it set up correctly
     */
    private static void displayBoard() {
        System.out.printf("   1   2   3%n"); //Row Headers
        for (int r = 0; r < ROW; r++) {
            System.out.printf("%d", r + 1); //Row Numbers
            for (int c = 0; c < COL; c++) {
                System.out.printf("|%3s", board[r][c]);
            }
            System.out.println("|"); // End lines
            if(r < ROW - 1)
                System.out.printf(" +---+---+---+%n"); //Separates Rows
        }
    }

    /**
     * in Scanner used to read System.in
     * isWin method to check is currentPlayer has won
     * isTie method to check for tie
     * @return get confirmation to continue or quit
     */
    private static boolean playRound() {
        Scanner in = new Scanner(System.in);
        boolean done = false;

        do {
            displayBoard();
            playerMove();

            if(isWin(String.valueOf(currentPlayer))){
                displayBoard();
                System.out.println("Congratulations " + currentPlayer + "'s won!");
                done = true;
            }
            else if (isTie()){
                displayBoard();
                System.out.println("It's a tie!");
                done = true;
            }
            else
                changePlayer();
        }while(!done);
        return SafeInput.getYNConfirm(in , "Would you like to play again?");
    }

    /**
     * Current Player X listed at top in String
     * ? acts as if else
     * current player is X changePlayer will change to O after turn
     * .equals instead of == because of the String
     */
    private static void changePlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    /**
     * in is Scanner to read System.in
     * SafeInput in order to get good input from method and user
     * ValidMove to make sure the cell is free for input
     */
    private static void playerMove() {
        Scanner in = new Scanner(System.in);
        int rowMove, colMove;
        boolean validMove = false;

        do {
            rowMove = SafeInput.getRangedInt(in, "Enter a row ", 1, 3) - 1;
            colMove = SafeInput.getRangedInt(in, "Enter a column ", 1, 3) - 1;

            if(isValidMove(rowMove, colMove)) {
                board[rowMove][colMove] = currentPlayer;
                validMove = true;
            }
            else{
                System.out.println("That space is already taken. Please try again.");
            }
        }while(!validMove);
    }

    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    /**
     * @param player represents either X or O as it changes
     * @return true for win
     * will check each row for three of the same player
     */
    private static boolean isRowWin(String player)
    {
        for( int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param player represents either X or O as it changes
     * @return true for win
     * will check each column for three of the same player
     */
    private static boolean isColWin(String player)
    {
        for( int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param player represents either X or O as it changes
     * @return true for win
     * will check each diagonal for three of the same player
     */
    private static boolean isDiagonalWin(String player)
    {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
        {
            return true;
        }
        else  if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player))
        {
            return true;
        }
        else
            return false;
    }

    private static boolean isWin(String player)
    {
        if(isRowWin(player))
        {
            return true;
        }
        else if(isColWin(player))
        {
            return true;
        }
        else if(isDiagonalWin(player))
        {
            return true;
        }
        else
            return false;
    }

    private static boolean isTie(){
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (board[r][c].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}