package net.petrikainulainen.testdoubles;

/**
 * Provides the created operation for {@link TodoItem} objects.
 */
interface TodoItemRepository {

    /**
     * Inserts a new todo item into the database.
     *
     * @param input The information of the new todo item.
     * @return  The information that was inserted into the database.
     */
    TodoItem create(CreateTodoItem input);
}
