package Controller;

import Connexion.Dbconnector;
import Model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreerEleve implements Initializable {

    /** Recupèrer les données entrées sur l'interface **/
    @FXML private TextField nomEtudiant;
    @FXML private TextField prenomEtudiant;
    @FXML private TextField telEtudiant;
    @FXML private Button Fermer;

    ObservableList<String> ChoiceSexe = FXCollections.observableArrayList("M","F");
    @FXML private ComboBox<String> choiceSexe;

    /** Méthode de création d'un étudiant **/
    @FXML
    public void CreateEtudiant(ActionEvent event) throws IOException {
        String nom = nomEtudiant.getText();
        String prenom = prenomEtudiant.getText();
        int tel = Integer.parseInt(telEtudiant.getText());

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setTelephone(tel);
        etudiant.setSexe(choiceSexe.getValue());

        int status = Dbconnector.Enregistrer(etudiant);

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un étudiant !!");
            alert.setHeaderText("Information");
            alert.setContentText("l'étudiant a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un étudiant !!");
            alert.setHeaderText("Information");
            alert.setContentText("l'étudiant n' a pas été ajouté ");
            alert.showAndWait();
        }
    }

    /** Méthode pour  **/
    @FXML
    public void CloseEtudiant() throws IOException {
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            choiceSexe.setItems(ChoiceSexe);
            choiceSexe.setValue("M");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
