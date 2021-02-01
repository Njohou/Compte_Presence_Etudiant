package Controller;

import Connexion.Dbconnector;
import Model.Cours;
import Model.Semestre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CreateCours implements Initializable {

    private int nbre_id = 0;
    @FXML private TextField intituleCours;
    @FXML private ComboBox<String> choiceSemestre = new ComboBox();

    ObservableList ChoiceSemestre = FXCollections.observableArrayList();
    @FXML private Button Fermer;

    @FXML
    public void CreateCours() throws IOException {

        String intitule = intituleCours.getText();

        Cours cours = new Cours();
        cours.setIntitule(intitule);
        /** recupère la première valeur du champs qui est l'id, ensuite qui est converti en Integer **/
        System.out.println("La taille de l'id : " + nbre_id);
        if (nbre_id >= 0 && nbre_id < 10) {
            cours.setIdSemestre(Integer.parseInt(choiceSemestre.getValue().substring(0,1)));
        }else if (nbre_id >= 10 && nbre_id < 100) {
            cours.setIdSemestre(Integer.parseInt(choiceSemestre.getValue().substring(0,2)));
        }else if (nbre_id >= 100 && nbre_id < 1000) {
            cours.setIdSemestre(Integer.parseInt(choiceSemestre.getValue().substring(0,3)));
        }

        int status = Dbconnector.Enregistrer(cours);

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un cours !!");
            alert.setContentText("le cours a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un cours !!");
            alert.setContentText("le cours n' a pas été ajouté ");
            alert.showAndWait();
        }
    }

    @FXML
    public void CloseCours() throws IOException {
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            PopulateCombox();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    /** Fonction pour remplir le comboBox lors de son affichage **/
    public void PopulateCombox() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try {
            connection = Dbconnector.connect();
            String sql = "SELECT * FROM semestre";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ChoiceSemestre.clear();
            while (rs.next()) {
                ChoiceSemestre.add(rs.getInt("idSemestre") + " -" + " Semestre : " + rs.getString("intitule") + ", " + "Année : " + rs.getInt("annee"));
                System.out.println(rs.getString("intitule") + " "+ rs.getInt("annee"));
                nbre_id++;
            }
            /** Mettre les différents champs recupérés en BDD dans le comboBox **/
            choiceSemestre.getItems().setAll(ChoiceSemestre);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
