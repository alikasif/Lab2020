package rippling;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

interface IKVStore {
    void set(String key, String value);
    String get(String key);
    String delete(String key);
    Set<Map.Entry<String, String>> getAllKVEntries();
    void clearAllKeys();
}

interface ITransactionalKVStore extends IKVStore {
    void begin();
    void end();
    void commit();
    void rollback();
}

class TransactionalKVStore implements ITransactionalKVStore {

    private Stack<MyKVSTore> stack = null;

    private MyKVSTore kvStore = null;

    public TransactionalKVStore() {
        stack = new Stack<>();
        kvStore = new MyKVSTore();
    }

    @Override
    public void set(String key, String value) {
        if(stack.isEmpty()) {
            kvStore.set(key, value);
            return;
        }
        MyKVSTore currentTxn = stack.peek();
        currentTxn.set(key, value);
    }

    @Override
    public String get(String key) {
        if(stack.isEmpty()) {
            return kvStore.get(key);
        }
        MyKVSTore currentTxn = stack.peek();
        return currentTxn.get(key);
    }

    @Override
    public String delete(String key) {
        if(stack.isEmpty()) {
            return kvStore.delete(key);
        }
        MyKVSTore currentTxn = stack.peek();
        return currentTxn.delete(key);
    }

    @Override
    public Set<Map.Entry<String, String>> getAllKVEntries() {
        throw new RuntimeException("not implemented getAllKVEntries()");
    }

    @Override
    public void clearAllKeys() {
        throw new RuntimeException("not implemented clearAllKeys()" );
    }

    @Override
    public void begin() {
        MyKVSTore newTxn = new MyKVSTore();
        stack.push(newTxn);
    }

    @Override
    public void end() {
        stack.pop();
    }

    @Override
    public void commit() {

        if(stack.isEmpty()){
            System.out.println("No active Txn");
            return;
        }

        MyKVSTore currentTxn = stack.pop();
        MyKVSTore nextActiveTxn = stack.isEmpty() ? null : stack.peek();
        for(Map.Entry<String, String> entry : currentTxn.getAllKVEntries()) {
            kvStore.set(entry.getKey(), entry.getValue());
            if(nextActiveTxn != null) {
                nextActiveTxn.set(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void rollback() {
        if(stack.isEmpty()){
            System.out.println("No active Txn");
            return;
        }
        MyKVSTore currentTxn = stack.peek();
        currentTxn.clearAllKeys();
    }
}

class ConcurrentTxnKVStore extends TransactionalKVStore {

    private ReadWriteLock readWriteLock;

    public ConcurrentTxnKVStore() {
        super();
        readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void commit() {
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            super.commit();
        }
        finally {
            writeLock.unlock();
        }
    }
}

class MyKVSTore implements IKVStore {

    private Map<String, String> kvStore = new HashMap<>();

    public MyKVSTore() {

    }

    public void set(String key, String value) {
        kvStore.put(key, value);
    }

    public String get(String key) {
        if(kvStore.containsKey(key))
            return kvStore.get(key);

        throw new IllegalArgumentException("key not found " + key);
    }

    public String delete(String key) {
        if(kvStore.containsKey(key))
            return kvStore.remove(key);

        throw new IllegalArgumentException("key not found " + key);
    }

    public Set<Map.Entry<String, String>> getAllKVEntries() {
        return kvStore.entrySet();
    }

    @Override
    public void clearAllKeys() {
        kvStore.clear();
    }
}

public class MyRedis {

    public static void main(String[] args) {
        /*IKVStore kvStore = new MyKVSTore();
        kvStore.set("1", "one");
        kvStore.set("2", "two");
        System.out.println(kvStore.get("1"));*/

        ITransactionalKVStore transactionalKVStore = new ConcurrentTxnKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.set("2", "two");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.end();
        System.out.println(transactionalKVStore.get("1"));

        System.out.println("new txn");
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));

        System.out.println("new txn");
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "11");
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "21");
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));

    }
}