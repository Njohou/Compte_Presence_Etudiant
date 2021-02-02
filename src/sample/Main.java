package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("View/HomeHoraire.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Accueil");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void test() {
        //1. Créer un Document vide
        XSSFWorkbook wb = new XSSFWorkbook();
        //2. Créer une Feuille de calcul vide
        Sheet feuille = wb.createSheet("new sheet");

        /*créer un nouveau font*/
        Font font = wb.createFont();
        //taille: 12px
        font.setFontHeightInPoints((short)14);
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
        row.createCell(5).setCellValue("TYPE");

        String [][] test = { {"1","33","12"},{"1","33","12"}  };
        int length = 5;
        for(int i = 0; i <= length; i++) {
            row = feuille.createRow(i + 1);
            for(int j=0; j<=5; j++){
                row.createCell(j).setCellValue("data" + i + j);
            }
        }

        //3. Créer une ligne et mettre qlq chose dedans
        // Row row = feuille.createRow((short)0);
        //4. Créer une Nouvelle cellule
        // Cell cell = row.createCell(0);
        //5. Donner la valeur
        // cell.setCellValue(1.2);

        // Ajouter d'autre cellule avec différents type
        // introw.createCell(1).setCellValue(3);
        // row.createCell(2).setCellValue('c');
        // row.createCell(3).setCellValue("chaine");
        // row.createCell(4).setCellValue(false);

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("nouveauFichier.xlsx");
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
