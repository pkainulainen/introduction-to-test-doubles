package net.petrikainulainen.testdoubles;

/**
 * Provides an audit log for {@link TodoItem} objects.
 */
interface TodoItemAuditLog {

    /**
     * Logs the information the changes made to a todo item to the
     * audit log.
     *
     * @param userId    The id of the user who performed the action.
     * @param action    The performed action.
     * @param todoItem  The target of the action.
     */
    void logTodoItem(Long userId, AuditLogAction action, TodoItem todoItem);
}
