/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/

/**
 * A class for the shopping cart of each client
 */

import java.util.HashMap;

public class ShoppingCart implements ProductStockContainer {
    private HashMap<Product, Integer> shoppingCart;

    /**
     * The constructor of the shopping cart
     */
    public ShoppingCart(){
        shoppingCart = new HashMap<Product, Integer>();
    }

    /**
     * A getter method to return the shopping cart of a client
     * @return the shopping cart
     */
    public HashMap<Product, Integer> getShoppingCart(){ return this.shoppingCart; }

    /**
     * A method to add products to the shopping cart
     * @param product, the product
     * @param quantity, the quantity of the product
     */
    @Override
    public void addProductQuantity(Product product, int quantity){
        if (shoppingCart.containsKey(product)){
            shoppingCart.put(product, shoppingCart.get(product) + quantity);
        }
        else{
            shoppingCart.put(product, quantity);
        }
    }

    /**
     * A method to remove a product from a class
     * @param product, the product to be removed
     */
    public void removeProduct(Product product){
        if (shoppingCart.containsKey(product)){
            shoppingCart.remove(product);
        }
    }

    /**
     * A method to remove a particular amount of a product
     * @param product, the product to be removed
     * @param quantity, the quantity to be removed
     */
    @Override
    public void removeProductQuantity(Product product, int quantity){
        if (shoppingCart.containsKey(product)){
            shoppingCart.put(product, shoppingCart.get(product) - quantity);
            if (shoppingCart.get(product) == 0){
                removeProduct(product);
            }
        }
    }

    /**
     * A getter method to get the quantity of a particular product in a cart
     * @param product Product, the product to be gotten
     * @return int, the amount of product in the cart
     */
    @Override
    public int getProductQuantity(Product product) {
        return this.shoppingCart.get(product);
    }


    /**
     * A getter method to get the number of products in a cart
     * @return int, the number of products in the shopping cart
     */
    @Override
    public int getNumOfProduct() {
        return this.shoppingCart.size();
    }
}
