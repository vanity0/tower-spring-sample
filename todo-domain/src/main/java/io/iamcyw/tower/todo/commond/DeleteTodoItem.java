package io.iamcyw.tower.todo.commond;

import java.io.Serializable;

public class DeleteTodoItem implements Serializable {
    private final String id;

    public DeleteTodoItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
