package reipplingpractise;

import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
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

class Transaction extends SimpleKVStore{
    Set<String> deletedKeys = new HashSet<>();
    IKVStore2 kvStore;

    public Transaction(){
        super();
    }
    public Transaction(Map<String, String> map) {
        super(map);
    }

    @Override
    public String delete(String key) {
        deletedKeys.add(key);
        return super.delete(key);
    }
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

class TransactionalKVStore2 implements ITransactionalKVStore {

    IKVStore2 globalTxnStore = new SimpleKVStore();
    Stack<Transaction> stack = new Stack<>();
    public TransactionalKVStore2(){
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
            Transaction transaction = new Transaction(globalTxnStore.getInternalMapCopy());
            stack.push(transaction);
        }
        else {
            stack.push(new Transaction(stack.peek().getInternalMapCopy()));
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

        Transaction pop = stack.pop();
        IKVStore2 nextTxn = stack.isEmpty() ? null : stack.pop();
        for (Map.Entry<String, String> entry : pop.getAllEntries()) {
            globalTxnStore.set(entry.getKey(), entry.getValue());
            if (nextTxn != null) {
                nextTxn.set(entry.getKey(), entry.getValue());
            }
        }
        for(String key : pop.deletedKeys) { // apply deletes from internal txn to global store
            globalTxnStore.delete(key);
            if (nextTxn != null) {
                nextTxn.delete(key);
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
    public synchronized void set(String key, String value) {
        /*Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();*/
            super.set(key, value);
        /*}finally {
            writeLock.unlock();
        }*/
    }

    @Override
    public synchronized String delete(String key) {
        String deletedValue = null;

/*
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
*/
            deletedValue = super.delete(key);
/*
        }finally {
            writeLock.unlock();
        }
*/
        return deletedValue;
    }
}

class ConcurrentTxnStore extends TransactionalKVStore2 {

    public ConcurrentTxnStore(){
        super();
    }
    @Override
    public synchronized void set(String key, String value) {
        super.set(key, value);
    }

    @Override
    public synchronized void begin() {
        super.begin();
    }

    @Override
    public synchronized void commit() {
        System.out.println(Thread.currentThread().getName());
        super.commit();
    }

    @Override
    public synchronized String delete(String key) {
        return super.delete(key);
    }

    @Override
    public synchronized void deleteAll() {
        super.deleteAll();
    }
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

        //testConcurrentSimple();
        //testConcurrentTxnStore();
        testTxnStore2();
    }

    private static void testTxnStore2() {
        TransactionalKVStore2 transactionalKVStore2 = new TransactionalKVStore2();
        transactionalKVStore2.set("1", "one");
        transactionalKVStore2.set("2", "two");
        transactionalKVStore2.begin();
        transactionalKVStore2.set("3", "three");
        transactionalKVStore2.set("2","22");
        transactionalKVStore2.delete("1");
        transactionalKVStore2.commit();
        System.out.println(transactionalKVStore2.get("2"));
        System.out.println(transactionalKVStore2.get("3"));
        System.out.println(transactionalKVStore2.get("1"));
    }

    private static void testConcurrentTxnStore(){

        final ConcurrentTxnStore concurrentTxnStore = new ConcurrentTxnStore();

        new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentTxnStore.set("1", "one");
                concurrentTxnStore.set("2", "two");
                // transactionalKVStore.commit();
                concurrentTxnStore.begin();
                concurrentTxnStore.delete("1");
                concurrentTxnStore.set("3", "three");
                concurrentTxnStore.commit();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(transactionalKVStore.get("1"));
                System.out.println(concurrentTxnStore.get("2"));
                System.out.println(concurrentTxnStore.get("3"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                concurrentTxnStore.set("1", "one1");
                concurrentTxnStore.set("2", "two1");
                // transactionalKVStore.commit();
                concurrentTxnStore.begin();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                concurrentTxnStore.delete("1");
                concurrentTxnStore.delete("2");
                concurrentTxnStore.set("3", "three1");
                concurrentTxnStore.commit();
                //System.out.println(transactionalKVStore.get("1"));
                //System.out.println(concurrentTxnStore.get("2"));
                System.out.println(concurrentTxnStore.get("3"));
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(concurrentTxnStore.get("2"));
        System.out.println(concurrentTxnStore.get("3"));
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
