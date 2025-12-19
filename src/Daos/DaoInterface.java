package Daos;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Interface générique pour les opérations CRUD sur les objets de type T
 * @param <T> le type d'objet à gérer
 * @param <K> le type de la clé primaire
 */
public interface DaoInterface<T, K> {
    
    /**
     * Crée un nouvel enregistrement dans la base de données
     * @param entity l'entité à créer
     * @return l'entité créée avec son ID généré
     */
    T create(T entity);
    
    /**
     * Récupère une entité par son identifiant
     * @param id l'identifiant de l'entité
     * @return un Optional contenant l'entité si trouvée, vide sinon
     */
    Optional<T> read(K id);
    
    /**
     * Récupère toutes les entités
     * @return une liste de toutes les entités
     */
    ArrayList<T> readAll();
    
    /**
     * Met à jour une entité existante
     * @param entity l'entité à mettre à jour
     * @return l'entité mise à jour
     */
    T update(T entity);
    
    /**
     * Supprime une entité
     * @param id l'identifiant de l'entité à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean delete(K id);
}