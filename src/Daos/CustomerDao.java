package Daos;

import Models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Implémentation concrète de Dao pour les objets Customer
 */
public class CustomerDao extends Dao<Customer, Integer> {

    public CustomerDao() throws Exception {
        super(); // Appelle le constructeur parent qui initialise la connexion JDBC
    }

    @Override
    public Customer create(Customer customer) {
        String sql = "INSERT INTO client (name, address) VALUES (?, ?)";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.name);
            statement.setString(2, customer.address);

            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                // Récupérer l'ID généré
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        return new Customer(generatedId, customer.name, customer.address);
                    }
                }
            }
            
            throw new SQLException("Échec de la création du client");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du client: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Customer> read(Integer customerId) {
        String sql = "SELECT * FROM client WHERE id = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    
                    return Optional.of(new Customer(id, name, address));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du client: " + e.getMessage(), e);
        }
        
        return Optional.empty(); // Client non trouvé
    }

    @Override
    public ArrayList<Customer> readAll() {
        ArrayList<Customer> client = new ArrayList<>();
        String sql = "SELECT * FROM client";
        
        try (Statement statement = jdbcConnect.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                client.add(new Customer(id, name, address));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture de tous les client: " + e.getMessage(), e);
        }
        
        return client;
    }

    @Override
    public Customer update(Customer customer) {
        String sql = "UPDATE client SET name = ?, address = ? WHERE id = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setString(1, customer.name);
            statement.setString(2, customer.address);
            statement.setInt(3, customer.id);

            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                return customer; // Retourne le client mis à jour
            } else {
                throw new SQLException("Aucun client trouvé avec l'ID: " + customer.id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du client: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Integer customerId) {
        String sql = "DELETE FROM client WHERE id = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            
            int rowsAffected = statement.executeUpdate();
            
            return rowsAffected > 0; // Retourne true si au moins une ligne a été supprimée
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du client: " + e.getMessage(), e);
        }
    }

    /**
     * Méthode utilitaire pour vérifier si un client existe
     * @param customerId l'ID du client à vérifier
     * @return true si le client existe, false sinon
     */
    public boolean exists(Integer customerId) {
        return read(customerId).isPresent();
    }
}