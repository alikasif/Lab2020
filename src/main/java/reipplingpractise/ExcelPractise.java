package reipplingpractise;

import java.util.*;

class Cell {
    String cellName;
    String rawValue;
    String value;
    List<Cell> observer = new ArrayList<>();

    public void addObserver(Cell cell, Set<Cell> cycles) {
        observer.add(cell);
        cycles.addAll(observer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(cellName, cell.cellName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellName);
    }
}

class Row {
    Map<String, Cell> cellMap = new HashMap<>();
}

class ExcelSheet {
    Map<String, Row> rowMap = new HashMap<>();
    Map<String, Cell> sheetMap = new HashMap<>();

    public void set(String cellName, String value) {
        setRawValue(cellName, value);
        setValue(cellName);
        notifyObservers(cellName);
    }

    public String get(String cellName) {
        Cell cell = sheetMap.get(cellName);
        if(cell == null)
            throw new IllegalArgumentException("cell not found "+ cellName);
        return cell.value;
    }

    private void notifyObservers(String cellName) {
        Cell cell = sheetMap.get(cellName);
        for(Cell c : cell.observer) {
            setValue(c.cellName);
        }
    }

    private void setValue(String cellName) {
        Integer calcValue = 0;
        Cell cell = sheetMap.get(cellName);
        String rawValue = cell.rawValue;

        if(rawValue.startsWith("=")) {
            String[] split = rawValue.substring(1).split("\\+");
            for(String s : split) {
                if (!Character.isDigit(s.charAt(0))) {
                    Cell refCell = sheetMap.get(s);
                    if(refCell == null)
                        throw new IllegalArgumentException("cell not found "+ s);
                    calcValue = calcValue + Integer.parseInt(refCell.value);
                }
                else {
                    calcValue = calcValue + Integer.parseInt(s);
                }
            }
        }
        else {
            calcValue = calcValue + Integer.parseInt(rawValue);
        }
        cell.value = String.valueOf(calcValue);
    }

    private void setRawValue(String cellName, String value) {

        Cell cell = sheetMap.getOrDefault(cellName, new Cell());
        cell.cellName = cellName;
        cell.rawValue = value;
        sheetMap.put(cellName, cell);

        if(value.startsWith("=")) { // A = 1+B;
            String[] split = value.substring(1).split("\\+");
            for(String s : split) {
                if (!Character.isDigit(s.charAt(0))) {

                    if(!sheetMap.containsKey(s))
                        throw new IllegalArgumentException("cell not found "+s);

                    if(cell.observer.contains(sheetMap.get(s)))
                        throw new IllegalStateException("cycle detected " + s +" "+cellName);

                    sheetMap.get(s).observer.add(cell);
                }
            }
        }
    }
}

public class ExcelPractise {

    public static void main(String[] args) {
        ExcelSheet excelSheet = new ExcelSheet();
        excelSheet.set("A1", "=EE1");
        excelSheet.set("B1", "2");
        excelSheet.set("C1", "=3+A1");
        System.out.println(excelSheet.get("A1"));
        System.out.println(excelSheet.get("B1"));
        System.out.println(excelSheet.get("C1"));
        excelSheet.set("D1", "=3+A1+B1+C1");
        System.out.println(excelSheet.get("D1"));
        excelSheet.set("A1", "=B1");
        System.out.println(excelSheet.get("B1"));
        System.out.println(excelSheet.get("C1"));
        System.out.println(excelSheet.get("D1"));
        excelSheet.set("F1", "=A1");
        excelSheet.set("G1", "=A1");
        System.out.println(excelSheet.get("B1"));

        excelSheet.set("A2", "1");
        excelSheet.set("B2", "=A2");
        excelSheet.set("C2", "=B2");
        excelSheet.set("D2", "=C2+B2");
        excelSheet.set("E2", "=D2");
        excelSheet.set("C2", "=E2");
    }
}
