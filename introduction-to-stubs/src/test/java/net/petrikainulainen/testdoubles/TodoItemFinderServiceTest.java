package net.petrikainulainen.testdoubles;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * This test class demonstrates how we can use our stub when
 * we are writing tests for the system under test.
 */
class TodoItemFinderServiceTest {

    private static final Long ID = 1L;
    private static final String TITLE = "title";

    private TodoItemFinderService service;

    @BeforeEach
    void configureSystemUnderTest() {
        TodoItem found = createFoundTodoItem();
        TodoItemRepositoryStub repository = new TodoItemRepositoryStub(found);
        service = new TodoItemFinderService(repository);
    }

    private TodoItem createFoundTodoItem() {
        TodoItem found = new TodoItem();
        found.setId(ID);
        found.setTitle(TITLE);
        return found;
    }

    @Nested
    @DisplayName("When the invocation is expected")
    @ExtendWith(SoftAssertionsExtension.class)
    class WhenInvocationIsExpected {

        @Test
        @DisplayName("Should return the found todo item")
        void shouldReturnFoundTodoItem() {
            Optional<TodoItem> result = service.findById(ID);
            assertThat(result).isPresent();
        }

        @Test
        @DisplayName("Should return the expected information of the found item")
        void shouldReturnExpectedInformationOfFoundTodoItem(SoftAssertions assertions) {
            TodoItem found = service.findById(ID).get();

            assertions.assertThat(found.getId())
                    .as("id")
                    .isEqualByComparingTo(ID);
            assertions.assertThat(found.getTitle())
                    .as("title")
                    .isEqualTo(TITLE);
        }
    }

    @Nested
    @DisplayName("When the invocation is unexpected")
    class WhenInvocationIsUnexpected {

        private final Long UNKNOWN_ID = 99L;

        @Test
        @DisplayName("Should throw exception")
        void shouldThrowException() {
            assertThatThrownBy(() -> service.findById(UNKNOWN_ID))
                    .isExactlyInstanceOf(UnexpectedInteractionException.class);
        }
    }
}
