package Controller;

import Connexion.Dbconnector;
import Model.Semestre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateSemestre implements Initializable {

    @FXML private TextField anneeSemestre;
    ObservableList<String> ListSemestre = FXCollections.observableArrayList("semestre 1", "semestre 2");
    @FXML private ComboBox<String> choiceSemestre;

    @FXML private Button Fermer;

    @FXML
    public void CreateSemestre(ActionEvent event) throws IOException {
        int annee = Integer.parseInt(anneeSemestre.getText());

        Semestre semestre = new Semestre();
        semestre.setAnnee(annee);
        semestre.setIntitule(choiceSemestre.getValue());

        int status = Dbconnector.Enregistrer(semestre);

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un semestre !!");
            alert.setHeaderText("Information");
            alert.setContentText("le semestre a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un semestre !!");
            alert.setHeaderText("Information");
            alert.setContentText("le semestre n' a pas été ajouté ");
            alert.showAndWait();
        }
    }

    @FXML
    public void CloseSemestre() throws IOException {
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            choiceSemestre.setItems(ListSemestre);
            choiceSemestre.setValue("semestre 1");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
