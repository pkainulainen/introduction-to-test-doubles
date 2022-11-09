package net.petrikainulainen.testdoubles;

/**
 * Provides the update operation for {@link TodoItem} objects.
 */
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Updates the information of the specified todo item.
     * @param newInformation    The new information of the todo item.
     * @return  The information of the updated todo item.
     * @throws NotFoundException    if the requested todo item isn't found from the database.
     */
    public TodoItem update(TodoItem newInformation) {
        TodoItem updated = repository.findById(newInformation.getId())
                        .orElseThrow(() -> new NotFoundException(String.format(
                                "No todo item found with id: #%d",
                                newInformation.getId()
                        )));
        updated.setTitle(newInformation.getTitle());
        return repository.update(updated);
    }
}
