package application.pizzaking;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.Date;
import javafx.scene.control.*;
import javax.swing.JOptionPane;




public class PizzaOrderFXMLController implements Initializable {
    static String resultStr;
    @FXML
    private RadioButton smallRB;
    @FXML
    private RadioButton mediumRB;
    @FXML
    private RadioButton largeRB;
    @FXML
    private TextArea resArea;
    @FXML
    private CheckBox MushroomsCB;
    @FXML
    private CheckBox cheeseCB;
    @FXML
    private CheckBox VegetablesCB;
    @FXML
    private TextField totalPriceField;
    @FXML
    private TextField NDrinkField;
    @FXML
    private Button submitButton;
    @FXML
    private TextField finalBillField;

    @FXML
    private Label time;
    private volatile boolean stop = false;


    @FXML
    private TextField creditcardField;




    @FXML
    private Button printButton;    //






    private static final double SMALL_PIZZA_PRICE = 6.0;
    private static final double MEDIUM_PIZZA_PRICE = 8.0;
    private static final double LARGE_PIZZA_PRICE = 12.0;
    private static final double TOPPINGS_PRICE = 2.0;


    @FXML
    private Spinner<Double> sp1;

    @FXML
    private Spinner<Double> sp2;

    @FXML
    private Spinner<Double> sp3;

    @FXML
    private Spinner<Double> sp4;

    private void submitButtonAction() {
        submitButton.setOnAction(t -> {

            double totalPrice;
            if (!smallRB.isSelected() && !mediumRB.isSelected() && !largeRB.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select the size of Pizza!");
                return;
            }
            String type;
            double TotalNumberofOrders = 0;
            if (smallRB.isSelected()) {
                type = "Small";
                totalPrice = SMALL_PIZZA_PRICE * sp1.getValue();
                TotalNumberofOrders = sp1.getValue();
            } else if (mediumRB.isSelected()) {
                type = "Medium";
                totalPrice = MEDIUM_PIZZA_PRICE * sp2.getValue();
               TotalNumberofOrders = sp2.getValue();

            } else {
                type = "Large";
                totalPrice = LARGE_PIZZA_PRICE * sp3.getValue();
                TotalNumberofOrders = sp3.getValue();


            }

            double SalesTax = totalPrice * 0.2;
            totalPrice = totalPrice + SalesTax;


            double toppingsPrice = 0.0;
            ArrayList<String> toppings = new ArrayList<>();
            if (MushroomsCB.isSelected())
                toppings.add("Mushrooms - $2");
            if (cheeseCB.isSelected())
                toppings.add("Cheese - $2");
            if (VegetablesCB.isSelected())
                toppings.add("Vegetables - $2");
            String toppingStr = "Toppings chosen: " + toppings.size();
            if (!toppings.isEmpty()) {
                toppingStr += "\n";
                for (int i = 0; i < toppings.size(); i++) {
                    if (i == toppings.size() - 1)
                        toppingStr += toppings.get(i);
                    else
                        toppingStr += toppings.get(i) + "\n";
                    toppingsPrice += TOPPINGS_PRICE;
                }
            }


            double nDrink;
            double DrinkPrice = 2.5;
            if (NDrinkField.getText().equals(""))
                nDrink = 0;
            else
                nDrink = Integer.parseInt(NDrinkField.getText().trim());

            double finalBill = (totalPrice + toppingsPrice + (nDrink * DrinkPrice));

            resultStr = "You selected:\n" + (int) TotalNumberofOrders + " " + type + "-sized Pizza " + "\n" + toppingStr + "\n" + "Remaining Customers: " + (int) (sp4.getValue() - 1) + "\n" + "Order Created at: " + new Date();
            resArea.setText(resultStr);

            totalPriceField.setText(String.format("$%.2f", totalPrice));

            finalBillField.setText(String.format("$%.2f", finalBill));



/*

            class Validate {
                public static boolean validate(final String n){
                    if (n == null || n.isEmpty()) return false;
                    boolean x = true;
                    int sum = 0;
                    int temp = 0;

                    for (int i = n.length() - 1; i >= 0; i--) {
                        temp = n.charAt(i) - '0';
                        sum += (x = !x) ? temp > 4 ? temp * 2 - 9 : temp * 2 : temp;
                    }

                    return sum % 10 == 0;
                }
            }

*/




        });
    }




    private void printButtonAction() {
        printButton.setOnAction(j -> {
                    try {                                         //kitchen
                        OutputStream outputStream = new FileOutputStream("output.txt");
                        Writer outputStreamWriter = new OutputStreamWriter(outputStream);

                        outputStreamWriter.write(resultStr);

                        outputStreamWriter.close();

                    } catch (Exception e) {
                        e.getMessage();
                    }
                });
    }






    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SpinnerValueFactory<Double> valueFactory1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,10);
        valueFactory1.setValue(1.0);
        sp1.setValueFactory(valueFactory1);
        SpinnerValueFactory<Double> valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,10);
        valueFactory1.setValue(1.0);
        sp2.setValueFactory(valueFactory2);
        SpinnerValueFactory<Double> valueFactory3 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,10);
        valueFactory1.setValue(1.0);
        sp3.setValueFactory(valueFactory3);
        SpinnerValueFactory<Double> valueFactory4 = new SpinnerValueFactory.DoubleSpinnerValueFactory(1,10);
        valueFactory1.setValue(1.0);
        sp4.setValueFactory(valueFactory4);
        ToggleGroup tg = new ToggleGroup();
        smallRB.setToggleGroup(tg);
        mediumRB.setToggleGroup(tg);
        largeRB.setToggleGroup(tg);
        submitButtonAction();
        printButtonAction();
        Timenow();
    }




    private void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while (!stop) {
                try {
                    Thread.sleep(1500);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow);
                });
            }
        });
        thread.start();
    }
    }