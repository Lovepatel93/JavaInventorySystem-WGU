/**
 *
 * @author Love Patel
 */
package model;

/**
 * This is the class that handles Outsourced Parts. This class Inherits from the Part class.
 */
public class OutsourcedPart extends Part {

    private String companyName;

    /**
     * This is a Constructor method of the Outsourced Part class. It helps with the initial data when an Outsourced
     * part is created.
     * @param partID Id of the Outsourced Part.
     * @param name Name of the Outsourced Part.
     * @param price Price of the Outsourced Part.
     * @param numInStock Inventory of the Outsourced Part.
     * @param min Minimum in stock required of this Outsourced Part.
     * @param max Maximum of stock required of this Outsourced Part.
     * @param companyName Name of the Company from which this Part is outsourced.
     */
    public OutsourcedPart(int partID, String name, double price, int numInStock, int min, int max, String companyName) {
       super(partID, name, price, numInStock, min, max);
       this.companyName = companyName;
    }

    /**
     * This method gets the Name of the Company of the Outsourced Part.
     * @return Returns the Company Nam.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This Method sets the name of the Company
     * @param companyName The name of the Company from whom the Part is being outsourced.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
