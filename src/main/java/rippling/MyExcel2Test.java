package rippling;

import org.junit.Assert;
import org.junit.Test;

public class MyExcel2Test {

    @Test
    public void testNativeGetSet() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "2");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
    }

    @Test
    public void testReferenceGetSet() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("1", sheet.get("B"));
    }

    @Test
    public void testTwoReferenceGetSet() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
    }

    @Test
    public void testThreeReferenceGetSet() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        sheet.set("D", "=C+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("5", sheet.get("D"));
    }

    @Test
    public void testFourReferenceGetSet() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        sheet.set("D", "=A+C+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("6", sheet.get("D"));
    }

    @Test
    public void testObserver() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        sheet.set("D", "=A+C+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("6", sheet.get("D"));

        sheet.set("A", "2");
        Assert.assertEquals("3", sheet.get("B"));
        Assert.assertEquals("4", sheet.get("C"));
        Assert.assertEquals("9", sheet.get("D"));
    }

    @Test(expected = RuntimeException.class)
    public void testReferenceNonExistentColumn() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=D+1");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
    }

    @Test
    public void testRemoveReferenceObserver() {
        Sheet sheet = new Sheet();
        sheet.set("A", "1");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        sheet.set("D", "=A+C+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("6", sheet.get("D"));

        sheet.set("B", "2");
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("6", sheet.get("D"));

        sheet.set("A", "2");
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
        Assert.assertEquals("7", sheet.get("D"));
    }

    @Test(expected = RuntimeException.class)
    public void testCyclicReference() {
        Sheet sheet = new Sheet();
        sheet.set("A", "=1+C");
        sheet.set("B", "=A+1");
        sheet.set("C", "=1+B");
        Assert.assertEquals("1", sheet.get("A"));
        Assert.assertEquals("2", sheet.get("B"));
        Assert.assertEquals("3", sheet.get("C"));
    }

    @Test
    public void testRow() {
        Sheet sheet = new Sheet();
        sheet.setValue("A1", "1");
        sheet.setValue("B1", "=A1+1");
        sheet.setValue("C1", "=1+B1");
        Assert.assertEquals("1", sheet.getValue("A1"));
        Assert.assertEquals("2", sheet.getValue("B1"));
        Assert.assertEquals("3", sheet.getValue("C1"));
    }

}
