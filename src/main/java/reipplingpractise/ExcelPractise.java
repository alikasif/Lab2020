package reipplingpractise;

import java.util.*;

class Cell {
    String cellName;
    String rawValue;
    String value;
    List<Cell> observers = new ArrayList<>();

    public void addObserver(Cell cell) {
        observers.add(cell);
    }

    public void setRawValue( Map<String, Cell> sheetMap) {
        String value = rawValue;
        if(value.startsWith("=")) { // A = 1+B;
            String[] split = value.substring(1).split("\\+");
            for(String s : split) {
                if (!Character.isDigit(s.charAt(0))) {

                    if(!sheetMap.containsKey(s))
                        throw new IllegalArgumentException("cell not found "+s);

                    if(this.observers.contains(sheetMap.get(s))) // A : =A
                        throw new IllegalStateException("cycle detected " + s +" " + cellName);

                    sheetMap.get(s).addObserver(this);
                }
            }
        }
    }

    public void setValue(Map<String, Cell> sheetMap) {

        Integer calcValue = 0;

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
        value = String.valueOf(calcValue);
        notifyObservers(sheetMap);
    }

    public void notifyObservers(Map<String, Cell> sheetMap) {
        for(Cell c : this.observers) {
            c.setValue(sheetMap);
        }
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
    @Override
    public String toString() {
        return "Cell{" +
                " Name='" + cellName + '\'' +
                ", RawValue='" + rawValue + '\'' +
                ", Value='" + value + '\'' +
                //", Observers='" + observers + '\'' +
                '}';
    }
}

class Row {
    Map<String, Cell> cellMap = new HashMap<>();
}

class ExcelSheet {
    Map<String, Row> rowMap = new HashMap<>();
    Map<String, Cell> sheetMap = new HashMap<>();

    public String get(String cellName) {
        Cell cell = sheetMap.get(cellName);
        if(cell == null)
            throw new IllegalArgumentException("cell not found "+ cellName);
        return cell.value;
    }

    public void set(String cellName, String value) {

        checkCycle(cellName, value);

        Cell cell = sheetMap.getOrDefault(cellName, new Cell());
        cell.cellName = cellName;
        cell.rawValue = value;
        sheetMap.put(cellName, cell);

        /*setRawValue(cellName, value);
        setValue(cellName);
        notifyObservers(cellName);*/

        cell.setRawValue(sheetMap);
        cell.setValue(sheetMap);
    }

    private void checkCycle(String cellName, String cellValue) {

        if(cellValue.contains(cellName))
            throw  new IllegalStateException("cycle found for "+ cellName + " cellValue: "+cellValue );

        List<String> cellNames = new ArrayList<>();
        cellNames.add(cellValue);

        while (!cellNames.isEmpty()) {
            String value = cellNames.remove(0);

            if (value.startsWith("=")) {

                String substring = value.substring(1);

                if (substring.contains("+")) {
                    String[] split = substring.split("\\+");
                    for (String s : split) {
                        if (!Character.isDigit(s.charAt(0))) {
                            cellNames.add(s);
                            cellNames.add(sheetMap.get(s).rawValue);
                        }
                    }
                }
                else {
                    if (!Character.isDigit(substring.charAt(0))) {
                        cellNames.add(substring);
                        cellNames.add(sheetMap.get(substring).rawValue);
                    }
                }
            }
            if(cellNames.contains(cellName)) {
                System.out.println();
                System.out.println("cycle found for " + cellName + " cellValue: "+cellValue +" value : " + cellNames);
                throw  new IllegalStateException("cycle found for "+ cellName + " cellValue: "+cellValue +" value : " + cellNames);
            }
        }
    }

    /*private void setRawValue(String cellName, String value) {

        Cell cell = sheetMap.get(cellName);

        if(value.startsWith("=")) { // A = 1+B;
            String[] split = value.substring(1).split("\\+");
            for(String s : split) {
                if (!Character.isDigit(s.charAt(0))) {

                    if(!sheetMap.containsKey(s))
                        throw new IllegalArgumentException("cell not found "+s);

                    if(cell.observers.contains(sheetMap.get(s)))
                        throw new IllegalStateException("cycle detected " + s +" "+cellName);

                    sheetMap.get(s).observers.add(cell);
                }
            }
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
        notifyObservers(cellName);
    }

    private void notifyObservers(String cellName) {
        Cell cell = sheetMap.get(cellName);
        for(Cell c : cell.observers) {
            setValue(c.cellName);
        }
    }*/

    @Override
    public String toString() {
        for(Map.Entry<String, Cell> entry : sheetMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        return "";
    }
}

public class ExcelPractise {

    public static void main(String[] args) {

        ExcelSheet excelSheet = new ExcelSheet();

        /*excelSheet.set("A1", "1");
        excelSheet.set("B1", "2");
        excelSheet.set("C1", "=3+A1");
        System.out.println(excelSheet.get("A1"));
        System.out.println(excelSheet.get("B1"));
        System.out.println(excelSheet.get("C1"));
        excelSheet.set("D1", "=3+A1+B1+C1");
        System.out.println(excelSheet.get("D1"));
        excelSheet.set("A1", "=B1");
        System.out.println();
        System.out.println(excelSheet.get("A1"));
        System.out.println(excelSheet.get("B1"));
        System.out.println(excelSheet.get("C1"));
        System.out.println(excelSheet.get("D1"));*/

        excelSheet.set("A", "1");
        excelSheet.set("B", "2");
        System.out.println("A => " +excelSheet.get("A"));
        System.out.println("B => " +excelSheet.get("B"));
        excelSheet.set("C", "=1+A");
        System.out.println("C => " +excelSheet.get("C"));
        excelSheet.set("D", "=C+B");
        System.out.println("D => " +excelSheet.get("D"));
        excelSheet.set("C", "=5+A");
        System.out.println("C => " +excelSheet.get("C"));
        System.out.println("D => " +excelSheet.get("D"));

        excelSheet.set("E", "=D+C");
        excelSheet.set("F", "=E");
        System.out.println("E => " +excelSheet.get("E"));
        System.out.println("F => " +excelSheet.get("F"));
        excelSheet.set("C", "=10+B");
        System.out.println("C => " +excelSheet.get("C"));
        System.out.println("D => " +excelSheet.get("D"));
        System.out.println("E => " +excelSheet.get("E"));
        System.out.println("F => " +excelSheet.get("F"));

        excelSheet.set("F", "=10+1+5");
        System.out.println("F => " +excelSheet.get("F"));

        excelSheet.toString();

        excelSheet.set("A2", "1");
        excelSheet.set("B2", "=A2");
        excelSheet.set("C2", "=B2");
        excelSheet.set("D2", "=C2+B2");
        excelSheet.set("E2", "=D2");
        //excelSheet.set("C2", "=E2");

        excelSheet.set("X1", "=X1+A");
    }
}
