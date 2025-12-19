package Daos;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Classe abstraite générique implémentant les opérations CRUD de base
 * @param <T> le type d'entité à gérer
 * @param <K> le type de la clé primaire
 */
public abstract class Dao<T, K> implements DaoInterface<T, K> {

    protected JdbcConnect jdbcConnect;

    public Dao() throws Exception {
        this.jdbcConnect = new JdbcConnect();
    }

    @Override
    public T create(T entity) {
        // Implémentation par défaut - à surcharger par les classes concrètes
        throw new UnsupportedOperationException("Méthode create() non implémentée");
    }

    @Override
    public Optional<T> read(K id) {
        // Implémentation par défaut - à surcharger par les classes concrètes
        throw new UnsupportedOperationException("Méthode read() non implémentée");
    }

    @Override
    public ArrayList<T> readAll() {
        // Implémentation par défaut - à surcharger par les classes concrètes
        throw new UnsupportedOperationException("Méthode readAll() non implémentée");
    }

    @Override
    public T update(T entity) {
        // Implémentation par défaut - à surcharger par les classes concrètes
        throw new UnsupportedOperationException("Méthode update() non implémentée");
    }

    @Override
    public boolean delete(K id) {
        // Implémentation par défaut - à surcharger par les classes concrètes
        throw new UnsupportedOperationException("Méthode delete() non implémentée");
    }

    /**
     * Ferme la connexion à la base de données
     */
    public void close() throws Exception {
        if (jdbcConnect != null) {
            jdbcConnect.close();
        }
    }
}
