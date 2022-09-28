package net.petrikainulainen.testdoubles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This mock returns the configured {@link TodoItem} object when
 * the system under test invokes the {@link #deleteById(Long)} method
 * and passes the id of the todo item as an argument.
 *
 * If an unexpected invocation occurs (the id isn't correct), this mock throws
 * a new {@link UnexpectedInteractionException} when the stubbed method is invoked.
 */
class TodoItemRepositoryMock implements TodoItemRepository {

    private final Long expectedIdArgument;
    private final TodoItem returned;
    private final boolean todoItemNotFound;

    private Long actualIdArgument;
    private boolean deleteByIdCalled;

    /**
     * Disable the no argument constructor.
     */
    private TodoItemRepositoryMock() {
        //These lines ensure that this class can be compiled.
        this.expectedIdArgument = null;
        this.returned = null;
        this.todoItemNotFound = false;
    }

    /**
     * Creates a new mock object and ensures that the mock object
     * throws the {@link NotFoundException} when the {@link deleteById(Long)}
     * method is invoked and the expected id is passed as an argument.
     *
     * @param expectedIdArgument    The expected id argument.
     */
    TodoItemRepositoryMock(Long expectedIdArgument) {
        this.expectedIdArgument = expectedIdArgument;
        this.returned = null;
        this.todoItemNotFound = true;
    }

    /**
     * Creates a new mock object and configures the {@link TodoItem} object
     * that's returned when the the {@link deleteById(Long)} method is invoked
     * and the expected id is passed as an argument.
     *
     * @param returned  The returned todo item.
     */
    TodoItemRepositoryMock(TodoItem returned) {
        if (returned == null) {
            throw new NullPointerException("The returned todo item cannot be null");
        }

        if (returned.getId() == null) {
            throw new IllegalArgumentException("The id of the returned todo item cannot be null");
        }

        this.expectedIdArgument = returned.getId();
        this.returned = returned;
        this.todoItemNotFound = false;
    }

    @Override
    public TodoItem deleteById(Long id) {
        this.deleteByIdCalled = true;
        this.actualIdArgument = id;

        if (invocationIsExpected(id)) {
            // This is one way to ensure that the subbed method can throw the
            // NotFoundException when we want to write a test which ensures that
            // the system under test is working as expected when the requested todo
            // item isn't found.
            //
            // Another option is to throw a NotFoundException every time when the
            // deleteById() method is invoked by using an unexpected id argument.
            // The downside of this approach is that we don't get a clear signal
            // that an unexpected interaction happened between the system under test
            // and our mock because the stubbed method hides this by throwing a
            // NotFoundException.
            //
            // That's why I chose this implementation.
            if (todoItemNotFound) {
                throw new NotFoundException(String.format(
                        "No todo item found with the id: %d",
                        id
                ));
            }

            return returned;
        }

        throw new UnexpectedInteractionException(
                "Unexpected method invocation. Expected that the value of the id argument is: %d but was: %d",
                this.returned.getId(),
                id
        );
    }

    private boolean invocationIsExpected(Long id) {
        return (id != null) && id.equals(this.expectedIdArgument);
    }

    /**
     * Verifies that the {@link deleteById(Long)} method was called
     * and the correct id was passed to the invoked method.
     */
    public void verify() {
        assertThat(deleteByIdCalled)
                .overridingErrorMessage("Expected that the deleteById() method was called once but it was not called.")
                .isTrue();

        //This assertion isn't required because the stubbed method throws
        //an exception if the system under test invokes it by using an
        //incorrect aka unexpected id. In other words, if the previous
        //assertion passes and the exception isn't thrown, we know that
        //the deleteById() method was invoked and the expected argument
        //was passed to it.
        //
        //However, I added this assertion here we have to check the value of
        //the actual argument if:
        //
        //1) The stubbed methods implement by this class return the default
        //   value if an unexpected invocation occurs.
        //2) We don't have to stub the invoked method because it doesn't
        //   return anything.
        assertThat(actualIdArgument)
                .overridingErrorMessage(
                        "Expected an argument: %d but got an argument: %d",
                        expectedIdArgument,
                        actualIdArgument
                )
                .isEqualTo(expectedIdArgument);
    }
}
