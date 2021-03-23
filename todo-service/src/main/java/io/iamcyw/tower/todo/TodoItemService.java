package io.iamcyw.tower.todo;

import io.iamcyw.tower.commandhandling.CommandHandler;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.todo.commond.AddNewTodoItem;
import io.iamcyw.tower.todo.commond.DeleteTodoItem;
import io.iamcyw.tower.todo.commond.UpdateTodoItem;
import io.iamcyw.tower.todo.repository.TodoItemCommandRepository;
import io.iamcyw.tower.utils.Assert;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private final TodoItemCommandRepository repository;

    private final CommandGateway commandGateway;

    public TodoItemService(TodoItemCommandRepository repository, CommandGateway commandGateway) {
        this.repository = repository;
        this.commandGateway = commandGateway;
    }

    @CommandHandler
    public String addNewItem(AddNewTodoItem command) {
        Assert.nonNull(command.getNewItem(), Messages.OBJ_NULL);
        Assert.nonNull(command.getNewItem()
                               .getId(), Messages.OBJ_ID_NULL);
        return repository.save(command.getNewItem())
                .getId();
    }

    @CommandHandler
    public void updateItem(UpdateTodoItem command) {
        commandGateway.send(new DeleteTodoItem(command.getTodoItem()
                                                       .getId()), (commandMessage, commandResultMessage) -> {
            commandGateway.send(new AddNewTodoItem(command.getTodoItem()));
        });
    }

    @CommandHandler
    public void delete(DeleteTodoItem command) {
        repository.deleteById(command.getId());
    }

}
