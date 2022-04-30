/*Team Members with student ID:
Iyamu Osaretinmwen 101157386
Oyindamola Taiwo-Olupeka 101155729
*/

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

/**
 * A class to implement the GUI of the store
 */
public class StoreView extends JFrame {
    private StoreManager storeManager;
    private int cardID;

    private JFrame mainFrame;
    private JFrame viewCartFrame;
    private JPanel optionPanel;
    private JScrollPane productPane;
    private JPanel headerPanel;
    private JLabel label;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private GridBagConstraints gbc;
    private JPanel productPanel;



    /**
     * A constructor for the store view class
     */
    public StoreView(StoreManager storeManager, int cardID){
        //the normal constructor
        this.storeManager = storeManager;
        this.cardID = cardID;

        //JTools
        this.mainFrame = new JFrame("Client StoreView");
        this.viewCartFrame = new JFrame("My Cart");

        this.viewCartFrame.setSize(400, 400);
        this.viewCartFrame.setResizable(false);

        this.mainFrame.setIconImage(new ImageIcon("C:\\Users\\aicey\\IdeaProjects\\VirtualStore\\VirtualStore\\VirtualStore/shop.png").getImage());
        this.mainFrame.pack();
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);
        this.mainFrame.setSize(700, 700);
        this.mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    // close it down!
                    mainFrame.setVisible(false);
                    mainFrame.dispose();
                }
            }
        });

        this.headerPanel = new JPanel(new BorderLayout());
        this.bodyPanel = new JPanel(new BorderLayout());
        this.footerPanel = new JPanel(new GridLayout());
        this.productPanel = new JPanel(new GridLayout(7, 20));
        this.productPane = new JScrollPane(this.productPanel);
        this.gbc = new GridBagConstraints();
        this.optionPanel = new JPanel(new FlowLayout());
        this.label = new JLabel("Welcome to the Course Store! (ID: " + this.cardID + ")", SwingConstants.CENTER);
        this.label.setBackground(Color.BLUE);
        this.label.setFont(label.getFont().deriveFont(Font.BOLD, 22));


        this.headerPanel.add(label, BorderLayout.CENTER);
        this.bodyPanel.add(productPane);
        this.footerPanel.add(optionPanel);
        this.productPane.getViewport().getView().setBackground(new Color(188, 198, 204));

        this.mainFrame.add(this.headerPanel, BorderLayout.NORTH);
        this.mainFrame.add(this.bodyPanel, BorderLayout.CENTER);
        this.mainFrame.add(this.footerPanel, BorderLayout.SOUTH);


        for (Map.Entry<Integer, Integer> entry : storeManager.getInventory().getInventory().entrySet()){

            JPanel p = createPanel(new Product(storeManager.getInventory().getProducts().get(entry.getKey()), entry.getKey(),
                    storeManager.getInventory().getPrice().get(entry.getKey())));
            this.productPanel.add(p);
        }
    }

    /**
     * A method to create buttons
     * @param name String, the name of he button
     * @return the button with the assigned name
     */
    public JButton createButton(String name){
        JButton button = new JButton(name);
        return button;
    }

    /**
     * A method to assign a default panel to a product with buttons and set ActionListeners
     * @param product Product, the product to be created
     * @return a panel assigning a product to a panel
     */
    public JPanel createPanel(Product product){
        Border raisedEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel header_Panel = new JPanel(new BorderLayout());
        JPanel body_Panel = new JPanel(new BorderLayout());
        JPanel footer_Panel = new JPanel(new FlowLayout());


        JLabel stock = new JLabel("Stock: " + storeManager.getInventory().getProductQuantity(product));
        JLabel price = new JLabel("Price: $" + storeManager.getInventory().getPrice().get(product.getId()));
        JLabel label = new JLabel(product.getName(), SwingConstants.CENTER);
        JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\aicey\\IdeaProjects\\VirtualStore\\VirtualStore\\VirtualStore/Book Logo Design.png"), JLabel.CENTER);

        label.setFont(label.getFont().deriveFont(Font.BOLD));
        header_Panel.add(label, BorderLayout.NORTH);
        header_Panel.add(price, BorderLayout.CENTER);
        header_Panel.add(stock, BorderLayout.SOUTH);


        body_Panel.setPreferredSize(new Dimension(250, 250));
        body_Panel.add(label1, BorderLayout.CENTER);


        mainPanel.setSize(new Dimension(500,500));
        JButton plus = createButton("+");
        JButton minus = createButton("-");
        footer_Panel.add(plus);
        footer_Panel.add(minus);
        minus.setEnabled(false);
        mainPanel.setBorder(raisedEtched);
        mainPanel.add(header_Panel, BorderLayout.NORTH);
        mainPanel.add(body_Panel, BorderLayout.CENTER);
        mainPanel.add(footer_Panel, BorderLayout.SOUTH);
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              /*-------------------------------------------*/
                if (storeManager.getInventory().getProductQuantity(product) > 0) {
                    storeManager.getCart().addProductQuantity(product, 1);
                    storeManager.getInventory().removeProductQuantity(product, 1);
                    stock.setText("Stock: " + storeManager.getInventory().getProductQuantity(product));
                }
                updateStock(product);
                if (storeManager.getInventory().getProductQuantity(product) < 200){
                    minus.setEnabled(true);
                }
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              /*-------------------------------------------*/
                if (storeManager.getInventory().getProductQuantity(product) < 200) {
                    storeManager.getCart().removeProductQuantity(product, 1);
                    storeManager.getInventory().addProductQuantity(product, 1);
                    stock.setText("Stock: " + storeManager.getInventory().getProductQuantity(product));
                }
                updateStock(product);
                if (storeManager.getInventory().getProductQuantity(product) == 200){
                    minus.setEnabled(false);
                }
            }
        });
        return mainPanel;
    }

    /**
     * A method to update the stock of a product given an id
     * @param product Product, the product of the product
     * @return int, the stock of the product
     */
    public int updateStock(Product product){
        return storeManager.getInventory().getProductQuantity(product);
    }

    /**
     * A method to show the cart of a client
     */
    private void showCart() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Product, Integer> p : storeManager.getCart().getShoppingCart().entrySet()) {
            builder.append(String.format("%15s", p.getKey().getName())).append(" | ");
            builder.append(String.format("%4d", p.getValue())).append(" | ");
            builder.append(String.format("%10s", String.format("$%.2f", p.getKey().getPrice()))).append(" | ");

            builder.append(System.lineSeparator());
        }
        builder.append("Total: $" + String.format("%.2f", storeManager.transactionProcess()));
        JOptionPane.showMessageDialog(this, builder.toString(), "My Cart", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * A method to show the cart content of a client with an option to checkout
     */
    private void showCheckout() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Product, Integer> p : storeManager.getCart().getShoppingCart().entrySet()) {
            builder.append(String.format("%15s", p.getKey().getName())).append(" | ");
            builder.append(String.format("%4d", p.getValue())).append(" | ");
            builder.append(String.format("%10s", String.format("$%.2f", p.getKey().getPrice()))).append(" | ");

            builder.append(System.lineSeparator());
        }
        builder.append("Total: $" + String.format("%.2f", storeManager.transactionProcess()));
        JOptionPane.showMessageDialog(this, builder.toString(), "My Cart", JOptionPane.INFORMATION_MESSAGE);
        int choice = JOptionPane.showConfirmDialog(this, builder.toString(), "Do you want to checkout", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    /**
     * A method to display the gui to the user
     */
    public boolean displayGUI(){
        JButton viewCart = createButton("View Cart");
        viewCart.setSize(new Dimension(250, 100));
        JButton checkout = createButton("Checkout");
        checkout.setSize(new Dimension(250, 100));
        JButton quit = createButton("Quit");
        quit.setSize(new Dimension(250, 100));
//        JLabel label = new JLabel("Default text", SwingConstants.CENTER);
        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //viewCartFrame.setVisible(true);
                if (storeManager.getCart().getShoppingCart().size() == 0){
                    //label.setText("You have no items in your Cart");
                    JOptionPane.showMessageDialog(viewCart, "Cart is empty", "My Cart", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
//                    label.setText("<html>" + storeManager.stringGetCart() + "<br>" + "Total: $" + storeManager.transactionProcess() + "<html>");
                    showCart();
                }
            }
        });
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //viewCartFrame.setVisible(true);
                if (storeManager.getCart().getShoppingCart().size() == 0){
                    //label.setText("You have no items in your Cart");
                    JOptionPane.showMessageDialog(viewCart, "Cart is empty", "My Cart", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    showCheckout();
//                    label.setText("<html>" + storeManager.stringGetCart() + "<br>" + "Total: $" + storeManager.transactionProcess() + "<html>");
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });

        this.optionPanel.add(viewCart, BorderLayout.WEST);
        this.optionPanel.add(checkout, BorderLayout.CENTER);
        this.optionPanel.add(quit, BorderLayout.EAST);

        return false;
    }


    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv = new StoreView(sm, sm.assignNewCartID());
        sv.displayGUI();
    }
}
