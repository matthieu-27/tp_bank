package Daos;

import Models.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Implémentation concrète de Dao pour les objets Account
 */
public class AccountDao extends Dao<Account, String> {

    public AccountDao() throws Exception {
        super(); // Appelle le constructeur parent qui initialise la connexion JDBC
    }

    @Override
    public Account create(Account account) {
        String sql = "INSERT INTO account (number, balance, bank_id) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setString(1, account.accountNumber);
            statement.setDouble(2, account.balance);
            statement.setInt(3, account.bankId);
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                return account; // Retourne le compte créé
            } else {
                throw new SQLException("Échec de la création du compte");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création du compte: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Account> read(String accountNumber) {
        String sql = "SELECT * FROM account WHERE number = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setString(1, accountNumber);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String accNumber = resultSet.getString("number");
                    double balance = resultSet.getDouble("balance");
                    int bankId = resultSet.getInt("bank_id");
                    
                    return Optional.of(new Account(accNumber, balance, bankId));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du compte: " + e.getMessage(), e);
        }
        
        return Optional.empty(); // Compte non trouvé
    }

    @Override
    public ArrayList<Account> readAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        
        try (Statement statement = jdbcConnect.connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                String accountNumber = resultSet.getString("number");
                double balance = resultSet.getDouble("balance");
                int bankId = resultSet.getInt("bank_id");
                
                accounts.add(new Account(accountNumber, balance, bankId));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture de tous les comptes: " + e.getMessage(), e);
        }
        
        return accounts;
    }

    @Override
    public Account update(Account account) {
        String sql = "UPDATE account SET balance = ?, bank_id = ? WHERE number = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setDouble(1, account.balance);
            statement.setInt(2, account.bankId);
            statement.setString(3, account.accountNumber);
            
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                return account; // Retourne le compte mis à jour
            } else {
                throw new SQLException("Aucun compte trouvé avec le numéro: " + account.accountNumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du compte: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String accountNumber) {
        String sql = "DELETE FROM account WHERE number = ?";
        
        try (PreparedStatement statement = jdbcConnect.connection.prepareStatement(sql)) {
            statement.setString(1, accountNumber);
            
            int rowsAffected = statement.executeUpdate();
            
            return rowsAffected > 0; // Retourne true si au moins une ligne a été supprimée
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du compte: " + e.getMessage(), e);
        }
    }

    /**
     * Méthode utilitaire pour vérifier si un compte existe
     * @param accountNumber le numéro de compte à vérifier
     * @return true si le compte existe, false sinon
     */
    public boolean exists(String accountNumber) {
        return read(accountNumber).isPresent();
    }
}
