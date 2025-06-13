package com.game;

import com.Exception.BombsMaxLimitException;

import java.util.Scanner;

public class MineSweeper {

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int gridSize = scanner.nextInt();
        System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int noOfBombs = scanner.nextInt();
        GridManager gridManager = new GridManager(gridSize);
        gridManager.insertDefaultValues();
        try {
            gridManager.insertBombs(noOfBombs);
        } catch (BombsMaxLimitException e) {
            throw new RuntimeException(e);
        }
        gridManager.insertAdjacentBombCount();
        System.out.println("Here is your minefield:");
        gridManager.printGrid();
        int result = playGame(scanner, gridSize, gridManager);
        if(result == -1){
            System.out.println("Oh no, you detonated a mine! Game over.");
        } else if (result == 1) {
            System.out.println("Congratulations, you have won the game!");
        }
    }

    public int playGame(Scanner scanner, int gridSize, GridManager gridManager){
        int result = 0;
        while(result == 0){
            System.out.println("Select a square to reveal (e.g. A1): D4");
            String selectedCell = scanner.next();
            if(!validateCellId(selectedCell, gridSize)){
                System.out.println("Invalid Cell ID " + selectedCell + "Select a square to reveal (e.g. A1): D4");
            }
            int x = selectedCell.charAt(0) - 'A';
            int y = Integer.parseInt(selectedCell.substring(1)) - 1;
            result = gridManager.updateGridCell(x, y);
            if(result  == -1){
                return result;
            }
            System.out.println("Here is your updated minefield:");
            gridManager.printGrid();
        }
        return result;
    }

    public boolean validateCellId(String cellId, int gridSize){
        if(!cellId.matches("^[A-Z]([1-9]|1[0-9]|2[0-6])$")){
            return false;
        }
        int x = cellId.charAt(0) - 'A';
        int y = Integer.parseInt(cellId.substring(1));
        if(x > gridSize || y > gridSize || gridSize > 26){
            return false;
        }
        return true;
    }
}
