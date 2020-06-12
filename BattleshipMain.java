/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author me
 */
import java.util.*;

public class BattleshipMain {
    private static String[][] gameBoardPlayer = createBoard();
    private static String[][] gameBoardComp = createBoard();
    private static String[][] displayBoard = createBoard();
    private static Ship patrol = new Ship("patrol", 2);
    private static Ship submarine = new Ship("submarine", 3);
    private static Ship destroyer = new Ship("destroyer", 3);
    private static Ship battleship = new Ship("battleship", 4);
    private static Ship carrier = new Ship("carrier", 5);
    private static Ship[] fleet = {patrol, submarine, destroyer, battleship, carrier};
    private static String[] symbols = {" P ", " S ", " D ", " B ", " C "};
    private static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        //creating and setting up game board for player
        setUpGamePlayer();
        
        //display of game board for computer
        setUpGameComp();
        System.out.println();
        
        System.out.println();
        System.out.println();
        
        //starting game
        //playGame();
    }
    public static void setUpGamePlayer() {
        String orientation; 
        int xCoord;
        int yCoord;
        
        System.out.println("Let's place your ships on this " + gameBoardPlayer.length + ""
                           + " by " + gameBoardPlayer[0].length + " board!");
        
        displayBoard(gameBoardPlayer);
        
        System.out.println("\n - - - - - - - - - - - - - - - - - - - \n");
        System.out.print("Position ships randomly (r) or manually (m): ");
        String choice = scan.next();
        
        if (choice.equalsIgnoreCase("r"))  {
            System.out.println("Outcome: ");
            for (int i = 0; i < fleet.length; i++) {
                int random = (int)(Math.random() * 10);
                //0 - 4: ships placed horizontally, 5 - 9: ships placed vertically
                if (random < 5) {
                    placeShipHorzRand(gameBoardPlayer, fleet[i], symbols[i]);
                }
                else {
                    placeShipVertRand(gameBoardPlayer, fleet[i], symbols[i]);
                }
            }
            //display after all ships are randomly placed
            displayBoard(gameBoardPlayer);
        }
        else {
            for (int i = 0; i < fleet.length; i++) {
                System.out.println();
                displayBoard(gameBoardPlayer);
                System.out.println();
                System.out.println("Current ship: " + fleet[i].getTitle());
                System.out.println("Length: " + fleet[i].getLength());
                System.out.println();
                System.out.print("Place vertically (v) or horizontally (h): ");
                orientation = scan.next();
                System.out.print("Starting x-coordinate: ");
                xCoord = scan.nextInt();
                System.out.print("Starting y-coordinate: ");
                yCoord = scan.nextInt();
                
                if (orientation.equalsIgnoreCase("h")) {
                    placeShipHorzMan(gameBoardPlayer, fleet[i], symbols[i], xCoord, yCoord);
                }
                else {
                    placeShipVertMan(gameBoardPlayer, fleet[i], symbols[i], xCoord, yCoord);
                }
            }
        }
        System.out.print("\nComplete!! Now the computer is setting up . . .");
        
    }
    public static void setUpGameComp() {
        for (int i = 0; i < fleet.length; i++) {
            int random = (int)(Math.random() * 10);
            
            if (random < 5) {
                placeShipHorzRand(gameBoardComp, fleet[i], symbols[i]);
            }
            else {
                placeShipVertRand(gameBoardComp, fleet[i], symbols[i]);
            }
        }
        
        System.out.print(" Computer is finished!");
        //displayBoard(gameBoardComp);
        
    }
    public static String[][] createBoard() {
        final int ROW_LENGTH = 10;
        final int COL_LENGTH = 10;
        String[][] board = new String[ROW_LENGTH][COL_LENGTH];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = " * ";
            }
        }
        return board;
    }
    
    public static void displayBoard(String[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }
    
    public static void placeShipHorzRand(String[][] board, Ship ship, String symbol) {
        int indexR = (int)(Math.random() * board.length);
        int indexC = (int)(Math.random() * (board[0].length - (ship.getLength() - 1)));

        for (int c = indexC; c < indexC + ship.getLength(); c++) {
            if (!board[indexR][c].equals(" * ")) {
                indexR = (int)(Math.random() * board.length);
                indexC = (int)(Math.random() * (board[0].length - (ship.getLength() - 1)));
                    
                c = indexC - 1;
            }
        }
        for (int c = indexC; c < indexC + ship.getLength(); c++) {
            board[indexR][c] = symbol;
        } 
    }
    
    public static void placeShipHorzMan(String[][] board, Ship ship, String symbol, int x, int y) {
        int indexC = x; //limited to length - 1
        while (indexC >= board[0].length - (ship.getLength() - 1) || indexC < 0) {
            System.out.println("\nThere is an out of bounds.");
            System.out.print("Pick a new x-coordinate: ");
            indexC = scan.nextInt();
        }
        
        int indexR = y;
        while (indexR >= board.length || indexR < 0) {
            System.out.println("\nThere is an out of bounds.");
            System.out.print("Pick a new y-coordinate: ");
            indexR = scan.nextInt();
        }


        for (int c = indexC; c < indexC + ship.getLength(); c++) {
            if (!board[indexR][c].equals(" * ")) {
                System.out.println("Coordinates caused overlap.");
                System.out.print("Pick a new x-coordinate: ");
                indexC = scan.nextInt();
                System.out.print("Pick a new y-coordinate: ");
                indexR = scan.nextInt();
                    
                c = indexC - 1;
            }
        }
        for (int c = indexC; c < indexC + ship.getLength(); c++) {
            board[indexR][c] = symbol;
        } 
    }
    
    public static void placeShipVertRand(String[][] board, Ship ship, String symbol) {
        int indexR = (int)(Math.random() * (board.length - (ship.getLength() - 1)));
        int indexC = (int)(Math.random() * board[0].length);
        
        for (int r = indexR; r < indexR + ship.getLength(); r++) {
            if (!board[r][indexC].equals(" * ")) {
                indexR = (int)(Math.random() * (board.length - (ship.getLength() - 1)));
                indexC = (int)(Math.random() * board[0].length);
                    
                r = indexR - 1;
            }
        }
        for (int r = indexR; r < indexR + ship.getLength(); r++) {
            board[r][indexC] = symbol;
        } 
    }
    
    public static void placeShipVertMan(String[][] board, Ship ship, String symbol, int x, int y) {
        int indexC = x; //limited to length - 1
        while (indexC >= board[0].length || indexC < 0) {
            System.out.println("\nThere is an out of bounds.");
            System.out.print("Pick a new x-coordinate: ");
            indexC = scan.nextInt();
        }
        
        int indexR = y;
        while (indexR >= board.length - (ship.getLength() - 1) || indexR < 0) {
            System.out.println("\nThere is an out of bounds.");
            System.out.print("Pick a new y-coordinate: ");
            indexR = scan.nextInt();
        }

        for (int r = indexR; r < indexR + ship.getLength(); r++) {
            if (!board[r][indexC].equals(" * ")) {
                System.out.println("Coordinates caused overlap.");
                System.out.print("Pick a new x-coordinate: ");
                indexC = scan.nextInt();
                System.out.print("Pick a new y-coordinate: ");
                indexR = scan.nextInt();
                    
                r = indexR - 1;
            }
        }
        for (int r = indexR; r < indexR + ship.getLength(); r++) {
            board[r][indexC] = symbol;
        } 
        
    }
        
     
    public static void playGame() {
        System.out.println("Welcome to the game of Battleship against the computer!");
        System.out.println("Please press ENTER to start.");
        scan.nextLine();
        scan.nextLine();
        System.out.println();
        System.out.println("GUESSING BOARD");
        displayBoard(displayBoard);
        System.out.println(" -----------------------------");
        displayBoard(gameBoardPlayer);
        System.out.println("YOUR BOARD");
        /*int count = 5;
        while (count >= 0) {
            System.out.println("Enter coordinates: ");
            int xCoord = scan.nextInt();
            int yCoord = scan.nextInt();
            
            
            if(!gameBoardComp[xCoord][yCoord].equals(" * ")) {
                System.out.println("You hit one of the computer's ships!");
                displayBoard[xCoord - 1][yCoord - 1] = " H ";
            }
            else {
                System.out.println("You missed!");
                displayBoard[xCoord - 1][yCoord - 1] = " M ";
            }
            
            displayBoard(displayBoard);
        }*/
    }
}
