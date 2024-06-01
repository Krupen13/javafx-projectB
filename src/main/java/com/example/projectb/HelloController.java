package com.example.projectb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.Initializable;


public class HelloController {

    @FXML
    private AnchorPane bmsinfo;

    @FXML
    private ImageView bmslogo;

    @FXML
    private Label discount;

    @FXML
    private Label discountrmd;

    @FXML
    private AnchorPane loginApp;

    @FXML
    private Button signin_btn;

    @FXML
    private Button signin_close;

    @FXML
    private ImageView signin_img;

    @FXML
    private Label signin_label;

    @FXML
    private Hyperlink signin_link;

    @FXML
    private Button signin_mini;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private AnchorPane signinpage;

    @FXML
    private Button signup_btn;

    @FXML
    private Button signup_close;

    @FXML
    private TextField signup_email;

    @FXML
    private ImageView signup_img;

    @FXML
    private Label signup_label;

    @FXML
    private Hyperlink signup_link;

    @FXML
    private Button signup_mini;

    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;

    @FXML
    private AnchorPane signuppage;

    @FXML
    private Label welcomebms;

    @FXML
    private Button bms_apply_btn;

    @FXML
    private AnchorPane bms_bookingPage;

    @FXML
    private Button bms_logout_btn;

    @FXML
    private TextField bms_promocode;

    @FXML
    private Button checkout_btn;

    @FXML
    private Button clear_cart;

    @FXML
    private Button movies_btn;

    @FXML
    private Button opera_btn;

    @FXML
    private Button standup_btn;

    @FXML
    private Button webseries_btn;

    @FXML
    private Button to_bp_from_login;

    @FXML
    private Button to_bp_from_signup;

    @FXML
    private AnchorPane bp_checkout;

    @FXML
    private AnchorPane bp_content;

    @FXML
    private AnchorPane bp_header;

    @FXML
    private Spinner<Integer> cartSpinner0;

    @FXML
    private Spinner<Integer> cartSpinner1;

    @FXML
    private Spinner<Integer> cartSpinner2;

    @FXML
    private Spinner<Integer> cartSpinner3;

    @FXML
    private Spinner<Integer> cartSpinner4;

    @FXML
    private Label item_total;

    @FXML
    private Spinner<Integer> cartSpinner5;

    @FXML
    private Button atc0;

    @FXML
    private Button atc1;

    @FXML
    private Button atc2;

    @FXML
    private Button atc3;

    @FXML
    private Button atc4;

    @FXML
    private Button atc5;


    @FXML
    private AnchorPane bms_movies_scrollpane;


    @FXML
    private AnchorPane bms_web_scrollpane;

    @FXML
    private AnchorPane bms_standup_scrollpane;

    @FXML
    private AnchorPane bp_general;

    @FXML
    private AnchorPane bp_webseries;

    @FXML
    private Label bms_web_atf;

    @FXML
    private Label bms_web_nr;


    @FXML
    private AnchorPane bms_movies_lattest;


    @FXML
    private AnchorPane bookingLanding;


    @FXML
    private AnchorPane bms_sports_scrollpane;

    @FXML
    private Label total;

    private SpinnerValueFactory<Integer> spin;

    private int num;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    public boolean validateEmailAddress() {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(signup_email.getText());

        Alert alert;

        if (match.find() && match.group().equals(signup_email.getText())) {

            return true;

        } else {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Occurred");
            alert.setContentText("Invalid Email Address");
            alert.setHeaderText(null);
            alert.showAndWait();

            return false;
        }
    }

    public void signup() {

        String sql = "INSERT INTO admin (email_address, username, password) VALUES (?,?,?)";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signup_email.getText());
            prepare.setString(2, signup_username.getText());
            prepare.setString(3, signup_password.getText());


            Alert alert;

            if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty() || signup_password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Occurred");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks !!");
                alert.showAndWait();

            } else if (signup_password.getText().length() < 6) {

                alert = new Alert(AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Password is too weak :( \nIt must contain at least 6 letters !!");
                alert.showAndWait();

            } else {

                prepare.execute();
                alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("SignUp Info");
                alert.setContentText("You've successfully Signed Up :)");
                alert.showAndWait();

                signup_email.setText("");
                signup_username.setText("");
                signup_password.setText("");


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookingPage.fxml"));
                AnchorPane pane = fxmlLoader.load();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void signin() {

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signin_username.getText());
            prepare.setString(2, signin_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if (signin_username.getText().isEmpty() || signin_password.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error occurred");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks !!");
                alert.showAndWait();

            } else if (signin_password.getText().length() < 6) {

                alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Password :(");
                alert.showAndWait();
            } else {
                if (result.next()) {

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("SignIn Info");
                    alert.setHeaderText(null);
                    alert.setContentText("You have successfully Logged In :)");
                    alert.showAndWait();


                } else {

                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error occurred");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username or Password :(");
                    alert.showAndWait();

                    signin_username.setText("");
                    signin_password.setText("");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void switchPages(ActionEvent event) {

        if (event.getSource() == signin_link) {

            signinpage.setVisible(false);
            signuppage.setVisible(true);

        } else if (event.getSource() == signup_link) {

            signinpage.setVisible(true);
            signuppage.setVisible(false);

        }
    }


    public void switchingToBookingPage(ActionEvent event) {

        if (event.getSource() == to_bp_from_login) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);

        } else if (event.getSource() == to_bp_from_signup) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);

        } else {

            signinpage.setVisible(true);
            signuppage.setVisible(false);
            bmsinfo.setVisible(true);
            bms_bookingPage.setVisible(false);
        }
    }

    public void signin_close() {

        System.exit(0);
    }

    public void signin_mini() {
        Stage stage = (Stage) signinpage.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signup_close() {

        System.exit(0);
    }


    public void signup_mini() {
        Stage stage = (Stage) signuppage.getScene().getWindow();
        stage.setIconified(true);
    }


    public void spinnerValue0() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner0.setValueFactory(spin);

    }

    public void displayValue0() {
        num = cartSpinner3.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
    }

    public void spinnerValue1() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner1.setValueFactory(spin);
    }

    public void displayValue1() {
        num1 = cartSpinner1.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
    }

    public void spinnerValue2() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner2.setValueFactory(spin);
    }

    public void displayValue2() {

        num2 = cartSpinner2.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));

    }

    public void spinnerValue3() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner3.setValueFactory(spin);
    }

    public void displayValue3() {
        num3 = cartSpinner3.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
    }

    public void spinnerValue4() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner4.setValueFactory(spin);
    }

    public void displayValue4() {
        num4 = cartSpinner4.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
    }

    public void spinnerValue5() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        cartSpinner5.setValueFactory(spin);
    }

    public void displayValue5() {
        num5 = cartSpinner5.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
    }

    public void totalAmount() {

        num = cartSpinner0.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
        num1 = cartSpinner1.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
        num2 = cartSpinner2.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
        num3 = cartSpinner3.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
        num4 = cartSpinner4.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));
        num5 = cartSpinner5.getValue();
        item_total.setText(String.valueOf(num + num1 + num2 + num3 + num4 + num5));

        total.setText(String.valueOf((num + num1 + num2 + num3 + num4 + num5) * 10));
    }

    Alert alert;

    public void promocode() {
        if (Objects.equals(bms_promocode.getText(), "WELCOME20")) {

            int totalValue = num + num1 + num2 + num3 + num4 + num5;
            total.setText(String.valueOf(((totalValue) * 10) * 0.8));

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Promo Code");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations, You have got 20% discount.");
            alert.showAndWait();
        } else {

            int totalValue = num + num1 + num2 + num3 + num4 + num5;
            total.setText(String.valueOf((totalValue) * 10));

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Promo Code");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid Promo Code");
            alert.showAndWait();
        }
    }

     /*  public void checkout() {
        if(checkout_btn.isPressed()) {

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thank you");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for booking with us, enjoy your show.");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thank you");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for booking with us, please enjoy your show.");
            alert.showAndWait();
        }
    }
    */


    public void clear(ActionEvent event) {
        if (event.getSource() == clear_cart && total.getText() == "00") {

            alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Cart is empty");
            alert.setContentText("Your cart is already empty !!");
            alert.showAndWait();

        } else {

            item_total.setText("00");
            total.setText("00");

            alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Cleared");
            alert.setContentText("Your cart has been cleared !");
            alert.showAndWait();
        }
    }

    public void checkOut(ActionEvent event) {

        if (total.getText().equals("00")) {

            total.setText("00");
            item_total.setText("00");
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("No items in your cart !!");
            alert.setHeaderText(null);
            alert.setContentText("Please add to your cart in order to Checkout !!!");
            alert.showAndWait();

        } else if (event.getSource() == checkout_btn && total.getText() != "00") {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thank you");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for booking with us, please enjoy your show.");
            alert.showAndWait();
        }
    }

    public void switchToMovies(ActionEvent event) {

        if (event.getSource() == movies_btn) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);
            bp_general.setVisible(true);
            bms_movies_scrollpane.setVisible(true);
            bms_web_scrollpane.setVisible(false);
            bms_web_atf.setVisible(false);
            bms_web_nr.setVisible(false);
            // bp_webseries.setVisible(false);
            bms_movies_lattest.setVisible(true);
            bms_standup_scrollpane.setVisible(false);
            bookingLanding.setVisible(false);
            bms_sports_scrollpane.setVisible(false);

        } else if (event.getSource() == webseries_btn) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);
            bp_general.setVisible(true);
            bms_movies_scrollpane.setVisible(false);
            bms_web_scrollpane.setVisible(true);
            bms_web_atf.setVisible(true);
            bms_web_nr.setVisible(true);
            //bp_webseries.setVisible(false);
            bms_movies_lattest.setVisible(false);
            bms_standup_scrollpane.setVisible(false);
            bookingLanding.setVisible(false);
            bms_sports_scrollpane.setVisible(false);

        } else if (event.getSource() == standup_btn) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);
            bp_general.setVisible(true);
            bms_movies_scrollpane.setVisible(false);
            bms_web_scrollpane.setVisible(false);
            bms_web_atf.setVisible(false);
            bms_web_nr.setVisible(false);
            //bp_webseries.setVisible(false);
            bms_movies_lattest.setVisible(false);
            bms_standup_scrollpane.setVisible(true);
            bookingLanding.setVisible(false);
            bms_sports_scrollpane.setVisible(false);

        } else if (event.getSource() == opera_btn) {

            signinpage.setVisible(false);
            signuppage.setVisible(false);
            bmsinfo.setVisible(false);
            bms_bookingPage.setVisible(true);
            bp_general.setVisible(true);
            bms_movies_scrollpane.setVisible(false);
            bms_web_scrollpane.setVisible(false);
            bms_web_atf.setVisible(false);
            bms_web_nr.setVisible(false);
            //bp_webseries.setVisible(false);
            bms_movies_lattest.setVisible(false);
            bms_standup_scrollpane.setVisible(false);
            bookingLanding.setVisible(false);
            bms_sports_scrollpane.setVisible(true);

        } else {

            signinpage.setVisible(true);
            signuppage.setVisible(false);
            bmsinfo.setVisible(true);
            bms_bookingPage.setVisible(false);
            bookingLanding.setVisible(false);
        }
    }

    public void logout(ActionEvent event) {
        if (event.getSource() == bms_logout_btn) {
            alert = new Alert(AlertType.NONE);
            alert.setHeaderText(null);
            alert.setTitle("BookMyShow");
            alert.setContentText("Thanks for visiting BookMyShow, see you again");

            signinpage.setVisible(true);
            signuppage.setVisible(false);
            bmsinfo.setVisible(true);
            bms_bookingPage.setVisible(false);
            bp_general.setVisible(false);
            bms_movies_scrollpane.setVisible(false);
            bms_web_scrollpane.setVisible(false);
            bms_web_atf.setVisible(false);
            bms_web_nr.setVisible(false);
            //bp_webseries.setVisible(false);
            bms_movies_lattest.setVisible(false);
            bms_standup_scrollpane.setVisible(false);
            bookingLanding.setVisible(false);
            bms_sports_scrollpane.setVisible(false);

        } else{
            alert = new Alert(AlertType.NONE);
            alert.setHeaderText(null);
            alert.setTitle("BookMyShow");
            alert.setContentText("Thanks for visiting BookMyShow, see you again");
            alert.showAndWait();
        }
    }







}

