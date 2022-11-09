package net.petrikainulainen.testdoubles;

import java.util.Optional;

/**
 * Provides CRUD operations for {@link TodoItem} objects.
 */
interface TodoItemRepository {

    /**
     * Finds the requested todo item from the database
     * by using its id as search criteria.
     * @param id    The id of the todo item.
     * @return  An {@link Optional} that contains the information of the found todo item.
     *          If the requested todo item isn't found from the database, this method returns
     *          an empty {@link Optional}
     */
    Optional<TodoItem> findById(Long id);

    /**
     * Updates the information of the specified todo item.
     * @param newInformation    The new information of the updated todo item.
     * @return  The information of the updated todo item.
     */
    TodoItem update(TodoItem newInformation);
}
