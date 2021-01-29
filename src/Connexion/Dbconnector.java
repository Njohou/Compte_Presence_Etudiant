package Connexion;

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
    }
