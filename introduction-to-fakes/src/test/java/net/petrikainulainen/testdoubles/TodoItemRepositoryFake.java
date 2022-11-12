package net.petrikainulainen.testdoubles;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This fake stores the todo items to a map. Also, this
 * fake creates the initial test data when our test code
 * creates a new {@link TodoItemRepositoryFake} object.
 */
class TodoItemRepositoryFake implements TodoItemRepository {

    private final Map<Long, TodoItem> todoItems = new HashMap<>();

    TodoItemRepositoryFake() {
        var writeBlogPost = new TodoItem();
        writeBlogPost.setId(TodoItems.WriteBlogPost.ID);
        writeBlogPost.setTitle(TodoItems.WriteBlogPost.TITLE);

        todoItems.put(TodoItems.WriteBlogPost.ID, writeBlogPost);
    }

    @Override
    public Optional<TodoItem> findById(Long id) {
        return Optional.ofNullable(todoItems.get(id));
    }

    @Override
    public TodoItem update(TodoItem newInformation) {
        var updated = Optional.ofNullable(todoItems.get(newInformation.getId()))
                .orElseThrow(() -> new NotFoundException(
                        String.format("No todo item found with id: #%d", newInformation.getId())
                ));
        updated.setTitle(newInformation.getTitle());
        return updated;
    }
}
