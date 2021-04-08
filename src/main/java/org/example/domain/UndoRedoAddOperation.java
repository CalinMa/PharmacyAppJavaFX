package org.example.domain;

import org.example.repository.IRepository;

public class UndoRedoAddOperation <T extends Entity>implements UndoRedoOperation{


    private IRepository<T> repository;
    private T entity;
    public UndoRedoAddOperation(IRepository<T> repository, T entity){
        this.repository = repository;
        this.entity = entity;

    }


    @Override
    public void doUndo() throws Exception {
        this.repository.delete(entity.getIdEntity());
    }

    @Override
    public void doRedo() throws Exception {
        this.repository.create(entity);
    }
}
