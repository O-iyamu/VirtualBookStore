/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/

/**
 * An inventory class to keep track of the inventory of the class
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Inventory implements ProductStockContainer{
    private HashMap<Integer, Integer>inventory;
    private HashMap<Integer, String>products;
    private HashMap<Integer, Double>price;
    private ArrayList<Product> productList;


    /**
     * A constructor of the inventory class
     */
    public Inventory(){
        inventory =  new HashMap<Integer, Integer>();
        products = new HashMap<Integer, String>();
        price = new HashMap<Integer, Double>();
        productList = new ArrayList<Product>();
        initialize();
    }


    /**
     * A method to get the stcok of a particular product
     * @param product Product, the product
     * @return the stock of the prodyct
     */
    @Override
    public int getProductQuantity(Product product) {
        int value = 0;
        if (!inventory.containsKey(product.getId())) {
            return value;
        }else{
            Iterator<Integer> it = inventory.keySet().iterator();
            while (it.hasNext()) {
                Integer key = it.next();
                if (key == product.getId()) {
                    value = inventory.get(key);
                }
            }
        }
        return value;
    }

    /**
     * A method to return the inventory
     * @return the inventory
     */
    public HashMap<Integer, Integer> getInventory(){ return this.inventory; }

    /**
     *  getter method to return the name of a particular product
     * @return the name of a product
     */
    public HashMap<Integer, String> getProducts(){ return this.products; }

    /**
     * A getter method to return the price of a particular product
     * @return the price of a product
     */
    public HashMap<Integer, Double> getPrice(){ return this.price;}

    /**
     * A getter method for the contents of the products in the inventory
     * @return the products in the inventory
     */
    public ArrayList<Product> getProductList(){ return this.productList; }

    /**
     * A method to return the string representation of the inventory
     */
    public String getRepInventory(){

        System.out.println("  Name  " + "  Product ID  " + "  Quantity  ");
        String inventory = "";

        for (Map.Entry<Integer, Integer> entry : this.inventory.entrySet()){
            inventory += "  " +this.products.get(entry.getKey()) + "    " + entry.getKey() + "    " + entry.getValue()
            + "  ";
        }

        return inventory;
    }

    /**
     * A method to add stock to particular product if already in the inventory and make a new
     * product if not in the inventory
     * @param p1, the product to be added to
     * @param newStock, the amount of stock to be added
     */
    @Override
    public void addProductQuantity(Product p1, int newStock){
        if (!inventory.containsKey(p1.getId())){
            Product newProduct = new Product(p1.getName(), p1.getId(), p1.getPrice());

        }else{
            Iterator<Integer> it = inventory.keySet().iterator();
            while(it.hasNext()){
               Integer key = it.next();
               if (key == p1.getId()) {

                   inventory.put(key, inventory.get(key) + newStock);
               }
            }
        }
    }


    /**
     * A method to remove a particular amount of stock of a product if in the inventory
     * @param product, Product, the product
     * @param removeStock, the amount to be removed
     */
    @Override
    public void removeProductQuantity(Product product, int removeStock){

        if (!inventory.containsKey(product.getId())){
            return;
        }else{
            Iterator<Integer> it = inventory.keySet().iterator();
            while(it.hasNext()){
                Integer key = it.next();
                if (key == product.getId()) {
                    if (inventory.get(product.getId()) - removeStock < 0){
                        return;
                    }
                    else {
                        inventory.put(key, inventory.get(key) - removeStock);
                    }
                }
            }
        }
    }

    /**
     * A getter method to get the number of Products in the inventory
     */
    @Override
    public int getNumOfProduct(){
        return this.inventory.size();
    }


    /**
     * A method to get the string representation of the product
     * @param productID, the Id of the product
     * @return the product information
     */
    public Product getInfo(int productID){
        Product newProduct = new Product();

        Iterator<Integer> it = inventory.keySet().iterator();
        while(it.hasNext()){
            Integer key = it.next();
            if (key == productID) {

                newProduct = new Product(products.get(key), key, price.get(key));
            }
        }
        return newProduct;
    }

    /**
     * A method to initialize the products in the inventory
     */
    public void initialize(){
        this.inventory.put(0, 200);
        this.products.put(0, "Math");
        this.price.put(0, 100.0);
        Product a = new Product(this.products.get(0), 0, this.price.get(0) );
        this.productList.add(a);

        this.inventory.put(1, 200);
        this.products.put(1, "English");
        this.price.put(1, 100.0);
        Product b = new Product(this.products.get(1), 1, this.price.get(1) );
        this.productList.add(b);

        this.inventory.put(2, 200);
        this.products.put(2, "History");
        this.price.put(2, 100.0);
        Product c = new Product(this.products.get(2), 2, this.price.get(2) );
        this.productList.add(c);

        this.inventory.put(3, 200);
        this.products.put(3, "Science");
        this.price.put(3, 100.0);
        Product d = new Product(this.products.get(3), 3, this.price.get(3) );
        this.productList.add(d);

        this.inventory.put(4, 200);
        this.products.put(4, "Psychology");
        this.price.put(4, 100.0);
        Product e = new Product(this.products.get(4), 4, this.price.get(4) );
        this.productList.add(e);

        this.inventory.put(5, 200);
        this.products.put(5, "Sociology");
        this.price.put(5, 100.0);
        Product f = new Product(this.products.get(5), 5, this.price.get(5) );
        this.productList.add(f);

        this.inventory.put(6, 200);
        this.products.put(6, "Biology");
        this.price.put(6, 100.0);
        Product g = new Product(this.products.get(6), 6, this.price.get(6) );
        this.productList.add(g);

        this.inventory.put(7, 200);
        this.products.put(7, "Linguistics");
        this.price.put(7, 100.0);
        Product h = new Product(this.products.get(7), 7, this.price.get(7) );
        this.productList.add(h);

        this.inventory.put(8, 200);
        this.products.put(8, "Programming");
        this.price.put(8, 100.0);
        Product i = new Product(this.products.get(8), 8, this.price.get(8) );
        this.productList.add(i);

        this.inventory.put(9, 200);
        this.products.put(9, "Digital System");
        this.price.put(9, 100.0);
        Product j = new Product(this.products.get(9), 9, this.price.get(9) );
        this.productList.add(j);

        this.inventory.put(10, 200);
        this.products.put(10, "Geography");
        this.price.put(10, 100.0);
        Product k = new Product(this.products.get(10), 10, this.price.get(10) );
        this.productList.add(k);

        this.inventory.put(11, 200);
        this.products.put(11, "Accounting");
        this.price.put(11, 100.0);
        Product l = new Product(this.products.get(11), 11, this.price.get(11) );
        this.productList.add(l);

        this.inventory.put(12, 200);
        this.products.put(12, "Nursing");
        this.price.put(12, 100.0);
        Product m = new Product(this.products.get(12), 12, this.price.get(12) );
        this.productList.add(m);

        this.inventory.put(13, 200);
        this.products.put(13, "Anthropology");
        this.price.put(13, 100.0);
        Product n = new Product(this.products.get(13), 13, this.price.get(13) );
        this.productList.add(n);

        this.inventory.put(14, 200);
        this.products.put(14, "Anatomy");
        this.price.put(14, 100.0);
        Product o = new Product(this.products.get(14), 14, this.price.get(14) );
        this.productList.add(o);

        this.inventory.put(15, 200);
        this.products.put(15, "Bioscience");
        this.price.put(15, 100.0);
        Product p = new Product(this.products.get(15), 15, this.price.get(15) );
        this.productList.add(p);

        this.inventory.put(16, 200);
        this.products.put(16, "Robotics");
        this.price.put(16, 100.0);
        Product q = new Product(this.products.get(16), 16, this.price.get(16) );
        this.productList.add(q);

        this.inventory.put(17, 200);
        this.products.put(17, "Computer Science");
        this.price.put(17, 100.0);
        Product r = new Product(this.products.get(17), 17, this.price.get(17) );
        this.productList.add(r);

        this.inventory.put(18, 200);
        this.products.put(18, "Architecture");
        this.price.put(18, 100.0);
        Product s = new Product(this.products.get(18), 18, this.price.get(18) );
        this.productList.add(s);

        this.inventory.put(19, 200);
        this.products.put(19, "Finance");
        this.price.put(19, 100.0);
        Product t = new Product(this.products.get(19), 19, this.price.get(19) );
        this.productList.add(t);

        this.inventory.put(20, 200);
        this.products.put(20, "Baking");
        this.price.put(20, 100.0);
        Product u = new Product(this.products.get(20), 20, this.price.get(20) );
        this.productList.add(u);

        this.inventory.put(21, 200);
        this.products.put(21, "Physics");
        this.price.put(21, 100.0);
        Product v = new Product(this.products.get(21), 21, this.price.get(21) );
        this.productList.add(v);

        this.inventory.put(22, 200);
        this.products.put(22, "Chemistry");
        this.price.put(22, 100.0);
        Product w = new Product(this.products.get(22), 20, this.price.get(22) );
        this.productList.add(w);

        this.inventory.put(23, 200);
        this.products.put(23, "Music");
        this.price.put(23, 100.0);
        Product x = new Product(this.products.get(23), 23, this.price.get(23) );
        this.productList.add(x);

        this.inventory.put(24, 200);
        this.products.put(24, "Geology");
        this.price.put(24, 100.0);
        Product y = new Product(this.products.get(24), 24, this.price.get(24) );
        this.productList.add(y);

        this.inventory.put(25, 200);
        this.products.put(25, "Economics");
        this.price.put(25, 100.0);
        Product z = new Product(this.products.get(25), 25, this.price.get(25) );
        this.productList.add(z);

        this.inventory.put(26, 200);
        this.products.put(26, "Marketing");
        this.price.put(26, 100.0);
        Product za = new Product(this.products.get(26), 26, this.price.get(26) );
        this.productList.add(za);

        this.inventory.put(27, 200);
        this.products.put(27, "Global Ethics");
        this.price.put(27, 100.0);
        Product zb = new Product(this.products.get(27), 27, this.price.get(27) );
        this.productList.add(zb);
    }
}
