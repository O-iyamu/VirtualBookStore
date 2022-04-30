/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/
public interface ProductStockContainer {

    public int getProductQuantity(Product product);

    public void addProductQuantity(Product product, int quantity);

    public void removeProductQuantity(Product product, int quantity);

    public int getNumOfProduct();


}
