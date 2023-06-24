package rippling;

import org.junit.Assert;
import org.junit.Test;

public class MyRedisTests {

    @Test
    public void testPut() {
        IKVStore kvStore = new MyKVSTore();
        kvStore.set("1", "one");
        Assert.assertEquals("one", kvStore.get("1"));
    }

    @Test
    public void testPutOverride() {
        IKVStore kvStore = new MyKVSTore();
        kvStore.set("1", "one");
        Assert.assertEquals("one", kvStore.get("1"));
        kvStore.set("1", "one1");
        Assert.assertEquals("one1", kvStore.get("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExistentKey() {
        IKVStore kvStore = new MyKVSTore();
        kvStore.get("1");
    }

    @Test
    public void testTxnPutGet() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        Assert.assertEquals("one", transactionalKVStore.get("1"));
    }

    @Test
    public void testTxnPutGetSingleTxnCommit() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.begin();
        transactionalKVStore.set("2", "two");
        transactionalKVStore.commit();
        Assert.assertEquals("one", transactionalKVStore.get("1"));
        Assert.assertEquals("two", transactionalKVStore.get("2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTxnPutGetSingleTxnRollback() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.begin();
        transactionalKVStore.set("2", "two");
        transactionalKVStore.rollback();
        transactionalKVStore.end();
        Assert.assertEquals("one", transactionalKVStore.get("1"));
        Assert.assertNull(transactionalKVStore.get("2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTxnPutGetSingleTxnBeginEnd() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.begin();
        transactionalKVStore.set("2", "two");
        transactionalKVStore.end();
        Assert.assertEquals("one", transactionalKVStore.get("1"));
        Assert.assertEquals("two", transactionalKVStore.get("2"));
    }

    @Test
    public void testTxnPutGetSingleInnerBeginCommit() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.begin();
        transactionalKVStore.set("2", "two");
        transactionalKVStore.begin();
        transactionalKVStore.set("3", "three");
        transactionalKVStore.commit();
        transactionalKVStore.commit();
        Assert.assertEquals("one", transactionalKVStore.get("1"));
        Assert.assertEquals("two", transactionalKVStore.get("2"));
        Assert.assertEquals("three", transactionalKVStore.get("3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTxnPutGetSingleInnerBeginCommitOuterRollback() {
        ITransactionalKVStore transactionalKVStore = new TransactionalKVStore();
        transactionalKVStore.set("1", "one");
        transactionalKVStore.begin();
        transactionalKVStore.set("2", "two");
        transactionalKVStore.begin();
        transactionalKVStore.set("3", "three");
        transactionalKVStore.commit();
        transactionalKVStore.rollback();
        transactionalKVStore.end();
        Assert.assertEquals("one", transactionalKVStore.get("1"));
        Assert.assertEquals("two", transactionalKVStore.get("2"));
        Assert.assertEquals("three", transactionalKVStore.get("3"));
    }
}
