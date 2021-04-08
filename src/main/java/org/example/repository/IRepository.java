package org.example.repository;

import org.example.domain.Entity;
import java.util.List;
// generic type T will only be allowed for Entity classes <T extends Entity>
public interface IRepository <T extends Entity>{
    // CRUD Methods = Create, Read, Update, Delete
    /**
     * Adds an entity to the repository.
     * @param entity the entity to add
     * @throws Exception if the id already exists
     */
    void create(T entity) throws Exception;

    /**
     * Gets a entity with a given id.
     * @param id the id
     * @return the entity with the given id, or null if none exists
     */
     T readOne(int id);

    /**
     * Returns all entities.
     * @return all entities.
     */
    List<T> read();

    /**
     * Updates a given entity by its id.
     * @param entity the given entity.
     * @throws Exception if the entity id does not exist.
     */
    void update(T entity) throws Exception;

    /**
     * Deletes an entity with a given id.
     * @param id the id of the entity to delete.
     * @throws Exception if there is no entity with the given id.
     */
     void delete(int id) throws Exception ;
}
