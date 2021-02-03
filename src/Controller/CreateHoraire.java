package Controller;

import Connexion.Dbconnector;
import Model.Horaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Cette classe gère la création des horaires
 *
 * @author Steve
 */
public class CreateHoraire implements Initializable {

    public int nbreID_cours = 0;
    public int nbreID_etudiant = 0;

    @FXML private Button Fermer;
    @FXML private ComboBox<String> CoursHoraire = new ComboBox();
    @FXML private ComboBox<String> NomEtudiant = new ComboBox();
    @FXML private ComboBox<String> typeHoraire;
    @FXML private DatePicker dateHoraire = new DatePicker();

    ObservableList ChoiceCours = FXCollections.observableArrayList();
    ObservableList ChoiceEtudiant = FXCollections.observableArrayList();
    ObservableList ChoiceType = FXCollections.observableArrayList("En retard", "Absent(e)", "Présent(e)");

    @FXML
    private void CreateHoraire(ActionEvent event) throws IOException {

       LocalDate date = dateHoraire.getValue();


       Horaire horaire = new Horaire();
       horaire.setDateCours(date.toString());
       horaire.setType(typeHoraire.getValue());
       if (nbreID_etudiant >= 0 && nbreID_etudiant < 10) {
           horaire.setIdEtudiant(Integer.parseInt(NomEtudiant.getValue().substring(0,1)));
       }else if (nbreID_etudiant >= 10 && nbreID_etudiant < 100) {
           horaire.setIdEtudiant(Integer.parseInt(NomEtudiant.getValue().substring(0,2)));
       }else if (nbreID_etudiant >= 100 && nbreID_etudiant < 1000) {
           horaire.setIdEtudiant(Integer.parseInt(NomEtudiant.getValue().substring(0,3)));
       }

        if (nbreID_cours >= 0 && nbreID_cours < 10) {
            horaire.setIdCours(Integer.parseInt(CoursHoraire.getValue().substring(0,1)));
        }else if (nbreID_cours >= 10 && nbreID_cours < 100) {
            horaire.setIdCours(Integer.parseInt(CoursHoraire.getValue().substring(0,2)));
        }else if (nbreID_cours >= 100 && nbreID_cours < 1000) {
            horaire.setIdCours(Integer.parseInt(CoursHoraire.getValue().substring(0,3)));
        }

        int status = Dbconnector.Enregistrer(horaire);

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'un état !!");
            alert.setContentText("l' état a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un état !!");
            alert.setContentText("l' état n' a pas été ajouté ");
            alert.showAndWait();
        }

        /** permet d'afficher la page d'accueil actualisée **/
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/HomeHoraire.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();
    }

    @FXML
    private void CloseHoraire(ActionEvent event) throws IOException {
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();

        Stage stage1 = new Stage();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/HomeHoraire.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Accueil");
        stage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            PopulateEtudiantComboBox();
            PopulateCoursComboBox();
            typeHoraire.setItems(ChoiceType);
            typeHoraire.setValue("Présent(e)");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PopulateEtudiantComboBox() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try {
            connection = Dbconnector.connect();
            String sql = "SELECT idEtudiant, nom, prenom FROM etudiant";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ChoiceEtudiant.clear();
            while (rs.next()) {
                ChoiceEtudiant.add(rs.getInt("idEtudiant") + " -" + " Nom : " + rs.getString("nom") +
                        ", " + "Prénom : " + rs.getString("prenom"));
                nbreID_etudiant++;
            }
            NomEtudiant.getItems().setAll(ChoiceEtudiant);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PopulateCoursComboBox() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try {
            connection = Dbconnector.connect();
            String sql = "SELECT idCours,nomCours,intitule, annee " +
                    "FROM cours NATURAL JOIN semestre;";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ChoiceCours.clear();
            while (rs.next()) {
                ChoiceCours.add(rs.getInt("idCours") + " -" + " Nom du cours : " + rs.getString("nomCours") +
                        ", N° semestre : " + rs.getString("intitule") + ", Année : " + rs.getInt("annee"));
                nbreID_cours++;
            }
            CoursHoraire.getItems().setAll(ChoiceCours);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
