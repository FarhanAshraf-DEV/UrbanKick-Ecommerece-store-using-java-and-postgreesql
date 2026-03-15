import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class UserDashboard extends JFrame {

    private JTextArea cartTextArea;
    private double totalAmount;
    private JLabel totalLabel;
    private List<Product> productList;
    private Map<String,Integer> cartItems;

    public UserDashboard(){

        setTitle("UrbanStore.pk - Products");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        setLocationRelativeTo(null);

        productList = new ArrayList<>();
        cartItems = new HashMap<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245,245,245));
        add(mainPanel);

        // HEADER
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30,30,30));

        JLabel titleLabel = new JLabel("UrbanStore.pk");
        titleLabel.setFont(new Font("Segoe UI",Font.BOLD,26));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel,BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(15,15));
        contentPanel.setBackground(new Color(245,245,245));
        mainPanel.add(contentPanel,BorderLayout.CENTER);

        // CATEGORY + SEARCH
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBackground(new Color(245,245,245));

        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>();

        categoryComboBox.addItem("All");
        categoryComboBox.addItem("Electronics");
        categoryComboBox.addItem("Clothing");
        categoryComboBox.addItem("Books");
        categoryComboBox.addItem("Home Decor");

        JTextField searchField = new JTextField(12);
        JButton searchButton = new JButton("Search");

        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);
        categoryPanel.add(new JLabel("Search:"));
        categoryPanel.add(searchField);
        categoryPanel.add(searchButton);

        contentPanel.add(categoryPanel,BorderLayout.NORTH);

        // PRODUCT GRID
        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane,BorderLayout.CENTER);

        JPanel productsPanel = new JPanel(new GridLayout(0,3,20,20));
        productsPanel.setBackground(new Color(240,240,240));
        scrollPane.setViewportView(productsPanel);

        // PRODUCTS
        productList.add(new Product("Apple","assets/products/ph1.jfif","PKR 122999",122999,"Electronics"));
        productList.add(new Product("OnePlus","assets/products/ph2.jpg","PKR 54999",54999,"Electronics"));
        productList.add(new Product("OPPO","assets/products/rel.jfif","PKR 23999",23999,"Electronics"));
        productList.add(new Product("Realme","assets/products/samsung-1283938_1280.jfif","PKR 19999",19999,"Electronics"));
        productList.add(new Product("White Shirt","assets/products/clt1.png","PKR 999",999,"Clothing"));
        productList.add(new Product("US Polo Shirt","assets/products/us.png","PKR 1999",1999,"Clothing"));
        productList.add(new Product("Linen Trouser","assets/products/tr.png","PKR 1499",1499,"Clothing"));
        productList.add(new Product("Core Java","assets/products/jav.jpg","PKR 749",749,"Books"));
        productList.add(new Product("Operating Systems","assets/products/download.png","PKR 1249",1249,"Books"));
        productList.add(new Product("Python ML","assets/products/pyt.png","PKR 849",849,"Books"));
        productList.add(new Product("Table Lamp","assets/products/tl.png","PKR 350",350,"Home Decor"));
        productList.add(new Product("Vase","assets/products/vs.jpg","PKR 150",150,"Home Decor"));

        updateProductPanel(productsPanel,"All");

        // CART PANEL
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250,400));
        contentPanel.add(leftPanel,BorderLayout.WEST);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setBorder(BorderFactory.createTitledBorder("Shopping Cart"));
        leftPanel.add(cartPanel);

        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);

        cartPanel.add(new JScrollPane(cartTextArea),BorderLayout.CENTER);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total Amount: PKR 0");
        totalPanel.add(totalLabel);

        cartPanel.add(totalPanel,BorderLayout.SOUTH);

        // BUTTONS
        JPanel bottomPanel = new JPanel();

        JButton resetButton = new JButton("Reset Cart");
        JButton checkoutButton = new JButton("Checkout");

        bottomPanel.add(resetButton);
        bottomPanel.add(checkoutButton);

        mainPanel.add(bottomPanel,BorderLayout.SOUTH);

        // ACTIONS
        resetButton.addActionListener(e -> resetCart());

        checkoutButton.addActionListener(e -> {
            if(totalAmount==0){
                JOptionPane.showMessageDialog(this,"Please add products first.");
            }else{
                JOptionPane.showMessageDialog(this,"Checkout Complete!");
                showBillingDashboard();
            }
        });

        categoryComboBox.addActionListener(e -> {
            String selected=(String)categoryComboBox.getSelectedItem();
            updateProductPanel(productsPanel,selected);
        });

        searchButton.addActionListener(e -> {

            String keyword = searchField.getText().toLowerCase();
            productsPanel.removeAll();

            for(Product product:productList){

                if(product.getProductName().toLowerCase().contains(keyword)){
                    addProductPanel(productsPanel,product);
                }

            }

            productsPanel.revalidate();
            productsPanel.repaint();
        });
    }

    private void updateProductPanel(JPanel productsPanel,String category){

        productsPanel.removeAll();

        for(Product product:productList){

            if(category.equals("All") || product.getCategory().equals(category)){
                addProductPanel(productsPanel,product);
            }

        }

        productsPanel.revalidate();
        productsPanel.repaint();
    }

    public void resetCart(){

        cartItems.clear();
        cartTextArea.setText("");
        totalAmount=0;

        totalLabel.setText("Total Amount: PKR 0");

    }

    private void updateCart(){

        cartTextArea.setText("");
        totalAmount=0;

        for(Product p:productList){

            if(cartItems.containsKey(p.getProductName())){

                int qty = cartItems.get(p.getProductName());
                double price = qty * p.getAmount();

                cartTextArea.append(p.getProductName()+" x "+qty+" - PKR "+(int)price+"\n");

                totalAmount+=price;
            }

        }

        totalLabel.setText("Total Amount: PKR "+(int)totalAmount);
    }

    private void addProductPanel(JPanel productsPanel,Product product){

        try{

            Image productImage = ImageIO.read(new File(product.getImagePath()));
            Image scaled = productImage.getScaledInstance(140,140,Image.SCALE_SMOOTH);

            JPanel productPanel = new JPanel(new BorderLayout());
            productPanel.setBackground(Color.WHITE);
            productPanel.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));

            productPanel.addMouseListener(new MouseAdapter(){

                public void mouseEntered(MouseEvent e){
                    productPanel.setBorder(BorderFactory.createLineBorder(new Color(0,123,255),2));
                }

                public void mouseExited(MouseEvent e){
                    productPanel.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
                }

            });

            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel nameLabel = new JLabel(product.getProductName(),JLabel.CENTER);
            JLabel priceLabel = new JLabel(product.getPrice(),JLabel.CENTER);

            JButton minusBtn = new JButton("-");
            JButton plusBtn = new JButton("+");

            JLabel qtyLabel = new JLabel("0");

            JPanel qtyPanel = new JPanel();
            qtyPanel.add(minusBtn);
            qtyPanel.add(qtyLabel);
            qtyPanel.add(plusBtn);

            productPanel.add(imageLabel,BorderLayout.NORTH);
            productPanel.add(nameLabel,BorderLayout.CENTER);
            productPanel.add(priceLabel,BorderLayout.SOUTH);

            JPanel bottom = new JPanel();
            bottom.add(qtyPanel);

            productPanel.add(bottom,BorderLayout.PAGE_END);

            plusBtn.addActionListener(e -> {

                int qty = cartItems.getOrDefault(product.getProductName(),0);
                qty++;

                cartItems.put(product.getProductName(),qty);
                qtyLabel.setText(String.valueOf(qty));

                updateCart();
            });

            minusBtn.addActionListener(e -> {

                int qty = cartItems.getOrDefault(product.getProductName(),0);

                if(qty>0){

                    qty--;

                    if(qty==0){
                        cartItems.remove(product.getProductName());
                    }else{
                        cartItems.put(product.getProductName(),qty);
                    }

                    qtyLabel.setText(String.valueOf(qty));

                    updateCart();
                }
            });

            productsPanel.add(productPanel);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showBillingDashboard(){

        BillingDashboard billingDashboard = new BillingDashboard(cartTextArea.getText(),totalAmount);
        billingDashboard.setVisible(true);

    }

    public class Product{

        private String productName;
        private String imagePath;
        private String price;
        private double amount;
        private String category;

        public Product(String name,String path,String price,double amount,String category){

            this.productName=name;
            this.imagePath=path;
            this.price=price;
            this.amount=amount;
            this.category=category;

        }

        public String getProductName(){return productName;}
        public String getImagePath(){return imagePath;}
        public String getPrice(){return price;}
        public double getAmount(){return amount;}
        public String getCategory(){return category;}
    }

    public static void main(String[] args){

        try{
            for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e){}

        new UserDashboard().setVisible(true);
    }
}
