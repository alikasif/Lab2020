package atlassian2024.snakegame;

import atlassian2024.snakegame.dto.Board;
import atlassian2024.snakegame.dto.Cell;
import atlassian2024.snakegame.dto.Direction;
import lombok.Getter;

public class Game {

    private Snake snake;
    private Board board;
    private Direction direction;
    @Getter
    private boolean gameOver;

    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
        this.gameOver = false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void update() {

        if (!gameOver) {
            if (direction != Direction.NONE) {

                Cell nextCell = getNextCell(snake.getHead());

                if (snake.isOwnBody(nextCell) || board.isWall(nextCell)) {
                    setDirection(Direction.NONE);
                    gameOver = true;
                } else {
                    snake.move(nextCell);
                    board.generateFood();
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition) {

        int row = currentPosition.getRow();
        int col = currentPosition.getColumn();

        if (direction == Direction.RIGHT) {
            col++;
        } else if (direction == Direction.LEFT) {
            col--;
        } else if (direction == Direction.UP) {
            row--;
        } else if (direction == Direction.DOWN) {
            row++;
        }

        if (board.isWall(new Cell(row, col)))
            throw new IllegalArgumentException(" !! hit wall at "+ row+"|"+col +" !!");

        Cell nextCell = board.getCells()[row][col];

        return nextCell;
    }
}
