package org.example.domain;

public abstract class Entity {
    private final  int idEntity;

    public Entity(int idEntity) {
        this.idEntity = idEntity;
    }

    public int getIdEntity() {
        return idEntity;
    }
}
