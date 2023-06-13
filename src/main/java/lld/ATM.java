package lld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum TransactionType {
    BALANCE_INQUIRY, DEPOSIT_CASH, DEPOSIT_CHECK, WITHDRAW, TRANSFER
}

enum TransactionStatus {
    SUCCESS, FAILURE, BLOCKED, FULL, PARTIAL, NONE
}

enum CustomerStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, CLOSED, UNKNOWN
}

class ATMAddress {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

class Customer {
    private String name;
    private String email;
    private String phone;
    private Address address;
    private CustomerStatus status;

    private DebitCard card;
    private Account account;

    public boolean makeTransaction(Transaction transaction) {
        return transaction.makeTransation();
    }
    public Address getBillingAddress() {return address;}
}

class DebitCard {
    private String cardNumber;
    private String customerName;
    private Date cardExpiry;
    private int pin;

    public Address getBillingAddress() {
        return null;
    }
}

class BankAccount {
    private int accountNumber;
    private double totalBalance;
    private double availableBalance;

    public double getAvailableBalance() {return 1.0;}
}

class SavingAccount extends BankAccount {
    private double withdrawLimit;
}

class CheckingAccount extends BankAccount {
    private String debitCardNumber;
}

class Bank {
    private String name;
    private String bankCode;

    List<ATM> atms = new ArrayList<>();

    public String getBankCode() {return "hello"; }
    public boolean addATM(ATM atm) {
        atms.add(atm); return true;
    }
}

public class ATM {
    private int atmID;
    private Address location;

    private CashDispenser cashDispenser;
    private Keypad keypad;
    private Screen screen;
    private Printer printer;
    private CheckDeposit checkDeposit;
    private CashDeposit cashDeposit;

    public boolean authenticateUser() {
        return true;
    }
    public boolean makeTransaction(Customer customer, Transaction transaction) { return transaction.makeTransation();}
}

class CashDispenser {
    private int totalFiveDollarBills;
    private int totalTwentyDollarBills;

    public boolean dispenseCash(double amount) { return true;}
    public boolean canDispenseCash() {return true;}
}

class Keypad {
    public String getInput() {return "";}
}

class Screen {
    public boolean showMessage(String message) {
        return true;
    }
    public TransactionType getInput() {
        return TransactionType.BALANCE_INQUIRY;
    }
}

class Printer {
    public boolean printReceipt(Transaction transaction) {
        return true;
    }
}

abstract class DepositSlot {
    private double totalAmount;
    public double getTotalAmount() {
        return 100.0;
    }
}

class CheckDepositSlot extends DepositSlot {
    public double getCheckAmount() {
        return 10.0;
    }
}

class CashDepositSlot extends DepositSlot {
    public double receiveDollarBill() {
        return 10.0;
    }
}

abstract class Transaction {
    private int transactionId;
    private Date creationTime;
    private TransactionStatus status;
    public abstract boolean makeTransation();
}

class BalanceInquiry extends Transaction {
    private int accountId;
    public double getAccountId() {
        return 1.2;
    }

    @Override
    public boolean makeTransation() {
        return false;
    }
}

abstract class Deposit extends Transaction {
    private double amount;
    public abstract double getAmount();
}

class CheckDeposit extends Deposit {
    private String checkNumber;
    private String bankCode;

    public String getCheckNumber() {
        return "check##";
    }

    @Override
    public boolean makeTransation() {
        return false;
    }

    @Override
    public double getAmount() {
        return 0;
    }
}

class CashDeposit extends Deposit {
    private double cashDepositLimit;

    @Override
    public boolean makeTransation() {
        return false;
    }

    @Override
    public double getAmount() {
        return 0;
    }
}

class Withdraw extends Transaction {
    private double amount;
    public double getAmount() {
        return 1.2;
    }

    @Override
    public boolean makeTransation() {
        return false;
    }
}

class Transfer extends Transaction {
    private int destinationAccountNumber;
    public int getDestinationAccount() {
        return 12;
    }

    @Override
    public boolean makeTransation() {
        return false;
    }
}

