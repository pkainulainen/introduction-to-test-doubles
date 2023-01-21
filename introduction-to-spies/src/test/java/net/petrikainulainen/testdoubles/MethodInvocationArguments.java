package net.petrikainulainen.testdoubles;

/**
 * Contains all arguments of one method invocation.
 */
class MethodInvocationArguments {

    private final AuditLogAction action;
    private final TodoItem todoItem;
    private final Long userId;

    /**
     * Creates a new object.
     *
     * @param userId    The id of the user performed the action.
     * @param action    The performed action.
     * @param todoItem  The target of the action.
     */
    MethodInvocationArguments(Long userId, AuditLogAction action, TodoItem todoItem) {
        this.action = action;
        this.todoItem = todoItem;
        this.userId = userId;
    }

    /**
     * Returns the performed action.
     */
    public AuditLogAction getAction() {
        return action;
    }

    /**
     * Returns the id of the user who performed the action.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the target of the action.
     */
    public TodoItem getTodoItem() {
        return todoItem;
    }
}
