package atlassian2024.snakegame.dto;

import lombok.Getter;
import lombok.Setter;

public class Cell {

    @Getter
    @Setter
    private int row;

    @Getter
    @Setter
    private int column;

    @Getter
    @Setter
    private CellType cellType;

    public Cell(int r, int c){
        this.row = r;
        this.column = c;
        this.cellType = CellType.EMPTY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (row != cell.row) return false;
        if (column != cell.column) return false;
        return cellType == cell.cellType;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + (cellType != null ? cellType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", cellType=" + cellType +
                '}';
    }
}
