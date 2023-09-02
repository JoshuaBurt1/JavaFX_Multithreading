package joshuaburt_sec005_ex01;
//Exercise01

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//e) Create an AccountTest class to test multiple transactions (threads).
// Use an ArrayList to create a list of three or more Transaction objects.
// Use method execute of ExecutorService to execute the threads.
// Display the results.
public class AccountTest {
    //Executes runnable (Transaction class)
    //Similar to TaskExecutor, SharedArrayTest, SharedArrayTest2
    public static void main(String[] args) {
        Account account = new Account(0); //creates Account object
        Random randomVal = new Random(); //random number for: transactionType (1=deposit,0=withdraw) & transactionAmount (0-999)

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, account, 1000, 111));
        transactions.add(new Transaction(randomVal.nextInt(2), account, randomVal.nextInt(1000),222));
        transactions.add(new Transaction(randomVal.nextInt(2), account, randomVal.nextInt(1000),333));
        transactions.add(new Transaction(randomVal.nextInt(2), account, randomVal.nextInt(1000),444));
        transactions.add(new Transaction(randomVal.nextInt(2), account, randomVal.nextInt(1000),555));

        ExecutorService executorService = Executors.newCachedThreadPool();


        for(int i =0; i< transactions.size();i++){
            executorService.execute(transactions.get(i));
        }
        //        Alternative:
        //        for (Transaction transaction : transactions) {
        //            executorService.execute(transaction);
        //        }
        executorService.shutdown();
    }
}



