package net.petrikainulainen.testdoubles;

import java.util.Optional;

/**
 * This stub returns the configured {@link TodoItem} object when
 * the system under test invokes the {@link #findById(Long)} method
 * and passes the id of the todo item as an argument.
 *
 * If an unexpected invocation occurs (the id isn't correct), this stub
 * throws a new {@link UnexpectedInteractionException} which fails the test.
 */
class TodoItemRepositoryStub implements TodoItemRepository {

    private final TodoItem returned;

    TodoItemRepositoryStub(TodoItem returned) {
        if (returned == null) {
            throw new NullPointerException("The returned todo item cannot be null");
        }

        if (returned.getId() == null) {
            throw new IllegalArgumentException("The id of the returned todo item cannot be null");
        }

        this.returned = returned;
    }

    @Override
    public Optional<TodoItem> findById(Long id) {
        if (invocationIsExpected(id)) {
            return Optional.of(returned);
        }
        throw new UnexpectedInteractionException(
                "Unexpected method invocation. Expected that the value of the id argument is: %d but was: %d",
                returned.getId(),
                id
        );
    }

    private boolean invocationIsExpected(Long id) {
        return (id != null) && id.equals(returned.getId());
    }
}