package Controller;

import Connexion.Dbconnector;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * Cette classe gère l'ecran principal de notre application
 *
 *chargé de presenter l'etat des étudiants à chaque cours
 * @author Steve
 */
public class AfficheHoraire implements Initializable {

    /**
     * Cette fonction est en charge d'afficher la liste des semestres crées
     */
    @FXML
    public void GotoShowSemestre() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/ListSemestre.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des semestres");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'afficher la liste des semestres crées
     */
    @FXML
    public void GotoCreateSemestre() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/CreateSemestre.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau semestre");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'afficher la liste des cours crées
     */
    @FXML
    public void GotoShowCours() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/ListCours.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des cours");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'afficher l'interface de création des cours
     */
    @FXML
    public void GotoCreateCours() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/CreateCours.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau cours");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'afficher la liste des étudiants crées
     */
    @FXML
    public void GotoShowEtudiant() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/ListEleves.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des étudiants");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'afficher l'interface de création des étudiants
     */
    @FXML
    public void GotoCreateEtudiant() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/CreerEleve.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouvel étudiant");
        stage.show();
    }

    /**
     * Cette fonction est en charge d'assigner(present, absent, retard) un état à un étudiant specifique
     */
    @FXML
    public void OpenCreateMethod(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageEmploye
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/CreateHoraire.fxml"));
            //stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Créer un nouvel état");
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Cette fonction est en charge d'initialiser le tableau d'état des étudiants
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage();
    }

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

    /**
     * Cette fonction est en charge de recuperer les donner dans la base de donnée
     * Et de les afficher dans le table View
     */
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
        /** Insertion des différentes données recupérées en BDD dans les différentes colonnes du tableau **/
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

    /**
     * Cette fonction est en charge d'appeller notre fonction en charge d'exporter nos données
     */
    @FXML public void exportData() {
        test(ListHoraire);
    }

    /**
     * Cette fonction est en charge d'exporter tous les données d'une table view vers notre fichier excel
     */
    public static void test(ObservableList<ShowHoraire> myList) {
        //1. Créer un Document vide
        XSSFWorkbook wb = new XSSFWorkbook();
        //2. Créer une Feuille de calcul vide
        Sheet feuille = wb.createSheet("new sheet");

        /*créer un nouveau font*/
        Font font = wb.createFont();
        //taille: 12px
        font.setFontHeightInPoints((short)12);
        font.setFontName("Courier New");
        font.setBold(true);

        /*création d'un nouveau style*/
        CellStyle cs = wb.createCellStyle();
        cs.setFont(font);

        Row row = feuille.createRow((short) 0);
        row.setRowStyle(cs);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("NOM");
        row.createCell(2).setCellValue("PRENOM");
        row.createCell(3).setCellValue("DATE");
        row.createCell(4).setCellValue("COURS");
        row.createCell(5).setCellValue("SEMESTRE");
        row.createCell(6).setCellValue("ANNEE");
        row.createCell(7).setCellValue("TYPE");

        for(int i = 0; i < myList.size(); i++) {
            row = feuille.createRow(i+1);
            row.createCell(0).setCellValue(myList.get(i).getIdHoraire());
            row.createCell(1).setCellValue(myList.get(i).getNomEtud());
            row.createCell(2).setCellValue(myList.get(i).getPrenomEtud());
            row.createCell(3).setCellValue(myList.get(i).getDateEtat());
            row.createCell(4).setCellValue(myList.get(i).getNomCours());
            row.createCell(5).setCellValue(myList.get(i).getNomSemestre());
            row.createCell(6).setCellValue(myList.get(i).getAnnee());
            row.createCell(7).setCellValue(myList.get(i).getType());
        }

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("nouveauFichier.xlsx");
            wb.write(fileOut);
            fileOut.close();
            /** Message d'alerte pour signaler que le fichier a été créé **/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export d'un fichier");
            alert.setContentText("Les données ont bien été exportées !!");
            alert.showAndWait();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
