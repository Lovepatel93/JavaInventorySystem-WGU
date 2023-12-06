/**
 * Supplied class Part.java
 */
/**
 *
 * @author Love Patel
 */
package model;

public abstract class Part {
    private int partID;
    private String partName;
    private Double partPrice = 0.0;
    private int partInStock;
    private int min;
    private int max;




    //Part Constructor
    public Part(int partID, String partName, Double partPrice, int partInStock, int min, int max) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partInStock = partInStock;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return gets the part Id.
     */
    public int getPartID() {
        return partID;
    }

    /**
     * @param partID the id to set
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }

    /**
     * @return Gets the name of the part.
     */
    public String getPartName() {
        return partName;
    }

    /**
     * @param partName the name to set
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * @return Gets the part Price
     */
    public Double getPartPrice() {
        return partPrice;
    }

    /**
     * @param partPrice the price to set
     */
    public void setPartPrice(Double partPrice) {
        this.partPrice = partPrice;
    }

    /**
     * @return Gets the Inventory available.
     */
    public int getPartInStock() {
        return partInStock;
    }

    /**
     * @param partInStock the stock to set
     */
    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }

    /**
     * @return Gets the minimum to be kept in inventory.
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return Gets the maximum to be kept in the Inventory.
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
