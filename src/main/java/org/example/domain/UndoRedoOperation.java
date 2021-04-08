package org.example.domain;

public interface UndoRedoOperation {
    void doUndo() throws Exception;
    void doRedo() throws Exception;

}
