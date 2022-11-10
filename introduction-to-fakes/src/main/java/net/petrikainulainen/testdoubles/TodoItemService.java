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
    public TodoItemDTO update(TodoItemDTO newInformation) {
        TodoItem updated = repository.findById(newInformation.getId())
                        .orElseThrow(() -> new NotFoundException(String.format(
                                "No todo item found with id: #%d",
                                newInformation.getId()
                        )));
        updated.setTitle(newInformation.getTitle());

        TodoItem returned = repository.update(updated);
        return mapToDTO(returned);
    }

    private TodoItemDTO mapToDTO(TodoItem model) {
        TodoItemDTO dto = new TodoItemDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        return dto;
    }
}
