/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/

/**
 * A class that manages the inventory of the system
 */

import java.util.ArrayList;
import java.util.Map;

public class StoreManager{
    private static int id_count = 0;
    private int cartID;
    private Inventory inventory;
    private ShoppingCart cart;


    /**
     *A constructor of the store manager class
     */
    public StoreManager(){
        this.cartID = id_count;
        this.inventory = new Inventory();
        this.cart = new ShoppingCart();
    }

    /**
     * A method to assign a unique cartID to each user
     * @return a unique cart ID
     */
    public static int assignNewCartID(){
        return id_count++;
    }

    /**
     * A method to get the inventory
     * @return VirtualStore.Inventory, the inventory
     */
    public Inventory getInventory(){
        return this.inventory;
    }

    /**
     * A method to return the cart
     * @return VirtualStore.ShoppingCart, the cart
     */
    public ShoppingCart getCart(){ return this.cart; }

    /**
     * A method to return a string representation of items in the cart
     * @return a string representation of the items in the cart
     */
    public StringBuilder stringGetCart(){
        StringBuilder cartItems = new StringBuilder();

        cartItems.append("<html>         Product  | Quantity|  Price  <br/>");
        for (Map.Entry<Product, Integer> entry : getCart().getShoppingCart().entrySet()) {
            cartItems.append(String.format("<html>%15s",entry.getKey().getName())).append("  |  ");
            cartItems.append(String.format("<html>%2d",entry.getValue())).append("     |  ");
            cartItems.append(String.format("<html>%5s", String.format("$%.2f", entry.getKey().getPrice()))).append("<br>");

        }
        return cartItems;
    }

    /**
     * A method to maintain the shopping cart of the system
     * @return the total of the items in the client's shopping cart
     */
    public double transactionProcess(){
        double total = 0;
        String itemInCart = "[ \n";
        for(Map.Entry<Product, Integer> entry : this.cart.getShoppingCart().entrySet()){
            if(inventory.getProductQuantity(entry.getKey()) < entry.getValue()){
                return -1;
            }
            else{
                itemInCart += entry.getKey() + ": " + entry.getValue() + "\n";
                total += inventory.getInfo(entry.getKey().getId()).getPrice() * entry.getValue();
                inventory.removeProductQuantity(entry.getKey(), entry.getValue());
            }
        }
        itemInCart += "]";
        return total;
    }
}
