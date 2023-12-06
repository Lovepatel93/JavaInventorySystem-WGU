/**
 * @author Love Patel
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the Product Class that handles all the operations of the Products.
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int productID;
    private String productName;
    private Double productPrice = 0.0;
    private int productInStock;
    private int productMin;
    private int productMax;

    static int nextID = 1;

    /**
     * This method gets the Id and then Increments the Id count for the next use. This method helps with
     * auto-generating of the Ids of the Products.
     * @return returns the next Id which is used for auto generating the Product IDs.
     */
    public static int getAndIncrementNextID() {
        return nextID++;
    }

    /*public static int getNextID() {
        return nextID;
    }*/


    /**
     * This is a Constructor of the Product class. It helps with initializing data in its each fields.
     * @param productID Id of the Product.
     * @param productName Name of the Product.
     * @param price Price of the Product.
     * @param numInStock Inventory of the Product.
     * @param min Minimum inventory of the Product.
     * @param max Maximum Inventory of the Product.
     */
    public Product(int productID, String productName, double price, int numInStock, int min, int max) {
        setProductID(productID);
        setProductName(productName);
        setProductPrice(price);
        setProductInStock(numInStock);
        setProductMin(min);
        setProductMax(max);

    }

    /**
     * This is a Product Constructor with the additional ability to initialize associated parts.
     * @param productID Id of the Product.
     * @param productName Name of the Product.
     * @param productPrice Price of the Product.
     * @param productInStock Inventory of the Product.
     * @param productMin Minimum inventory of the Product.
     * @param productMax Maximum Inventory of the Product.
     * @param associatedParts list of all the parts associated with the product.
     */
    public Product(int productID, String productName, double productPrice, int productInStock, int productMin, int productMax, ObservableList associatedParts) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInStock = productInStock;
        this.productMin = productMin;
        this.productMax = productMax;
        this.associatedParts.addAll(associatedParts);

    }
/**
 * This method allows the Default Product constructor.
 */
    public Product() {}

    /**
     * This method gets the Id of the Product.
     * @return Returns the id of the Product.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method sets the product id.
     * @param productID Id of the product.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * This method gets the name of the Product.
     * @return Returns the name of the product.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method sets the name of the Product
     * @param productName The name of the Product.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method gets the Price of the Product.
     * @return Returns the Price of the Product.
     */
    public Double getProductPrice() {
        return productPrice;
    }

    /**
     * This method sets the price of the Product.
     * @param productPrice Price of the Product.
     */
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This method gets the Inventory of the Particular product.
     * @return Returns the Inventory of this Product.
     */
    public int getProductInStock() {
        return productInStock;
    }

    /**
     * This method sets the Inventory of the Product.
     * @param productInStock Returns the inventory number of the Particular product.
     */
    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }

    /**
     * This method gets the minimum number of particular Product required to be kept in the inventory.
     * @return Gets the minimum number of the particular product required to be kept in the inventory.
     */
    public int getProductMin() {
        return productMin;
    }

    /**
     * This method sets the minimum inventory of this product.
     * @param productMin Minimum inventory of the product.
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**
     * This method gets the maximum inventory of this product.
     * @return Returns the maximum inventory allowed of this product.
     */
    public int getProductMax() {
        return productMax;
    }

    /**
     * This method sets the Maximum inventory of this product.
     * @param productMax The maximum inventory of this product allowed to be kept in the inventory.
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    /**
     * This method adds parts into the Associated parts list of this product.
     * @param part Selected part to be associated with the product.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method dissociates the part from the product.
     * @param selectedAssociatedPart The selected part that needs to be dissociated.
     * @return Returns true if the method is successful in dissociating the selected part from the product.
     * It returns false otherwise.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * This method gets the list of all the parts associated with the product.
     * @return Returns the list of all the parts associated with the product.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
