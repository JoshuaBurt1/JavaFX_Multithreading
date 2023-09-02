//Exercise 03: This is not mandatory, but you will get 10 bonus marks for this if you attempt it correctly.
//Build the GUI, using JavaFx/Scenebuilder as shown below.
//Here it shows two functionalities, one for calculating the factorial of a large integer value asynchronously or in parallel to other functionality – Car Loan Calculator.
//Since calculating a factorial value for large int value may take some time. This task you need to start on a separate thread, and while it calculates factorial, user should be able to calculate the interest on a car loan.
//Note: how to handle large numeric values, you may find some pre-defined some structs or types to handle large values in Java.

package joshuaburt_sec005_ex03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AsynchronousAppController extends Application{
    //if the Fibonacci number takes too long to run, it still allows user to click button
    @FXML
    private TextField number;
    @FXML
    private Label lbl1;
    @FXML
    private TextField displayFactorial;
    @FXML
    private Button btnStart1;

    @FXML
    private TextField amount;
    @FXML
    private TextField rate;
    @FXML
    private TextField duration;
    @FXML
    private TextField displayOutput;
    @FXML
    private Button btnStart2;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AsynchronousAppController.class.getResource("asynchronousAppController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400); //Set application viewport size
        stage.setTitle("Asynchronous Programming"); // displayed in window's title bar
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    void calculateFactorial(/*ActionEvent event*/) { //factorial over 10000 will take some time
        try {
            int ival = Integer.parseInt(number.getText()); // Parse the integer value from the text field
            Factorial task = new Factorial(ival); // creates new FiboCalcTask object "task" using ival parameter
            ExecutorService executorService = Executors.newFixedThreadPool(1); //Create an ExecutorService with a fixed thread pool size of 1 [multithreading object]
            executorService.execute(task); // Execute the task using the ExecutorService, which runs it in a separate thread [allows FiboCalcTask calculation to run concurrently]
            executorService.shutdown(); // Shut down the ExecutorService when it's no longer needed

            lbl1.textProperty().bind(task.messageProperty()); // Bind the textProperty of lbl1 to the messageProperty of FiboCalcTask object "task"
            task.setOnRunning((calculatingEvent) -> { // When FiboCalcTask object "task" is calculating [time-consuming recursive FiboCalcTask.java]
                btnStart1.setDisable(true); // Disable "Start" button
                displayFactorial.setText(""); // Clear lbl2 text
            });

            task.setOnSucceeded((succeededEvent) -> { // When FiboCalcTask object "task" has succeeded
                displayFactorial.setText(task.getValue().toString()); // Set the text of lbl2 to the result value of the task
                btnStart1.setDisable(false); // Re-enable "Start" button
            });


        } catch (NumberFormatException e) { // Handle the case where the text in tf1 cannot be parsed as an integer
            number.setText("Enter a number");
            number.selectAll();
            number.requestFocus();
        }
    }
    /*
        int number1 = Integer.parseInt(number.getText());
        BigInteger factorial = BigInteger.ONE;

        for (int i = number1; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        displayFactorial.setText(String.format("Factorial: %s", factorial)); //rounds to 2 decimal places
    }*/

    @FXML
    void calculateInterest(/*ActionEvent event*/) {
        double price = Double.parseDouble(amount.getText());
        int length = Integer.parseInt(duration.getText());
        double interest = Double.parseDouble(rate.getText());
        double totalPayment = (price);
        for (int i = 0; i < length; i++) {
            totalPayment *= (1 + interest / 100); // totalPayment should be multiplied interest rate every year
        }
        double monthPayment = (totalPayment) / 12;
        displayOutput.setText(String.format("Total: $%.2f; Monthly: $%.2f", totalPayment, monthPayment)); //rounds to 2 decimal places
    }

}
