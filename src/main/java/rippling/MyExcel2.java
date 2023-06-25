package rippling;

import java.util.*;

class Sheet {

    Map<String, Cell> sheetMap = new HashMap<>();

    Map<String, Row> rowMap = new HashMap<>();

    public Sheet() {
    }

    public void set(String cellName, String cellValue) {
        Cell cell = null;
        if(sheetMap.containsKey(cellName)) {
            cell = sheetMap.get(cellName);
            cell.setRawValue(cellValue);
        }
        else {
            cell = new Cell(cellName, cellValue);
            sheetMap.put(cellName, cell);
            cell.setRawValue(cellValue);
        }
        cell.evaluateValue();
        cell.notifyObservers();
    }

    public void setValue(String cellName, String cellValue) {
        String rowId = cellName.substring(1);
        Row row = rowMap.getOrDefault(rowId, new Row());
        rowMap.put(rowId, row);
        row.setValue(cellName, cellValue);
    }

    public String getValue(String cellName) {
        String rowId = cellName.substring(1);
        Row row = rowMap.get(rowId);
        if(row == null)
            throw  new RuntimeException("cell not set");
        return row.getValue(cellName);
    }

    public String get(String cellName) {
        if(!sheetMap.containsKey(cellName)) {
            throw  new RuntimeException("cell not set");
        }
        else {
            Cell cell = sheetMap.get(cellName);
            return cell.value;
        }
    }

    @Override
    public String toString() {
        for(Map.Entry<String, Cell> entry : sheetMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        return "";
    }

    class Row {
        Map<String, Cell> cellMap = new HashMap<>();

        public void setValue(String cellName, String cellValue) {
            Cell cell = cellMap.getOrDefault(cellName, new Cell(cellName, cellValue));
            cellMap.put(cellName, cell);
            sheetMap.put(cellName, cell);
            cell.setRawValue(cellValue);
            cell.evaluateValue();
            cell.notifyObservers();
        }

        public String getValue(String cellName) {
            Cell cell = cellMap.get(cellName);
            if(cell == null)
                throw  new RuntimeException("cell not set " + cellName);
            return cell.value;
        }
    }

    class Cell {
        String colName;
        String value;
        String rawValue;
        Set<Cell> observers;

        public Cell(String name, String value) {
            this.colName = name;
            this.rawValue = value;
            observers = new HashSet<>();
        }

        @Override
        public String toString() {
            return "Cell{" +
                    " Name='" + colName + '\'' +
                    ", RawValue='" + rawValue + '\'' +
                    ", Value='" + value + '\'' +
                    //", Observers='" + observers + '\'' +
                    '}';
        }

        public void setRawValue(String rawValue) {
            this.rawValue = rawValue;

            // it is referring to some other cell. set this cell as observer in referred cell observer list
            if(rawValue.startsWith("=")) {
                String tmp = rawValue.substring(1);
                String[] split = tmp.split("\\+");
                for(String s : split) {
                    if(s.matches("[A-Z][0-9]*+")) {
                        if(sheetMap.containsKey(s))
                            sheetMap.get(s).observers.add(this); // C = 1 + A ==> add C in observer list of A
                        else
                            throw new RuntimeException("cell not found "+ s);
                    }
                }
            }
            // System.out.println(this .colName + " observer: "+ observers);
        }

        public String evaluateValue() {
            value = setRecursively();
            notifyObservers();
            return value;
        }

        private String setRecursively() {

            Integer value = 0;
            if(rawValue.startsWith("=")) {
                String[] split = rawValue.substring(1).split("\\+");
                for(String sv : split) {
                    if(sv.matches("[A-Z][0-9]*+")) {
                        Cell cell = sheetMap.get(sv);
                        value = value + Integer.parseInt(cell.value); // DFS
                    }
                    else {
                        value = value + Integer.parseInt(sv);
                    }
                }
            }
            else {
                value = value + Integer.parseInt(rawValue);
            }
            return String.valueOf(value);
        }

        private void notifyObservers() {
            for(Cell c : observers) {
                System.out.println("notifying from "+ this.colName + " to "+ c.colName);
                c.evaluateValue();
            }
        }
    }
}
public class MyExcel2 {
    public static void main(String[] args) {

        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "2");
        System.out.println("A => " +sheet.get("A"));
        System.out.println("B => " +sheet.get("B"));
        sheet.set("C", "=1+A");
        System.out.println("C => " +sheet.get("C"));
        sheet.set("D", "=C+B");
        System.out.println("D => " +sheet.get("D"));
        sheet.set("C", "=5+A");
        System.out.println("C => " +sheet.get("C"));
        System.out.println("D => " +sheet.get("D"));

        sheet.set("E", "=D+C");
        sheet.set("F", "=E");
        System.out.println("E => " +sheet.get("E"));
        System.out.println("F => " +sheet.get("F"));
        System.out.println("resetting C");
        sheet.set("C", "=10+B");
        System.out.println("C => " +sheet.get("C"));
        System.out.println("D => " +sheet.get("D"));
        System.out.println("E => " +sheet.get("E"));
        System.out.println("F => " +sheet.get("F"));

        sheet.toString();
    }
}
