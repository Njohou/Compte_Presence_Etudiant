package Controller;

import Connexion.Dbconnector;
import Model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ListEleves implements Initializable {

    @FXML private TableView<Etudiant> tableEtud;
    @FXML private TableColumn<Etudiant, Integer> idEtud;
    @FXML private TableColumn<Etudiant, String> nom;
    @FXML private TableColumn<Etudiant, String> prenom;
    @FXML private TableColumn<Etudiant, String> sexe;
    @FXML private TableColumn<Etudiant, Integer> telephone;

    public ObservableList<Etudiant> ListEtud = FXCollections.observableArrayList();

    public void Affichage() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;
        try{
            connection = Dbconnector.connect();
            String sql = "SELECT * FROM etudiant";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListEtud.clear();
            while (rs.next()) {
                ListEtud.add(new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        idEtud.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("idEtud"));
        nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        sexe.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("sexe"));
        telephone.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("telephone"));
        tableEtud.setItems(ListEtud);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage();
    }
}
