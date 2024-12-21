package atlassian2024.snakegame.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class Board {

    @Getter
    @Setter
    private int ROW_COUNT;

    @Getter
    @Setter
    private int COLUMN_COUNT;

    @Getter
    @Setter
    private Cell[][] cells;

    @Getter
    @Setter
    private Cell currentFoodCell;

    public Board(int rc, int cc, int initRow, int initCol){
        this.ROW_COUNT = rc;
        this.COLUMN_COUNT = cc;

        cells = new Cell[ROW_COUNT][COLUMN_COUNT];

        for(int i=0; i<ROW_COUNT; i++){
            for(int j=0; j<COLUMN_COUNT; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        cells[initRow][initCol].setCellType(CellType.SNAKE);
    }

    public Cell getCell(int r, int c){
        return cells[r][c];
    }

    public void generateFood() {

        while (currentFoodCell == null || currentFoodCell.getCellType() != CellType.FOOD) {

            int row = (int) (Math.random() * ROW_COUNT);
            int column = (int) (Math.random() * COLUMN_COUNT);

            if (cells[row][column].getCellType() == CellType.SNAKE)
                    continue;

            cells[row][column].setCellType(CellType.FOOD);
            currentFoodCell = cells[row][column];
        }
    }

    public boolean isWall(Cell nextCell) {
        if (nextCell.getRow() == ROW_COUNT || nextCell.getRow() <0)
            return true;
        else if (nextCell.getColumn() == COLUMN_COUNT || nextCell.getColumn() <0)
            return true;
        else
            return false;
    }

    public void printBoard() {
        System.out.println("Board ::");
        for(int i=0; i<ROW_COUNT; i++){
            for(int j=0; j<COLUMN_COUNT; j++) {
                if (cells[i][j].getCellType() == CellType.EMPTY)
                    System.out.print("_");
                else if (cells[i][j].getCellType() == CellType.FOOD)
                    System.out.print("F");
                else if (cells[i][j].getCellType() == CellType.SNAKE)
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}
