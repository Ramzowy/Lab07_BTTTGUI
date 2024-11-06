import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Invoice extends JFrame {
    private Address address;
    private String title;
    private ArrayList<LineItem> lineItems;
    private JTextArea itemsArea;
    private JLabel totalAmountLabel;

    // Constructor to accept the title and address attributes
    public Invoice(String title, String street, String city, String state, int zipCode) {
        this.title = title;
        this.address = new Address(street, city, state, zipCode);
        this.lineItems = new ArrayList<>();
        createGUI();
    }

    public JFrame createGUI() {
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main panel with a border and padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the entire JFrame
        mainPanel.setLayout(new BorderLayout());

        JPanel titlePanel = createTitlePanel();
        JPanel addressPanel = createAddressPanel();
        JPanel totalPanel = createTotalPanel();
        JPanel itemsPanel = createItemsPanel();

        // Add components to the main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(addressPanel, BorderLayout.WEST);
        mainPanel.add(itemsPanel, BorderLayout.CENTER);
        mainPanel.add(totalPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        return this;
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    private JPanel createAddressPanel() {
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setBorder(BorderFactory.createTitledBorder("Address")); // Title border for clarity

        JLabel streetLabel = new JLabel(address.getStreet());
        JLabel cityLabel = new JLabel(address.getCity() + ", " + address.getState() + " " + address.getZipCode());

        streetLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        addressPanel.add(streetLabel);
        addressPanel.add(cityLabel);
        addressPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding within the address panel
        return addressPanel;
    }

    private JPanel createItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BorderLayout());

        // Create a JTextArea to display the line items
        itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Use monospaced font for alignment

        // Initialize the header
        String header = String.format("%-20s %-10s %-10s %-10s%n", "Product Name", "Quantity", "Price", "Total");
        itemsArea.append(header);
        itemsArea.append("------------------------------------------\n"); // Separator line

        // Create a JScrollPane to hold the JTextArea
        JScrollPane scrollPane = new JScrollPane(itemsArea);
        scrollPane.setPreferredSize(new Dimension(400, 150)); // Set height to 150 for a smaller area

        // Create a JPanel for the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Create labels and text fields for product name, quantity, and price
        JLabel productNameLabel = new JLabel("Product Name:");
        JTextField productNameField = new JTextField(10);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(5);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(5);

        // Create a button to add the item
        JButton addButton = new JButton("Add Item");

        // Add action listener to the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String productName = productNameField.getText();
                String quantityText = quantityField.getText();
                String priceText = priceField.getText();

                // Validate input
                if (!productName.isEmpty() && !quantityText.isEmpty() && !priceText.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityText);
                        int price = Integer.parseInt(priceText);

                        // Create a new Product and LineItem
                        Product product = new Product(productName, price);
                        LineItem lineItem = new LineItem(product, quantity);
                        lineItems.add(lineItem); // Add line item to the list

                        // Add line item to JTextArea
                        String lineItemText = String.format("%-20s %-10d $%-10d $%-10d%n",
                                product.getName(), lineItem.getQuantity(), lineItem.getProduct().getPrice(), lineItem.getCalculatedTotal());
                        itemsArea.append(lineItemText);

                        // Update the total amount
                        updateTotal();

                        // Clear input fields
                        productNameField.setText("");
                        quantityField.setText("");
                        priceField.setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(itemsPanel, "Please enter valid numbers for quantity and price.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(itemsPanel, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the input panel
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(addButton); // Add button to the input panel

        // Add scroll pane and input panel to itemsPanel
        itemsPanel.add(scrollPane, BorderLayout.CENTER);
        itemsPanel.add(inputPanel, BorderLayout.SOUTH); // Add input panel to the bottom

        return itemsPanel;
    }

    private JPanel createTotalPanel() {
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel amountDueLabel = new JLabel("Amount Due: ");
        amountDueLabel.setFont(new Font("Arial", Font.BOLD, 18));

        totalAmountLabel = new JLabel("$0.00");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 18));

        totalPanel.add(amountDueLabel);
        totalPanel.add(totalAmountLabel);
        return totalPanel;
    }

    private void updateTotal() {
        int total = 0;
        for (LineItem item : lineItems) {
            total += item.getCalculatedTotal();
        }
        totalAmountLabel.setText("$" + total);
    }
}