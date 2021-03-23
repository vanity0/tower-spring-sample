package io.iamcyw.tower.todo.commond;

import io.iamcyw.tower.todo.entity.TodoItem;

import java.io.Serializable;

public class AddNewTodoItem implements Serializable {
    private final TodoItem newItem;

    public AddNewTodoItem(TodoItem newItem) {
        this.newItem = newItem;
    }

    public TodoItem getNewItem() {
        return newItem;
    }


}
