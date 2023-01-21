package net.petrikainulainen.testdoubles;

/**
 * Provides the create operation for {@link TodoItem} objects.
 */
public class TodoItemService {

    private final TodoItemAuditLog auditLog;
    private final TodoItemRepository repository;

    public TodoItemService(TodoItemAuditLog auditLog, TodoItemRepository repository) {
        this.auditLog = auditLog;
        this.repository = repository;
    }

    /**
     * Creates a new todo item.
     *
     * @param userId    The id of the user who created the todo item.
     * @param input     The information of the created todo item.
     * @return  The information that was inserted into the database.
     */
    public TodoItem create(Long userId, CreateTodoItem input) {
        var created = repository.create(input);
        auditLog.logTodoItem(userId, AuditLogAction.CREATE, created);
        return created;
    }
}
