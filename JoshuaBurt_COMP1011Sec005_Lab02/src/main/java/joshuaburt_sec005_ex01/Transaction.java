package joshuaburt_sec005_ex01;

//Exercise01
public class Transaction implements Runnable { //Exercise1: c) Use Java Runnable interface to implement a Transaction class. (Chapter 23.6: ArrayWriter, Consumer, PrintTask, Producer)
    private int transactionType;
    private Account account;
    private int transactionAmount;
    private int transactionNum;

    public Transaction(int transactionType, Account account, int transactionAmount, int transactionNum) { //Constructor
        this.transactionType = transactionType; //used to activate either deposit or withdraw method; Account.java
        this.account = account; //uses Account class; Account.java (contains deposit & withdraw methods)
        this.transactionAmount = transactionAmount; //amount added (deposit) or subtracted (withdrawn)
        this.transactionNum = transactionNum;
    }

    @Override
    public void run() { // d) Perform withdraw and deposit operations in run method.
        if (transactionType==1) {
            account.deposit(transactionAmount, transactionNum);
        } else {
            account.withdraw(transactionAmount, transactionNum);
        }
    }
}
