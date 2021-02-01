package Controller;

import Connexion.Dbconnector;
import Model.ShowCours;
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

public class ListCours implements Initializable {

    @FXML
    private TableView<ShowCours> tableCours;
    @FXML private TableColumn<ShowCours, Integer> idCours;
    @FXML private TableColumn<ShowCours, String> intituleCours;
    @FXML private TableColumn<ShowCours, String> semestreCours;
    @FXML private TableColumn<ShowCours, Integer> anneeCours;

    public ObservableList<ShowCours> ListCours = FXCollections.observableArrayList();
    public void Affichage() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try{
            connection = Dbconnector.connect();
            String sql = "SELECT idCours,nomCours,intitule, annee " +
                    "FROM cours NATURAL JOIN semestre;";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListCours.clear();
            while (rs.next()) {
                ListCours.add(new ShowCours(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        idCours.setCellValueFactory(new PropertyValueFactory<ShowCours, Integer>("idCours"));
        intituleCours.setCellValueFactory(new PropertyValueFactory<ShowCours, String>("nomCours"));
        semestreCours.setCellValueFactory(new PropertyValueFactory<ShowCours, String>("intitule"));
        anneeCours.setCellValueFactory(new PropertyValueFactory<ShowCours, Integer>("annee"));
        tableCours.setItems(ListCours);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage();
    }
}
