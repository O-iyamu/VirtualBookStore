/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/

/**
 * A class that to represent a product object
 */
public class Product {

    private String name;
    private int id;
    private double price;

    /**
     * A default constructor of the product class
     */
    public Product(){
        this("",0, 0.0);
    }

    /**
     * A constructor of the product class
     * @param name, the name of the product
     * @param id, the product id
     * @param price, the product price
     */
    public Product(String name, int id, double price){
        this.name=name;
        this.id=id;
        this.price=price;
    }

    //Accessors

    /**
     * An accessor method for the name attribute
     * @return the name of the product
     */
    public String getName(){
        return name;
    }


    /**
     * An accessor method for the Id attribute
     * @return the Id of the product
     */
    public int getId(){
        return id;
    }

    /**
     * An accessor method for the price attribute
     * @return the price of the product
     */
    public double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                '}';
    }
}
