package faculty.project.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

        @FXML
        private TextField login;

        @FXML
        private PasswordField password;


        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        void onClick(ActionEvent event) {
                System.out.println(login.getText());
                System.out.println(password.getText());
        }

        @FXML
        void initialize() {

        }
}
