package Connexion;

import Model.Cours;
import Model.Etudiant;
import Model.Horaire;
import Model.Semestre;

import java.sql.*;

public class Dbconnector {
    static Connection connection;

    public static Connection connect() throws SQLException {
        /** Se connecter à la base de donnée **/
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/controle-horaire",
                    "root",
                    ""
            );
        }catch (SQLException e) {
            e.printStackTrace();
            }
            return connection;
        }

    /*****************  Etudiant  *******************/
    public static int Enregistrer(Etudiant etudiant) {
        int st = 0;
        try {
            String sql = "INSERT INTO etudiant(nom, prenom, sexe, telephone) VALUES (?,?,?,?)";
            Connection connection = Dbconnector.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1, etudiant.getNom());
            stat.setString(2, etudiant.getPrenom());
            stat.setString(3, etudiant.getSexe());
            stat.setInt(4, etudiant.getTelephone());

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.printf(String.valueOf(st));
        return st;
    }

    /********************** Semestre ***********************/
    public static int Enregistrer(Semestre semestre) {
        int st = 0;
        try {
            String sql = "INSERT INTO semestre(intitule, annee) VALUES (?,?)";
            Connection connection = Dbconnector.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1, semestre.getIntitule());
            stat.setInt(2, semestre.getAnnee());

            st = stat.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf(String.valueOf(st));
        return st;
    }

    /********************** Cours ***********************/
    public static int Enregistrer(Cours cours) {
        int st = 0;
        try {
            String sql = "INSERT INTO cours(nomCours, idSemestre) VALUES (?,?)";
            Connection connection = Dbconnector.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1, cours.getIntitule());
            stat.setInt(2, cours.getIdSemestre());

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /************************** Horaire *****************************/
    public static int Enregistrer(Horaire horaire) {
        int st = 0;
        try {
            String sql = "INSERT INTO horaire(idCours, idEtudiant, types, dateRetard) VALUES (?,?,?,?)";
            Connection connection = Dbconnector.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setInt(1, horaire.getIdCours());
            stat.setInt(2, horaire.getIdEtudiant());
            stat.setString(3, horaire.getType());
            stat.setString(4, horaire.getDateCours());

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }
}




