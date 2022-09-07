package net.petrikainulainen.testdoubles;

import java.util.Optional;

/**
 * Provides CRUD operations for {@link TodoItem} objects.
 */
interface TodoItemRepository {

    /**
     * Delete the todo item whose id is given as a method argument
     * when this method is invoked.
     * @param id    The id of the deleted todo item.
     * @return      The information of the deleted todo item.
     * @throws      NotFoundException   When the deleted todo item isn't found
     *                                  from the database.
     */
    TodoItem deleteById(Long id);
}
