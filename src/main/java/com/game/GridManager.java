package com.game;

import com.Exception.BombsMaxLimitException;

import java.util.*;
public class GridManager {
    int gridSize;
    GridCell[][] grid;

    public GridManager(int gridSize){
        this.gridSize = gridSize;
        this.grid = new GridCell[gridSize][gridSize];
    }

    public void insertDefaultValues(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = new GridCell(false, "-", false);
            }
        }
    }

    public void insertBombs(int noOfBombs) {
//        if (noOfBombs > gridSize * gridSize * 0.35)
//            throw new BombsMaxLimitException("maximum is 35% of the total squares");
        UniqueRandomPair u = new UniqueRandomPair();
        Set<UniqueRandomPair.Pair> bombCells = u.generatePair(gridSize, noOfBombs);
        for (UniqueRandomPair.Pair pair : bombCells) {
            grid[pair.x][ pair.y] = new GridCell(true, "B", false);;
        }
    }

    public void insertAdjacentBombCount(){
        // Define all 8 possible directions around the cell
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},          {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j].isBomb()) {
                    continue;
                }
                int bombCount = 0;
                for (int[] dir : directions) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];

                    if (newRow >= 0 && newRow < gridSize &&
                            newCol >= 0 && newCol < gridSize &&
                            grid[newRow][newCol].isBomb()) {
                        bombCount++;
                    }
                }
                grid[i][j] = new GridCell(false, String.valueOf(bombCount), false);
            }
        }
    }

    public void printGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if(grid[i][j].isVisible()){
                    System.out.print(grid[i][j].getValue() + "\t");
                } else {
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
    }

    public int updateGridCell(int x, int y){
        if(grid[x][y].isBomb()){
            return -1;
        }
        grid[x][y].setVisible(true);
        if(isWin()){
            return 1;
        }
        return 0;
    }
    public boolean isWin(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if(!grid[i][j].isVisible() && !grid[i][j].isBomb()){
                    return false;
                }
            }
        }
        return true;
    }
}

