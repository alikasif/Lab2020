package reipplingpractise;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

interface IKVStore2 {

    void set(String key, String value);
    String get(String key);
    String delete(String key);

    void deleteAll();

    Set<Map.Entry<String, String>> getAllEntries();
}

class SimpleKVStore implements IKVStore2 {
    Map<String, String> map = new HashMap<>();

    public SimpleKVStore() {
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

    IKVStore2 kvStore2 = new SimpleKVStore();
    Stack<IKVStore2> stack = new Stack<>();
    public TransactionalKVStore(){
    }

    @Override
    public void set(String key, String value) {
        if(stack.isEmpty())
            kvStore2.set(key, value);
        else {
            IKVStore2 peek = stack.peek();
            peek.set(key,value);
        }
    }

    @Override
    public String get(String key) {
        if(stack.isEmpty())
            return kvStore2.get(key);
        else {
            IKVStore2 peek = stack.peek();
            return peek.get(key);
        }
    }

    @Override
    public String delete(String key) {
        if(stack.isEmpty())
            return kvStore2.delete(key);
        else {
            IKVStore2 peek = stack.peek();
            return peek.delete(key);
        }
    }

    @Override
    public void deleteAll() {
        if(stack.isEmpty())
            kvStore2.deleteAll();
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
    public void begin() {
        IKVStore2 txnKVStore = new SimpleKVStore();
        stack.push(txnKVStore);
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
        if(stack.isEmpty())
            throw new IllegalStateException("no active txn ");
        IKVStore2 pop = stack.pop();
        IKVStore2 nextTxn = stack.isEmpty()? null: stack.pop();
        for(Map.Entry<String, String> entry : pop.getAllEntries()) {
            kvStore2.set(entry.getKey(), entry.getValue());
            if(nextTxn != null) {
                nextTxn.set(entry.getKey(), entry.getValue());
            }
        }
    }
}
public class KVPractise2 {
    public static void main(String[] args) {

        SimpleKVStore simpleKVStore = new SimpleKVStore();
        simpleKVStore.set("1", "one");
        System.out.println(simpleKVStore.get("1"));
        // System.out.println(simpleKVStore.get("2"));

        TransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.set("2", "two");
        // transactionalKVStore.commit();
        transactionalKVStore.begin();
        transactionalKVStore.set("1", "one1");
        transactionalKVStore.commit();
        System.out.println(transactionalKVStore.get("1"));
    }
}
