package reipplingpractise;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

interface IKVStore2 {

    void set(String key, String value);
    String get(String key);
    String delete(String key);
    void deleteAll();
    Set<Map.Entry<String, String>> getAllEntries();
    Map<String, String> getInternalMapCopy();
}

class SimpleKVStore implements IKVStore2 {
    Map<String, String> map = new HashMap<>();

    public SimpleKVStore() {
    }

    public SimpleKVStore(Map<String, String> kv) {
        map.putAll(kv);
    }

    public Map<String, String> getInternalMapCopy() {
        return new HashMap<>(map);
    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        if(!map.containsKey(key))
            throw new IllegalArgumentException("key not found " + key);
        return map.get(key);
    }

    @Override
    public String delete(String key) {
        if(!map.containsKey(key))
            throw new IllegalArgumentException("key not found " + key);
        return map.remove(key);
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public Set<Map.Entry<String, String>> getAllEntries() {
        return map.entrySet();
    }
}

interface ITransactionalKVStore extends IKVStore2 {
    void begin();
    void end();
    void rollback();
    void commit();
}

class TransactionalKVStore implements ITransactionalKVStore {

    IKVStore2 globalTxnStore = new SimpleKVStore();
    Stack<IKVStore2> stack = new Stack<>();

    boolean trulyTransaction = true;
    public TransactionalKVStore(){

    }

    @Override
    public void set(String key, String value) {
        if(stack.isEmpty())
            globalTxnStore.set(key, value);
        else {
            IKVStore2 peek = stack.peek();
            peek.set(key,value);
        }
    }

    @Override
    public String get(String key) {
        if(stack.isEmpty())
            return globalTxnStore.get(key);
        else {
            IKVStore2 peek = stack.peek();
            return peek.get(key);
        }
    }

    @Override
    public String delete(String key) {
        if(stack.isEmpty())
            return globalTxnStore.delete(key);
        else {
            IKVStore2 peek = stack.peek();
            return peek.delete(key);
        }
    }

    @Override
    public void deleteAll() {
        if(stack.isEmpty())
            globalTxnStore.deleteAll();
        else {
            IKVStore2 peek = stack.peek();
            peek.deleteAll();
        }
    }

    @Override
    public Set<Map.Entry<String, String>> getAllEntries() {
        throw new NotImplementedException("getAllEntries()");
    }

    @Override
    public Map<String, String> getInternalMapCopy() {
        throw  new NotImplementedException("getInternalMapCopy()");
    }

    @Override
    public void begin() {
        if(stack.isEmpty()) {
            IKVStore2 txnKVStore = new SimpleKVStore(globalTxnStore.getInternalMapCopy());
            stack.push(txnKVStore);
        }
        else {
            IKVStore2 peek = stack.peek();
            IKVStore2 txnKVStore = new SimpleKVStore(peek.getInternalMapCopy());
            stack.push(txnKVStore);
        }
    }

    @Override
    public void end() {
        if(stack.isEmpty())
            throw new IllegalStateException("no active txn ");
        stack.pop();
    }

    @Override
    public void rollback() {
        if(stack.isEmpty())
            throw new IllegalStateException("no active txn ");
        IKVStore2 peek = stack.peek();
        peek.deleteAll();
    }

    @Override
    public void commit() {

        if (stack.isEmpty())
            throw new IllegalStateException("no active txn ");

        if(trulyTransaction) {
            performCommit();
        }
        else {
            IKVStore2 pop = stack.pop();
            IKVStore2 nextTxn = stack.isEmpty() ? null : stack.pop();
            for (Map.Entry<String, String> entry : pop.getAllEntries()) {
                globalTxnStore.set(entry.getKey(), entry.getValue());
                if (nextTxn != null) {
                    nextTxn.set(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void performCommit() {
        IKVStore2 pop = stack.pop(); // current txn
        IKVStore2 nextTxn = stack.isEmpty() ? null : stack.pop();

        globalTxnStore.deleteAll();
        if (nextTxn != null)
            nextTxn.deleteAll();

        for (Map.Entry<String, String> entry : pop.getAllEntries()) {
            globalTxnStore.set(entry.getKey(), entry.getValue());
            if (nextTxn != null) {
                nextTxn.set(entry.getKey(), entry.getValue());
            }
        }
    }
}

class ConcurrentKVStore extends SimpleKVStore {
    ReadWriteLock readWriteLock;
    public ConcurrentKVStore() {
        readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void set(String key, String value) {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            super.set(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public String delete(String key) {
        Lock writeLock = readWriteLock.writeLock();
        String deletedValue = null;
        try {
            writeLock.lock();
            deletedValue = super.delete(key);
        }finally {
            writeLock.unlock();
        }
        return deletedValue;
    }
}

class ConcurrentTxnStore extends TransactionalKVStore {

}

public class KVPractise2 {
    public static void main(String[] args) {

        /*
        SimpleKVStore simpleKVStore = new SimpleKVStore();
        simpleKVStore.set("1", "one");
        System.out.println(simpleKVStore.get("1"));
        // System.out.println(simpleKVStore.get("2"));

        TransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.set("2", "two");
        // transactionalKVStore.commit();
        transactionalKVStore.begin();
        transactionalKVStore.delete("1");
        transactionalKVStore.set("3", "three");
        transactionalKVStore.commit();
        //System.out.println(transactionalKVStore.get("1"));
        System.out.println(transactionalKVStore.get("2"));
        System.out.println(transactionalKVStore.get("3"));
        */

        testConcurrentSimple();
    }

    private static void testConcurrentSimple() {

        final ConcurrentKVStore concurrentKVStore = new ConcurrentKVStore();

        new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentKVStore.set("1", "one");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentKVStore.set("1", "one1");
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(concurrentKVStore.get("1"));
    }
}
