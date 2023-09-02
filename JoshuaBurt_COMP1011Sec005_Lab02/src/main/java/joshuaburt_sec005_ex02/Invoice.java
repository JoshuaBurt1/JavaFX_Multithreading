package joshuaburt_sec005_ex02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

//Exercise02
public class Invoice {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double pricePerItem;

    //Class Invoice includes four instance variables
    // – partNumber (type String), part description (type String),
    // a quantity of the item being purchased (type int) and
    // ;pricePerItem (type double) and corresponding get methods.

    public Invoice(String partNumber, String partDescription, int quantity, double pricePerItem) { //Constructor
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public double getValue() {
        double value = Double.valueOf(quantity)*pricePerItem;
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %8.2f",
                getPartNumber(), getPartDescription(), getQuantity(), getPricePerItem());
    }
    static Invoice[] invoices = {
            new Invoice("87", "Electric Sander", 7, 57.98),
            new Invoice("24", "Power Saw", 18, 99.99),
            new Invoice("7", "Sledge Hammer", 11, 21.50),
            new Invoice("77", "Hammer", 76, 11.99),
            new Invoice("39", "Lawn Mower", 3, 79.50),
            new Invoice("68", "Screw Driver", 106, 6.99),
            new Invoice("56", "Jig Saw", 21, 11.00)
    };
    public static void main(String[] args) {
        //Perform the following queries on the array of Invoice objects and display the results:
        //a) Use streams to sort the Invoice objects by partDescsription, then display the results. [3 marks] (Chapter 17 : 11 --> ArraysAndStreams; ProcessingEmployees.java)
        List<Invoice> list = Arrays.asList(invoices);
        System.out.println("Invoices sorted by partDescription:");
        list.stream().sorted(Comparator.comparing(Invoice::getPartDescription)).forEach(System.out::println); //sorts by alphabetical order
        System.out.println();

        //b) Use streams to sort the Invoice objects by pricePerItem, then display the results. [3 marks]
        System.out.println("Invoices sorted by pricePerItem:");
        list.stream().sorted(Comparator.comparing(Invoice::getPricePerItem)).forEach(System.out::println); //sorts by alphabetical order
        System.out.println();

        //c) Use streams to map each Invoice to its partDescription and quantity, sort the results by quantity, then display the results. [4 marks]
        System.out.println("Invoices sorted by quantity:");
        list.stream()
                .sorted(Comparator.comparingInt(Invoice::getQuantity))
                .map(invoice -> String.format("%s - Quantity: %d", invoice.getPartDescription(), invoice.getQuantity()))
                .forEach(System.out::println);
        System.out.println();

        //d) Use streams to map each Invoice to its partDescription and value of the Invoice (i.e. quantity * pricePerItem). Order the results by Invoice Value. [4 marks]
        System.out.println("Invoices sorted by invoice value:");
        list.stream()
                .sorted(Comparator.comparingDouble(Invoice::getValue))
                .map(invoice -> String.format("%s - Value: %8.2f", invoice.getPartDescription(), invoice.getValue()))
                .forEach(System.out::println);
        System.out.println();

        //e) Modify the part(d) to select the invoice values in the range $200 to $500. [4 marks]
        Predicate<Invoice> twoToFiveHundred =
                invoice -> (invoice.getValue() >= 200 && invoice.getValue() <= 500);
        System.out.println("Invoices sorted by invoice value between 200 & 500:");
        list.stream()
                .filter(twoToFiveHundred)
                .sorted(Comparator.comparingDouble(Invoice::getValue))
                .map(invoice -> String.format("%s - Value: %8.2f", invoice.getPartDescription(), invoice.getValue()))
                .forEach(System.out::println);
        System.out.println();

        //f) Find any one invoice in which the partDescription contains the word “saw”. [2 marks]
        System.out.println("Invoices sorted by partDescription containing the word “saw”:");
        list.stream()
                .filter(invoice -> invoice.partDescription.matches("(?i).*saw.*"))
                .forEach(System.out::println);
        System.out.println();
    }
}
