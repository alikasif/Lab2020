package rippling;

import java.util.*;

class Sheet {

    Map<String, Cell> cellMap = new HashMap<>();

    public Sheet() {
    }

    public void set(String cellName, String cellValue) {
        Cell cell = null;
        if(cellMap.containsKey(cellName)) {
            cell = cellMap.get(cellName);
            cell.setRawValue(cellValue);
        }
        else {
            cell = new Cell(cellName, cellValue);
            cellMap.put(cellName, cell);
            cell.setRawValue(cellValue);
        }
        cell.evaluateValue();
        cell.notifyObservers();
    }

    public String get(String cellName) {
        if(!cellMap.containsKey(cellName)) {
            throw  new RuntimeException("cell not set");
        }
        else {
            Cell cell = cellMap.get(cellName);
            return cell.value;
        }
    }

    @Override
    public String toString() {
        for(Map.Entry<String, Cell> entry : cellMap.entrySet()) {
            System.out.println(entry.getValue());
        }
        return "";
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
                    ", Value='" + value + '\'' +
                    ", rawValue='" + rawValue + '\'' +
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
                    if(s.matches("[A-Z]+")) {
                        if(cellMap.containsKey(s))
                            cellMap.get(s).observers.add(this); // C = 1 + A ==> add C in observer list of A
                        else
                            throw new RuntimeException("cell not found "+ s);
                    }
                }
            }
            // System.out.println(this .colName + " observer: "+ observers);
        }

        public String evaluateValue() {
            value = setRecursively();
            return value;
        }

        private String setRecursively() {

            Integer value = 0;
            if(rawValue.startsWith("=")) {
                String[] split = rawValue.substring(1).split("\\+");
                for(String sv : split) {
                    if(sv.matches("[A-Z]+")) {
                        Cell cell = cellMap.get(sv);
                        value = value + Integer.parseInt(cell.evaluateValue()); // DFS
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

        public void notifyObservers() {
            for(Cell c : observers) {
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
        sheet.set("C", "=1+A");
        System.out.println("C => " +sheet.get("C"));
        sheet.set("D", "=C+B");
        System.out.println("D => " +sheet.get("D"));
        sheet.set("C", "=5+A");
        System.out.println("C => " +sheet.get("C"));
        System.out.println("D => " +sheet.get("D"));

        sheet.set("E", "=D+C");
        System.out.println("E => " +sheet.get("E"));
        sheet.set("C", "=10+B");
        System.out.println("C => " +sheet.get("C"));
        System.out.println("E => " +sheet.get("E"));
        System.out.println("D => " +sheet.get("D"));

        sheet.toString();
    }
}
