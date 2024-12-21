package atlassian2024.snakegame;

import atlassian2024.snakegame.dto.Cell;
import atlassian2024.snakegame.dto.CellType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {

    private Deque<Cell> snakeBody;

    @Getter
    @Setter
    private Cell head;

    public Snake(Cell initialPosition){
        this.snakeBody = new ArrayDeque<>();
        this.head = initialPosition;
        this.head.setCellType(CellType.SNAKE);
        snakeBody.offer(this.head);
    }

    public void move(Cell nextCell){

        boolean isFood = nextCell.getCellType()==CellType.FOOD;

        if (!isFood) {
            Cell tail = snakeBody.pollLast();
            tail.setCellType(CellType.EMPTY);
        }

        snakeBody.addFirst(nextCell);
        nextCell.setCellType(CellType.SNAKE);
        head = nextCell;
    }

    public boolean isOwnBody(Cell nextCell) {
        for (Cell cell : snakeBody) {
            if (cell == nextCell) { // snake hit itself
                System.out.println("snake hits its own body");
                return true;
            }
        }
        return false;
    }

}
