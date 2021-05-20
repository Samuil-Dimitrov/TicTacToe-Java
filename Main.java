package com.company;

import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    //Static methods are the methods in Java that can be called
    // without creating an object of class.
    public static void PrintGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();//empty row
        }
    }

    public static void AddXorO(char[][] gameBoard, int nPosition, String user) {
        // Prefer switch if the number of cases are more
        // than 5 because it's faster
        char cSymbol;

        if (user.equals("player")) {
            cSymbol = 'X';
            playerPositions.add(nPosition);
        } else {
            cSymbol = 'O';
            cpuPositions.add(nPosition);
        }

        switch (nPosition) {
            case 1:
                gameBoard[0][0] = cSymbol;
                break;
            case 2:
                gameBoard[0][2] = cSymbol;
                break;
            case 3:
                gameBoard[0][4] = cSymbol;
                break;
            case 4:
                gameBoard[2][0] = cSymbol;
                break;
            case 5:
                gameBoard[2][2] = cSymbol;
                break;
            case 6:
                gameBoard[2][4] = cSymbol;
                break;
            case 7:
                gameBoard[4][0] = cSymbol;
                break;
            case 8:
                gameBoard[4][2] = cSymbol;
                break;
            case 9:
                gameBoard[4][4] = cSymbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner(char[][] gameBoard) {
        //we make them as lists
        //rows
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        //colums
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        //diagonals
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(3, 5, 7);

        //we store them in a list
        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);

        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);

        winningConditions.add(leftDiagonal);
        winningConditions.add(rightDiagonal);


        for (List list : winningConditions) {
            //method (x.containsAll(c)) accepts a Collection c and will return true
            // if ALL elements in that Collection are also contained in x.
            if (playerPositions.containsAll(list)) {
                return "Congratulations! You won!";
            } else if (cpuPositions.containsAll(list)) {
                return "Computer wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                //if the board is full
                return "Tie!";
            }
        }
        return "";
    }


    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        PrintGameBoard(gameBoard);

        while (true) {

            //user turn
            System.out.println("Enter a position from 1 to 9: ");
            Scanner scanner = new Scanner(System.in);
            int nPosition = scanner.nextInt();
            while (playerPositions.contains(nPosition) || cpuPositions.contains(nPosition)) {
                System.out.println("Enter an empty position!");
                nPosition = scanner.nextInt();
            }
            String user = "player";
            AddXorO(gameBoard, nPosition, user);
            System.out.println();//empty row
            String winner = checkWinner(gameBoard);
            if (winner.length() > 0) { // if the string is not empty
                //then we have a winner
                System.out.println();
                System.out.println(winner);
                PrintGameBoard(gameBoard);
                break;
            }
            //////////////////////////////////////////

            //cpu turn
            user = "cpu";
            Random random = new Random();
            //generate random number from 1 to 9

            nPosition = random.nextInt(9) + 1;
            while (playerPositions.contains(nPosition) || cpuPositions.contains(nPosition)) {
                nPosition = random.nextInt(9) + 1;
            }
            AddXorO(gameBoard, nPosition, user);
            PrintGameBoard(gameBoard);

            winner = checkWinner(gameBoard);
            if (winner.length() > 0) {
                System.out.println();
                System.out.println(winner);
                PrintGameBoard(gameBoard);
                break;
            }
        }

    }
}
