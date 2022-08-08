package net.petrikainulainen.testdoubles;

import java.util.Optional;

/**
 * Provides query operations for {@link TodoItem} objects.
 */
public class TodoItemFinderService {

    private final TodoItemRepository repository;

    public TodoItemFinderService(TodoItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds the information of a single todo item by using its id as
     * search criteria.
     *
     * @param id    The id of the returned todo item.
     * @return      An {@link Optional} object that contains the
     *              found todo item. If no todo item is found, this
     *              method returns an empty {@link Optional}.
     */
    public Optional<TodoItem> findById(Long id) {
        return repository.findById(id);
    }
}
