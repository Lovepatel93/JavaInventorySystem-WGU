/**
 *
 * @author Love Patel
 */
package model;

import controller.MainScreenController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * This is the Inventory class. It is used to keep track of Available Inventory of Parts and Products and
 * to make changes when needed appropriately.
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This adds a part when user needs to add a new part in the app.
     * @param newPart The Part that needs to be added.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method adds a product to the Product Inventory when a users needs to add one.
     * @param newProduct The Products that is added to Inventory.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method Searches for the Part based on the Part ID
     * @param partId Id of the part that user wants to search
     * @return It returns the Part whose Id is being searched.
     */
    public static Part lookupPart(int partId) {
        for (Part p : allParts) {
            if (p.getPartID() == partId) {
                return p;
            }
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Part cannot be found!");
        alert.setContentText("The Part whose id you are looking for does not exist.");
        alert.showAndWait();

        return null;
    }

    /**
     * This method searches for the Product based on its ID
     * @param productId The ID of the product that is being searched
     * @return Returns the Product whose ID matches with the one we wear looking for.
     */
    public static Product lookupProduct(int productId) {
        for (Product p : allProducts) {
            if (p.getProductID() == productId) {
                return p;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Part cannot be found!");
        alert.setContentText("The Product whose id you are looking for does not exist.");
        alert.showAndWait();

        return null;
    }

    /**
     * The method Searches for the Part based on it's name.
     * @param partNameToLookup The Name of the Part we are searching.
     * @return Returns The Part whose name matches the one we are searching
     */
    public static ObservableList<Part> lookupPart(String partNameToLookup) {
        if (!allParts.isEmpty()) {
            ObservableList foundPartsList = FXCollections.observableArrayList();
            for (Part p : getAllParts()) {
                if (p.getPartName().toLowerCase().contains(partNameToLookup.toLowerCase())) {
                    foundPartsList.add(p);
                }
            }
            if (foundPartsList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part cannot be found!");
                alert.setContentText("The Part whose name you are looking for does not exist.");
                alert.showAndWait();// If user searches parts that doesn't exist then show an error message.
            } else {
                return foundPartsList; //if the part names were found then show the found parts.
            }
        }
        return null;
    }

    /**
     * This Method searches for a product based on it's name.
     * @param productNameToLookup The name of the Product we are Searching.
     * @return Returns the Product whose name we are searching.
     */
    public static ObservableList<Product> lookupProduct(String productNameToLookup) {
        if (!allProducts.isEmpty()) {
            ObservableList foundProductsList = FXCollections.observableArrayList();
            for (Product p : getAllProducts()) {
                if (p.getProductName().toLowerCase().contains(productNameToLookup.toLowerCase())) {
                    foundProductsList.add(p);
                }
            }
            if (foundProductsList.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Product cannot be found!");
                alert.setContentText("The Product whose name you are looking for does not exist.");
                alert.showAndWait(); // If user searches products that doesn't exist then show an error message.
            } else {
                return foundProductsList; //if the product names were found then show the found products list.
            }

        }
        return null;
    }

    /**
     * This method updates the selected part with all the changes made by the user.
     * @param index the Index location of the part that is being updated.
     * @param selectedPart The Part that the user has selected to modify.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    /**
     * This method updates the selected product with all the changes made by the user.
     * @param index the Index location of the product that is being updated.
     * @param newProduct The Product that the user has selected to modify.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * This method Deleted the Part Selected by user.
     * @param selectedPart The Part that the user has selected.
     * @return It returns true if the method was successful in Deleting the selected Part. If not, It returns False.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * This Method deleted the selected product.
     * @param selectedProduct The product that the User has selected.
     * @return It returns true if the method is successful in Deleting the Product. If not, it returns False.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * This method gets the list of all parts from the Inventory.
     * @return Returns the list of All Parts stored in the Inventory.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method get the list of all products from the inventory.
     * @return Returns the list of all Products stored in the Inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
