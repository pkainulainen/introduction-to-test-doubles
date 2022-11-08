package net.petrikainulainen.testdoubles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This test demonstrates how we can use our fake when
 * we are writing tests for the system under test.
 */
class TodoItemServiceTest {

    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    void configureSystemUnderTest() {
        this.repository = new TodoItemRepositoryFake();
        this.service = new TodoItemService(this.repository);
    }

    @Nested
    @DisplayName("Update todo time")
    class UpdateTodoItem {

        private TodoItem newInformation;

        @Nested
        @DisplayName("When the updated todo item isn't found")
        class WhenUpdatedTodoItemIsNotFound {

            @BeforeEach
            void createUpdatedTodoItem() {
                newInformation = new TodoItem();
                newInformation.setId(TodoItems.UNKNOWN_ID);
            }

            @Test
            @DisplayName("Should throw exception")
            void shouldThrowException() {
                assertThatThrownBy(() -> service.update(newInformation))
                        .isExactlyInstanceOf(NotFoundException.class);
            }
        }

        @Nested
        @DisplayName("When the updated todo item is found")
        class WhenUpdatedTodoItemIsFound {

            @BeforeEach
            void createUpdatedTodoItem() {
                newInformation = new TodoItem();
                newInformation.setId(TodoItems.WriteBlogPost.ID);
                newInformation.setTitle(TodoItems.NEW_TITLE);
            }
        }
    }
}
