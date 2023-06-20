package rippling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Transaction {
    private Map<String, String> store;
    private Transaction next;

    public Transaction() {
        store = new HashMap<>();
        next = null;
    }

    public Map<String, String> getStore() {
        return store;
    }

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }
}

class TransactionStack  {
    private Transaction top;
    private int size;

    private Map<String, String> GlobalStore;

    public TransactionStack(Map<String, String> globalStore) {
        top = null;
        size = 0;
        this.GlobalStore = globalStore;
    }

    public void pushTransaction() {
        Transaction temp = new Transaction();
        temp.setNext(top);
        top = temp;
        size++;
    }

    public void popTransaction() {
        if (top == null) {
            System.out.println("ERROR: No Active Transactions");
        } else {
            top = top.getNext();
            size--;
        }
    }

    public Transaction peek() {
        return top;
    }

    public void rollBackTransaction() {
        if (top == null) {
            System.out.println("ERROR: No Active Transaction");
        } else {
            top.getStore().clear();
        }
    }

    public void commit() {
        Transaction activeTransaction = peek();
        if (activeTransaction != null) {
            for (Map.Entry<String, String> entry : activeTransaction.getStore().entrySet()) {
                GlobalStore.put(entry.getKey(), entry.getValue());
                if (activeTransaction.getNext() != null) {
                    activeTransaction.getNext().getStore().put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            System.out.println("INFO: Nothing to commit");
        }
    }
}

public class MemoryKVStore {
    private static Map<String, String> GlobalStore = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TransactionStack txnStack = new TransactionStack(GlobalStore);

        while (true) {
            System.out.print("> ");
            String text = reader.readLine();
            String[] operation = text.split(" ");

            switch (operation[0]) {
                case "BEGIN":
                    txnStack.pushTransaction();
                    break;
                case "ROLLBACK":
                    txnStack.rollBackTransaction();
                    break;
                case "COMMIT":
                    txnStack.commit();
                    txnStack.popTransaction();
                    break;
                case "END":
                    txnStack.popTransaction();
                    break;
                case "SET":
                    set(operation[1], operation[2], txnStack);
                    break;
                case "GET":
                    get(operation[1], txnStack);
                    break;
                case "DELETE":
                    delete(operation[1], txnStack);
                    break;
                case "COUNT":
                    count(operation[1], txnStack);
                    break;
                case "STOP":
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR: Unrecognised Operation " + operation[0]);
                    break;
            }
        }
    }

    private static void set(String key, String value, TransactionStack items) {
        Transaction activeTransaction = items.peek();
        if (activeTransaction == null) {
            GlobalStore.put(key, value);
        } else {
            activeTransaction.getStore().put(key, value);
        }
    }

    private static void get(String key, TransactionStack items) {
        Transaction activeTransaction = items.peek();
        if (activeTransaction == null) {
            if (GlobalStore.containsKey(key)) {
                System.out.println(GlobalStore.get(key));
            } else {
                System.out.println(key + " not set");
            }
        } else {
            if (activeTransaction.getStore().containsKey(key)) {
                System.out.println(activeTransaction.getStore().get(key));
            } else {
                System.out.println(key + " not set");
            }
        }
    }

    private static void delete(String key, TransactionStack items) {
        Transaction activeTransaction = items.peek();
        if (activeTransaction == null) {
            GlobalStore.remove(key);
        } else {
            activeTransaction.getStore().remove(key);
        }
        System.out.println(key + " deleted");
    }

    private static void count(String value, TransactionStack items) {
        int count = 0;
        Transaction activeTransaction = items.peek();
        if (activeTransaction == null) {
            for (String storedValue : GlobalStore.values()) {
                if (storedValue.equals(value)) {
                    count++;
                }
            }
        } else {
            for (String storedValue : activeTransaction.getStore().values()) {
                if (storedValue.equals(value)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}