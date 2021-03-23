package io.iamcyw.tower.todo.controller;

import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.messaging.responsetypes.ResponseTypes;
import io.iamcyw.tower.queryhandling.QueryGateway;
import io.iamcyw.tower.todo.commond.AddNewTodoItem;
import io.iamcyw.tower.todo.commond.DeleteTodoItem;
import io.iamcyw.tower.todo.commond.UpdateTodoItem;
import io.iamcyw.tower.todo.entity.TodoItem;
import io.iamcyw.tower.todo.query.GetTodoItemWithId;
import io.iamcyw.tower.todo.query.QueryAllTodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TodoListController {

    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private CommandGateway commandGateway;

    public TodoListController() {
    }

    @RequestMapping("/home")
    public Map<String, Object> home() {
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID()
                .toString());
        model.put("content", "home");
        return model;
    }

    /**
     * HTTP GET
     */
    @RequestMapping(value = "/api/todolist/{index}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTodoItem(@PathVariable("index") String index) {
        try {
            return new ResponseEntity<TodoItem>(queryGateway.query(new GetTodoItemWithId(index), TodoItem.class)
                                                        .get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(index + " not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP GET ALL
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllTodoItems() {
        try {
            return new ResponseEntity<>(
                    queryGateway.query(new QueryAllTodoItem(), ResponseTypes.multipleInstancesOf(TodoItem.class)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nothing found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP POST NEW ONE
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewTodoItem(@RequestBody TodoItem item) {
        try {
            item.setId(UUID.randomUUID()
                               .toString());
            commandGateway.send(new AddNewTodoItem(item));
            return new ResponseEntity<String>("Entity created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity creation failed", HttpStatus.CONFLICT);
        }
    }

    /**
     * HTTP PUT UPDATE
     */
    @RequestMapping(value = "/api/todolist", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateTodoItem(@RequestBody TodoItem item) {
        try {
            commandGateway.send(new UpdateTodoItem(item));
            return new ResponseEntity<String>("Entity updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity updating failed", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * HTTP DELETE
     */
    @RequestMapping(value = "/api/todolist/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTodoItem(@PathVariable("id") String id) {
        try {
            commandGateway.send(new DeleteTodoItem(id));
            return new ResponseEntity<String>("Entity deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Entity deletion failed", HttpStatus.NOT_FOUND);
        }

    }

}
