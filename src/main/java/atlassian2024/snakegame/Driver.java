package atlassian2024.snakegame;

import atlassian2024.snakegame.dto.Board;
import atlassian2024.snakegame.dto.Cell;
import atlassian2024.snakegame.dto.Direction;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Board board = new Board(5, 5, 0, 0);

        Cell cell = board.getCell(0, 0);

        Snake snake = new Snake(cell);

        Game game = new Game(snake, board);

        Scanner keyboard = new Scanner(System.in);

        while (!game.isGameOver()) {
            board.printBoard();
            System.out.println("enter an integer");
            Direction direction = getDirection(keyboard.nextInt());

            if (direction == null) {
                System.out.println("wrong input");
                continue;
            }
            if (direction != Direction.NONE)
                game.setDirection(direction);
            game.update();
        }
    }

    public static Direction getDirection(int input) {
        if (input == 4)
            return Direction.LEFT;
        if (input == 6)
            return Direction.RIGHT;
        if (input == 8)
            return Direction.UP;
        if (input == 2)
            return Direction.DOWN;
        if (input == 5)
            return Direction.NONE;

        return null;

    }
}
