package org.example.service;

import org.example.domain.UndoRedoOperation;

import java.util.Stack;

public class UndoRedoManager {

    private final Stack<UndoRedoOperation> undoOperation = new Stack<>();
    private final Stack<UndoRedoOperation> redoOperation = new Stack<>();

    public void doUndo () throws Exception {
        if (!undoOperation.isEmpty()) {
          UndoRedoOperation operation = undoOperation.pop();
        operation.doUndo();
        redoOperation.push(operation);
        }
    }
    public void doRedo() throws Exception {
        if (!redoOperation.isEmpty()){
            UndoRedoOperation operation = redoOperation.pop();
            operation.doRedo();
            undoOperation.push(operation);
        }
    }
    public void addToUndo (UndoRedoOperation undoRedoOperation){
        undoOperation.push(undoRedoOperation);
        redoOperation.clear();
    }
}
