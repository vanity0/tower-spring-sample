package io.iamcyw.tower.todo.commond;

import io.iamcyw.tower.todo.entity.TodoItem;

import java.io.Serializable;

public class UpdateTodoItem implements Serializable {

    private final TodoItem todoItem;

    public UpdateTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }


}
