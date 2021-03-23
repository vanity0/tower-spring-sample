package io.iamcyw.tower.todo;

import io.iamcyw.tower.queryhandling.QueryHandler;
import io.iamcyw.tower.todo.entity.TodoItem;
import io.iamcyw.tower.todo.query.GetTodoItemWithId;
import io.iamcyw.tower.todo.query.QueryAllTodoItem;
import io.iamcyw.tower.todo.repository.TodoItemQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemQuery {

    private final TodoItemQueryRepository todoItemQueryRepository;

    public TodoItemQuery(TodoItemQueryRepository todoItemQueryRepository) {
        this.todoItemQueryRepository = todoItemQueryRepository;
    }

    @QueryHandler
    public TodoItem getById(GetTodoItemWithId query) {
        return todoItemQueryRepository.getOne(query.getId());
    }

    @QueryHandler
    public List<TodoItem> getById(QueryAllTodoItem query) {
        return todoItemQueryRepository.findAll();
    }

}
