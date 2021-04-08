package org.example.repository;

import org.example.domain.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity> implements IRepository<T>{
    private Map<Integer, T> storage = new HashMap<>();

    // CRUD Methods = Create, Read, Update, Delete

    /**
     * Adds an entity to the repository.
     * @param entity the entity to add
     * @throws Exception if the id already exists
     */
    public void create(T entity) throws Exception {
        if (this.storage.containsKey(entity.getIdEntity())) {
            throw new Exception("The id already exists!");
        }

        this.storage.put(entity.getIdEntity(), entity);
    }
    /**
     * Gets a entity with a given id.
     * @param id the id
     * @return the entity with the given id, or null if none exists
     */
    public T readOne(int id) {
        return this.storage.get(id);
    }

    /**
     * Returns all entities.
     * @return all entities.
     */
    public List<T> read() {
        return new ArrayList<>(this.storage.values());
    }

    /**
     * Updates a given entity by its id.
     * @param entity the given entity.
     * @throws Exception if the entity id does not exist.
     */
    public void update(T entity) throws Exception {
        if (!this.storage.containsKey(entity.getIdEntity())) {
            throw new Exception("There is no entity with the given id to update!");
        }

        this.storage.put(entity.getIdEntity(), entity);
    }

    /**
     * Deletes an entity with a given id.
     * @param id the id of the entity to delete.
     * @throws Exception if there is no drug with the given id.
     */
    public void delete(int id) throws Exception {
        if (!this.storage.containsKey(id)) {
            throw new Exception("There is no entity with the given id to delete!");
        }

        this.storage.remove(id);
    }
}
