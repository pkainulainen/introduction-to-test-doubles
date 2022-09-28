package net.petrikainulainen.testdoubles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TodoItemServiceTest {

    private static final long TODO_ITEM_ID = 4L;
    private static final String TODO_ITEM_TITLE = "FooBar";

    private TodoItemService service;
    private TodoItemRepositoryMock repository;

    @Nested
    @DisplayName("When the invocation is expected")
    class WhenInvocationIsExcepted {

        @Nested
        @DisplayName("When the todo item isn't found")
        class WhenTodoItemIsNotFound {

            @BeforeEach
            void configureSystemUnderTest() {
                repository = new TodoItemRepositoryMock(TODO_ITEM_ID);
                service = new TodoItemService(repository);
            }

            @Test
            @DisplayName("Should throw an exception")
            void shouldThrowException() {
                assertThatThrownBy(() -> service.deleteById(TODO_ITEM_ID));
            }
        }

        @Nested
        @DisplayName("When the todo item is found")
        class WhenTodoItemIsFound {

            @BeforeEach
            void configureSystemUnderTest() {
                TodoItem deleted = createDeletedTodoItem();
                repository = new TodoItemRepositoryMock(deleted);
                service = new TodoItemService(repository);
            }

            @Test
            @DisplayName("Should return a todo item with the correct id")
            void shouldReturnTodoItemWithCorrectId() {
                TodoItem deleted = service.deleteById(TODO_ITEM_ID);
                assertThat(deleted.getId()).isEqualTo(TODO_ITEM_ID);
            }

            @Test
            @DisplayName("Should return a todo item with the correct title")
            void shouldReturnTodoItemWithCorrectTitle() {
                TodoItem deleted = service.deleteById(TODO_ITEM_ID);
                assertThat(deleted.getTitle()).isEqualTo(TODO_ITEM_TITLE);
            }

            @Test
            @DisplayName("Should delete the correct todo item")
            void shouldDeleteCorrectTodoItem() {
                service.deleteById(TODO_ITEM_ID);
                repository.verify();
            }
        }
    }

    @Nested
    @DisplayName("When the invocation is unexpected")
    class WhenInvocationIsUnexpected {

        private static final long UNKNOWN_ID = 999L;

        @BeforeEach
        void configureSystemUnderTest() {
            TodoItem deleted = createDeletedTodoItem();
            repository = new TodoItemRepositoryMock(deleted);
            service = new TodoItemService(repository);
        }

        @Test
        @DisplayName("Should throw exception")
        void shouldThrowException() {
            //This isn't a good test. I wrote this only because I didn't want that
            //this test fails when an unexpected invocation happens between the
            //system under test and our stub.
            assertThatThrownBy(() -> service.deleteById(UNKNOWN_ID))
                    .isExactlyInstanceOf(UnexpectedInteractionException.class);
        }
    }

    private TodoItem createDeletedTodoItem() {
        TodoItem deleted = new TodoItem();
        deleted.setId(TODO_ITEM_ID);
        deleted.setTitle(TODO_ITEM_TITLE);
        return deleted;
    }
}
