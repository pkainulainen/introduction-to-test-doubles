package net.petrikainulainen.testdoubles;

import java.util.Optional;

/**
 * Provides CRUD operations for {@link TodoItem} objects.
 */
interface TodoItemRepository {

    /**
     * Finds the a todo item from the database by using
     * its id as search criteria.
     *
     * @param id    The id of the returned todo item.
     * @return      An {@link Optional} object that contains the
     *              found todo item. If no todo item is found, this
     *              method returns an empty {@link Optional}.
     */
    Optional<TodoItem> findById(Long id);
}
