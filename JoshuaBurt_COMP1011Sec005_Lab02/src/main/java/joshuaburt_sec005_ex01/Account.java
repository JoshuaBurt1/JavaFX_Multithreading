package joshuaburt_sec005_ex01;

//Exercise01
public class Account { //Exercise01: a) Create an Account class and implement both deposit and withdraw operations.

    private int total;
    public Account(int total) { //Constructor
        this.total = total; //amount of account
    }

    public synchronized void deposit(int transactionAmount, int transactionNum) { //Exercise01: b) Synchronize the operations to allow thread synchronization. (Chapter 23.8: public synchronized void)
        total += transactionAmount;
        System.out.printf("Transaction number: %s: $%s deposited; total: $%s%n", transactionNum, transactionAmount, total);
    }

    public synchronized void withdraw(int transactionAmount, int transactionNum) { //Exercise01: b) Synchronize the operations to allow thread synchronization. (Chapter 23.8: public synchronized void)
            total -= transactionAmount;
            System.out.printf("Transaction number: %s: $%s withdrawn; total: $%s%n", transactionNum, transactionAmount, total);

    }
}
