package com.yashen.wms.contraller;

import com.jfoenix.controls.JFXComboBox;
        import com.jfoenix.controls.JFXPasswordField;
        import com.jfoenix.controls.JFXTextField;
import com.yashen.wms.model.AccountModel;
import com.yashen.wms.to.Account;
import com.yashen.wms.util.Navigation;
        import com.yashen.wms.util.Routes;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class MainViewFormContraller implements Initializable {

    public Label timeLbl;
    public Label dateLbll;
    @FXML
    private AnchorPane context;

    @FXML
    private JFXTextField userNameTxt;

    @FXML
    private JFXPasswordField passwordtxt;

    @FXML
    private JFXComboBox<String> accountTypeCmb;

    private Date currentDate;
    private SimpleDateFormat dateFomat;
    private SimpleDateFormat timeFomat;

    public MainViewFormContraller(){
        currentDate = new Date();
        dateFomat = new SimpleDateFormat("MM/dd/yyyy");
        timeFomat = new SimpleDateFormat("h:mm a");

    }

    @FXML
    void signInBtnOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        boolean isValied = isValied();
        if (isValied){
            System.out.println("match");
        }else {
            new Alert(Alert.AlertType.ERROR,"Your Username or Password is incorrect. try again!",
                    ButtonType.OK).showAndWait();
        }

    }



    private boolean isValied() throws SQLException, ClassNotFoundException, IOException {
        Account user = AccountModel.getUser(userNameTxt.getText().trim());
        if (userNameTxt.getText().trim().equals(user.getUserName()) && passwordtxt.getText().trim().equals(user.getPassword())
        && accountTypeCmb.getValue().equals(user.getAccType())){

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/yashen/wms/view/AdminMainForm.fxml"));
            Parent parent = fxmlLoader.load();
            AdminMainFormContraller controller = fxmlLoader.getController();
            controller.nameLbl.setText(user.getFirstName()+"  "+user.getLastname());
            controller.accountLbl.setText(user.getAccType());
            Stage stage = (Stage)context.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            /*FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.load(getClass().getResource("/com/yashen/wms/view/AdminMainForm.fxml"));
            AdminMainFormContraller controller = fxmlLoader.getController();
            controller.nameLbl.setText(user.getFirstName()+"  "+user.getLastname());
            controller.accountLbl.setText(user.getAccType());*/

            return true;

        }else{
            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountTypeCmb.getItems().addAll("Admin","Manager","Worker");
        setDate();
        setTime();
    }
    private void setTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    timeLbl.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setDate() {
        dateLbll.setText(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));
    }
}
