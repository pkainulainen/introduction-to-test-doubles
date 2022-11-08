package net.petrikainulainen.testdoubles;

/**
 * Provides CRUD operations for {@link TodoItem} objects.
 */
interface TodoItemRepository {

    /**
     * Finds the requested todo item from the database
     * by using its id as search criteria.
     * @param id    The id of the todo item.
     * @return  The information of the found todo item.
     * @throws  NotFoundException   If no todo item is found from the database.
     */
    TodoItem findById(Long id);

    /**
     * Updates the information of the specified todo item.
     * @param newInformation    The new information of the updated todo item.
     * @return  The information of the updated todo item.
     * @throws  NotFoundException   If the updated todo item isn't found from the database.
     */
    TodoItem update(TodoItem newInformation);
}
