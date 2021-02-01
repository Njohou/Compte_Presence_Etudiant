package Controller;

import Connexion.Dbconnector;
import Model.Semestre;
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

public class ListSemestre implements Initializable {
    @FXML
    private TableView<Semestre> tableSemestre;
    @FXML private TableColumn<Semestre, Integer> idSemestre;
    @FXML private TableColumn<Semestre, String> labelSemestre;
    @FXML private TableColumn<Semestre, Integer> anneeSemestre;

    public ObservableList<Semestre> ListSemestre = FXCollections.observableArrayList();

    public void Affichage() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;

        try {
            connection = Dbconnector.connect();
            String sql = "SELECT * FROM semestre";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListSemestre.clear();
            while (rs.next()) {
                ListSemestre.add(new Semestre(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        idSemestre.setCellValueFactory(new PropertyValueFactory<Semestre, Integer>("idSemestre"));
        labelSemestre.setCellValueFactory(new PropertyValueFactory<Semestre, String>("intitule"));
        anneeSemestre.setCellValueFactory(new PropertyValueFactory<Semestre, Integer>("annee"));
        tableSemestre.setItems(ListSemestre);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage();
    }
}