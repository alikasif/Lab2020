package rippling;

import java.util.*;

class Row {
    int rowId;
    Cell[] cells = new Cell[26];
}

class Cell {

    String colIndex;
    String value;
    String rawValue;
    List<String> observers;

    public Cell(String index, String value) {
        this.colIndex = index;
        this.value = value;
        observers = new ArrayList<>();
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "index='" + colIndex + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public void setObserver(String index) {
        observers.add(index);
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;

        // it is referring to some other cell. set this cell as observer in referred cell observer list
        if(rawValue.startsWith("=")) {
            String tmp = rawValue.substring(1);
            String[] split = tmp.split("\\+");
            for(String s : split) {
                if(s.matches("[A-Z]+")) {
                    ;
                }
            }
        }
    }
}

class SpreadSheet {

    Map<String, Cell> sheet;
    int value;
    boolean isRecursive = false;

    public SpreadSheet() {
        sheet = new HashMap<>();
    }

    public void set(String index, String value) {
        sheet.put(index, new Cell(index, value));
    }

    public String get(String index) {
        Cell cell = sheet.get(index);
        if(cell != null) {
            if(cell.value.startsWith("=")) {
                isRecursive = false;
                value = 0;
                getRecursively(index, new HashSet<>());
                return String.valueOf(value);
            }
            else {
                return cell.value;
            }
        }
        return null;
    }

    private void getRecursively(String index, Set<String> visited) {

        Cell cell = null;

        if(sheet.containsKey(index)) {

            cell = sheet.get(index);

            if(cell.value.startsWith("=")) {
                String subString = cell.value.substring(1);
                String [] split = subString.split("\\+");
                for(String c : split) {
                    if(!visited.contains(c)) {
                        if(!Character.isDigit(c.charAt(0))) {
                            visited.add(c);
                        }
                        if(Character.isDigit(c.charAt(0))) {
                            value = (value + Integer.valueOf(c));
                        }
                        else {
                            getRecursively(c, visited);
                        }
                    }
                    else {
                        isRecursive = true;
                    }
                }
            }
            else {
                value = value + Integer.valueOf(cell.value);
            }
        }
        else {
            throw new RuntimeException("column not found : " + index);
        }

        if(isRecursive) {
            value = -1;
        }
    }
}

public class MyExcel {
    public static void main(String[] args) {

        SpreadSheet spreadSheet = new SpreadSheet();
        spreadSheet.set("A1", "1");
        spreadSheet.set("A2", "-2");
        System.out.println(spreadSheet.get("A1"));
        System.out.println(spreadSheet.get("A2"));
        System.out.println(spreadSheet.get("A3"));

        spreadSheet.set("A3", "=1+2");
        System.out.println(spreadSheet.get("A3"));

        spreadSheet.set("A4", "=1+A2");
        System.out.println(spreadSheet.get("A4"));

        spreadSheet.set("A5", "=A3+A4");
        System.out.println(spreadSheet.get("A5"));

        spreadSheet.set("A6", "=A3+A4");
        System.out.println(spreadSheet.get("A5"));

        spreadSheet.set("A7", "=A11+A12");
        System.out.println(spreadSheet.get("A7"));
    }
}
