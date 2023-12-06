/**
 *
 * @author Love Patel
 */
package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * This Is the Main class for the JavaFX Inventory Management Application. It create the app and also contains the main method that populates
 * Data in the Different screens of the Application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static int nextID = 1;
    /**
     * This method gets the Id and then Increments the Id count for the next use. This method helps with
     * auto-generating of the Ids of the Parts.
     * @return returns the next Id which is used for auto generating the Part IDs.
     */
    public static int getAndIncrementNextID() {
        return nextID++;
    }

    /**
     * This is the main method. It is used here to populate dummy data in to the different tableviews on different screens.
     * @param args args
     */
    public static void main(String[] args) {

        ObservableList<Part> associatedParts = FXCollections.observableArrayList();

        //Adding InHouse Parts
        InHousePart part1 = new InHousePart(getAndIncrementNextID(), "CPU", 250.00, 5, 1, 100, 001);
        InHousePart part2 = new InHousePart(getAndIncrementNextID(), "Hard Drive", 345.00, 10, 1, 100, 002);
        InHousePart part3 = new InHousePart(getAndIncrementNextID(), "Motherboard", 250.00, 20, 1, 100, 003);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);


        //Adding Outsourced Parts
        OutsourcedPart part4 = new OutsourcedPart(getAndIncrementNextID(), "Mouse", 50.00, 50, 1, 100, "Company1");
        OutsourcedPart part5 = new OutsourcedPart(getAndIncrementNextID(), "Case", 69.00, 60, 1, 100, "Company2");
        OutsourcedPart part6 = new OutsourcedPart(getAndIncrementNextID(), "RAM", 100.00, 70, 1, 100, "Company3");

        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);


        //Adding Products and associating parts with them.
        associatedParts.add(part1);
        Product product1 = new Product(Product.getAndIncrementNextID(), "Desktop", 549.99, 24, 1, 100, associatedParts);
        associatedParts.add(part2);
        Product product2 = new Product(Product.getAndIncrementNextID(), "Printer", 249.00, 30, 1, 100, associatedParts);
        associatedParts.add(part3);
        Product product3 = new Product(Product.getAndIncrementNextID(), "Tablet", 399.99, 36, 1, 100, associatedParts);
        associatedParts.add(part4);
        Product product4 = new Product(Product.getAndIncrementNextID(), "DSLR Camera", 899.99, 40, 1, 100, associatedParts);

        Product product5 = new Product(Product.getAndIncrementNextID(), "Smart Phone", 850.00, 50, 1, 100);
        product5.addAssociatedPart(part5);
        Product product6 = new Product(Product.getAndIncrementNextID(), "Laptop", 1299.99, 60, 1, 100);
        product6.addAssociatedPart(part6);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
        Inventory.addProduct(product6);


        launch(args);
    }
}
