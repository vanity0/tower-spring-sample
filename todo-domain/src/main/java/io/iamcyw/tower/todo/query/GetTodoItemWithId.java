package io.iamcyw.tower.todo.query;

import java.io.Serializable;

public class GetTodoItemWithId implements Serializable {
    private final String id;

    public GetTodoItemWithId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


}
