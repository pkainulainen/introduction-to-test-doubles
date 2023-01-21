package net.petrikainulainen.testdoubles;

import java.util.HashMap;
import java.util.Map;

/**
 * This fake stores the {@link TodoItem} objects in a {@link Map}.
 */
class TodoItemRepositoryFake implements TodoItemRepository {

    private final Map<Long, TodoItem> todoItems = new HashMap<>();

    @Override
    public TodoItem create(CreateTodoItem input) {
        var id = todoItems.size() + 1L;

        var created = new TodoItem();
        created.setId(id);
        created.setTitle(input.getTitle());

        todoItems.put(id, created);
        return created;
    }
}
