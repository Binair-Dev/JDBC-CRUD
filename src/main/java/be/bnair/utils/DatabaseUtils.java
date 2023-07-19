package be.bnair.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseUtils {
    public static void createTableIfNotExists() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS test (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nom VARCHAR(50)," +
                    "age INT)";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table créée avec succès (ou déjà existante).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAllFromTable() {
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {

            String selectQuery = "SELECT * FROM test";
            ResultSet resultSet = stmt.executeQuery(selectQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Age: " + age);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoTable(String nom, int age) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO test (nom, age) VALUES (?, ?)")) {

            pstmt.setString(1, nom);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

            System.out.println("Informations ajoutées avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromTable(int id) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM test WHERE id = ?")) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Informations supprimées avec succès.");
            } else {
                System.out.println("Aucune information trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTable(int id, String nom, int age) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE test SET nom = ?, age = ? WHERE id = ?")) {

            pstmt.setString(1, nom);
            pstmt.setInt(2, age);
            pstmt.setInt(3, id);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Informations mises à jour avec succès.");
            } else {
                System.out.println("Aucune information trouvée avec cet ID, mise à jour impossible.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
