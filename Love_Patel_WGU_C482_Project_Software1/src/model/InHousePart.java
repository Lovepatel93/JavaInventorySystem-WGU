/**
 *
 * @author Love Patel
 */
package model;

/**
 * This is model class for handling InHouse Parts. It Inherits from the Part class.
 */
public class InHousePart extends Part {

    private int machineID;

    /**
     * This is a Constructor of the InHousePart class.
     * @param partID Unique ID of the InHouse Part.
     * @param name Name of the InHouse Part.
     * @param price Price of the InHouse Part.
     * @param numInStock Inventory available for the InHouse Part
     * @param min Minimum Inventory of the InHouse Part.
     * @param max Maximum Inventory of the InHouse Part.
     * @param machineID Unique Machine ID for the InHouse Part.
     */
    public InHousePart(int partID, String name, double price, int numInStock, int min, int max, int machineID) {
       super(partID, name, price, numInStock, min, max);
       this.machineID = machineID;
    }

    /**
     * This is a Getter for MachineID
     * @return Gets the Machine ID of the InHouse Part.
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * This is a Setter for MachineID.
     * @param machineID Sets the Machine ID of the InHouse Part.
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
