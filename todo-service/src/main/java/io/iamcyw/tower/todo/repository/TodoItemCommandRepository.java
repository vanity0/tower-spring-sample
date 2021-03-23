package io.iamcyw.tower.todo.repository;

import io.iamcyw.tower.todo.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemCommandRepository extends JpaRepository<TodoItem, String> {
}
