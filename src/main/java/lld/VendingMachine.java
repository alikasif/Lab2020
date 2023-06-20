package lld;

import java.util.Map;
import java.util.Set;

interface State {
    public void collectCash(int cash);
    public void dispenseChange(String productCode);
    public void dispenseItem(String productCode);
    public void cancelTransaction();
}

class DispenseChange implements State {
    private VendingMachine vendingMachine;

    public DispenseChange(VendingMachine vmch) {
        this.vendingMachine = vmch;
    }

    @Override
    public void collectCash(int cash) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseChange(String productCode) {
        this.vendingMachine.calculateChange(productCode);
        this.vendingMachine.setState(new DispenseItem(vendingMachine));
    }

    @Override
    public void dispenseItem(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void cancelTransaction() {
        throw new IllegalStateException("wrong action");
    }
}

class DispenseItem implements State {
    private VendingMachine vendingMachine;

    public DispenseItem(VendingMachine vmch) {
        this.vendingMachine = vmch;
    }

    @Override
    public void collectCash(int cash) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseItem(String productCode) {
        vendingMachine.dispenseItem(productCode);
        vendingMachine.removeProduct(productCode);
        vendingMachine.setState(new ReadyState(vendingMachine));
    }

    @Override
    public void cancelTransaction() {
        throw new IllegalStateException("wrong action");
    }
}

class ReadyState implements State {
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vmch) {
        this.vendingMachine = vmch;
    }

    @Override
    public void collectCash(int cash) {
        vendingMachine.addCollectedCash(cash);
        vendingMachine.setState(new DispenseChange(vendingMachine));
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseItem(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void cancelTransaction() {
        throw new IllegalStateException("wrong action");
    }
}

class TransactionCancelledState implements State {

    VendingMachine vmch;

    public TransactionCancelledState(VendingMachine vmch) {
        this.vmch = vmch;
    }

    @Override
    public void collectCash(int cash) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseChange(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void dispenseItem(String productCode) {
        throw new IllegalStateException("wrong action");
    }

    @Override
    public void cancelTransaction() {
        vmch.setCollectedCash(0);
        vmch.setState(new ReadyState(vmch));
    }
}

public class VendingMachine {

    private int collectedCash;
    private State state;
    private Map<String, Set<String>> productCodeItemMap;
    private Map<String, Integer> productCodePriceMap;

    public void addCollectedCash(int collectedCash) {
        this.collectedCash += collectedCash;
    }

    public VendingMachine setCollectedCash(int collectedCash) {
        this.collectedCash = collectedCash;
        return this;
    }

    public State getState() {
        return state;
    }

    public VendingMachine setState(State state) {
        this.state = state;
        return this;
    }

    public void removeProduct(String productCode) {
    }

    public void dispenseChange(String productCode) {
        this.state.dispenseChange(productCode);
    }

    public void cancelTransaction() {
        this.state.cancelTransaction();
    }

    public int calculateChange(String productCode) {
        return collectedCash - productCodePriceMap.get(productCode);
    }

    public void dispenseItem(String productCode) {
        this.state.dispenseItem(productCode);
    }

    public int getCollectedCash() {
        return collectedCash;
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setState(new ReadyState(vendingMachine));
    }
}