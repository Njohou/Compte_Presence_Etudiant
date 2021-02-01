package Controller;

import Connexion.Dbconnector;
import Model.ShowCours;
import Model.ShowHoraire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AfficheHoraire implements Initializable {
    /** Afficher la liste des semestres **/
    @FXML
    public void GotoShowSemestre() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ListSemestre.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des semestres");
        stage.show();
    }

    @FXML
    public void GotoCreateSemestre() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreateSemestre.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau semestre");
        stage.show();
    }

    @FXML
    public void GotoModifyandDeleteSemestre() throws IOException {

    }

    @FXML
    public void GotoShowCours() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ListCours.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des cours");
        stage.show();
    }

    @FXML
    public void GotoCreateCours() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreateCours.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau cours");
        stage.show();
    }

    @FXML
    public void GotoModifyandDeleteCours() throws IOException {

    }
    /** Afficher la liste des éltudiants **/
    @FXML
    public void GotoShowEtudiant() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/ListEleves.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des étudiants");
        stage.show();
    }
    /** Afficher la création des étudiants **/
    @FXML
    public void GotoCreateEtudiant() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreerEleve.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouvel étudiant");
        stage.show();
    }

    @FXML
    public void GotoModifyandDeleteEtudiant() throws IOException {

    }

    @FXML
    public void OpenCreateMethod(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageEmploye
            Parent root = FXMLLoader.load(getClass().getResource("../View/CreateHoraire.fxml"));
            //stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Créer un nouvel état");
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage();
    }

    /** Affichage des éléments d'un état **/
    @FXML
    private TableView<ShowHoraire> tableHoraire;
    @FXML private TableColumn<ShowHoraire, Integer> idHoraire;
    @FXML private TableColumn<ShowHoraire, String> nomEtudiant;
    @FXML private TableColumn<ShowHoraire, String> prenomEtudiant;
    @FXML private TableColumn<ShowHoraire, String> dateCours;
    @FXML private TableColumn<ShowHoraire, String> nomCours;
    @FXML private TableColumn<ShowHoraire, String> semestreHoraire;
    @FXML private TableColumn<ShowHoraire, Integer> anneeHoraire;
    @FXML private TableColumn<ShowHoraire, String> typeHoraire;

    public ObservableList<ShowHoraire> ListHoraire = FXCollections.observableArrayList();

    public void Affichage() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try {
            connection = Dbconnector.connect();
            String sql = "SELECT idHoraire, nom, prenom, dateRetard, nomCours, intitule, annee, types " +
                    "FROM etudiant NATURAL JOIN horaire NATURAL JOIN cours NATURAL JOIN semestre";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListHoraire.clear();
            while (rs.next()) {
                ListHoraire.add(new ShowHoraire(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                        rs.getString(8)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        idHoraire.setCellValueFactory(new PropertyValueFactory<ShowHoraire, Integer>("idHoraire"));
        nomEtudiant.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("nomEtud"));
        prenomEtudiant.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("prenomEtud"));
        dateCours.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("dateEtat"));
        nomCours.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("nomCours"));
        semestreHoraire.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("nomSemestre"));
        anneeHoraire.setCellValueFactory(new PropertyValueFactory<ShowHoraire, Integer>("annee"));
        typeHoraire.setCellValueFactory(new PropertyValueFactory<ShowHoraire, String>("type"));
        tableHoraire.setItems(ListHoraire);
    }
}
