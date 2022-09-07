package net.petrikainulainen.testdoubles;

/**
 * Provides the delete operation for {@link TodoItem} objects.
 */
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Delete the todo item whose id is given as a method argument
     * when this method is invoked.
     * @param id    The id of the deleted todo item.
     * @return      The information of the deleted todo item.
     * @throws      NotFoundException   When the deleted todo item isn't found
     *                                  from the database.
     */
    public TodoItem deleteById(Long id) {
        return repository.deleteById(id);
    }
}
