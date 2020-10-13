package sample.business;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.models.FootballPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class TeamBusiness {
    List<FootballPlayer> playersList= new ArrayList();
    String imagePath = " ";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPlayerName;

    @FXML
    private TextField txtPlayerNationality;

    @FXML
    private TextField txtPlayerBirthday;

    @FXML
    private TextField txtPlayerHeight;

    @FXML
    private TextField txtPlayerWeight;

    @FXML
    private Button btnChooseImage;

    @FXML
    private TextField txtPlayerNumber;

    @FXML
    private ComboBox<String> choiceBox;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSearch;

    @FXML
    private ImageView imgPlayerImage;

    @FXML
    private TableView<FootballPlayer> tableView;

    @FXML
    private TableColumn<FootballPlayer, String> posColumn;

    @FXML
    private TableColumn<FootballPlayer, String> playerNameColumn;

    @FXML
    private TableColumn<FootballPlayer, String> playerNumberColumn;

    @FXML
    private Button btnExit;

    String[] choiceBoxList = {"GK", "DF", "MF", "ST"};

    @FXML
    void addPlayer(ActionEvent event) {
        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setName(txtPlayerName.getText());
        footballPlayer.setNationality(txtPlayerNationality.getText());
        footballPlayer.setDateOfBirth(txtPlayerBirthday.getText());
        footballPlayer.setHeight(txtPlayerHeight.getText());
        footballPlayer.setWeight(txtPlayerWeight.getText());
        footballPlayer.setNumber(txtPlayerNumber.getText());
        footballPlayer.setPosition(choiceBox.getSelectionModel().selectedItemProperty().getValue());
        footballPlayer.setImagePath(imagePath);
        for (FootballPlayer player : playersList) {
            boolean exist = player.getName().equals(txtPlayerName.getText()) || player.getNumber().equals(txtPlayerNumber.getText());
            boolean blank = txtPlayerName.getText().equals("") || txtPlayerNationality.getText().equals("") ||
                    txtPlayerBirthday.getText().equals("") || txtPlayerHeight.getText().equals("") || txtPlayerWeight.getText().equals("") ||
                    txtPlayerNumber.getText().equals("") || choiceBox.getSelectionModel().selectedItemProperty().getValue().equals("");
            if (exist || blank){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("System Information");
                alert.setContentText("This player is exist or something is blank! Please try again");
                alert.show();
                return;
            }
        }
        playersList.add(footballPlayer);
        IOFile.writePlayerToFile(playersList, "Players.dat");
    }

    @FXML
    void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("D:/Codegym/CodeGym.Case_Module_2/src/sample/view"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imagePath = file.toURI().toString();
            Image image = new Image(imagePath);
            imgPlayerImage.setImage(image);
        }
    }

    @FXML
    void editPlayer(ActionEvent event) {

    }

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void removePlayer(ActionEvent event) {

    }

    @FXML
    void searchPlayer(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtPlayerName != null : "fx:id=\"txtPlayerName\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerNationality != null : "fx:id=\"txtPlayerNationality\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerBirthday != null : "fx:id=\"txtPlayerBirthday\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerHeight != null : "fx:id=\"txtPlayerHeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerWeight != null : "fx:id=\"txtPlayerWeight\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnChooseImage != null : "fx:id=\"btnChooseImage\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert txtPlayerNumber != null : "fx:id=\"txtPlayerNumber\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnRemove != null : "fx:id=\"btnRemove\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnSearch != null : "fx:id=\"btnRemove1\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert posColumn != null : "fx:id=\"posCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert playerNameColumn != null : "fx:id=\"playerNameCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert playerNumberColumn != null : "fx:id=\"playerNumberCollum\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'TeamBusiness.fxml'.";
        try{
            playersList = IOFile.readPlayerFromFile("Players.dat");
        }   catch (Exception e){
            e.printStackTrace();
        }
        posColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerNumberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        tableView.getItems().addAll(playersList);
        choiceBox.getItems().addAll(choiceBoxList);
    }
}
