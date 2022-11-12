package net.petrikainulainen.testdoubles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

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

        private TodoItemDTO newInformation;

        @Nested
        @DisplayName("When the updated todo item isn't found")
        class WhenUpdatedTodoItemIsNotFound {

            @BeforeEach
            void createUpdatedTodoItem() {
                newInformation = new TodoItemDTO();
                newInformation.setId(TodoItems.UNKNOWN_ID);
            }

            @Test
            @DisplayName("Should throw exception")
            void shouldThrowException() {
                assertThatThrownBy(() -> service.update(newInformation))
                        .isExactlyInstanceOf(NotFoundException.class);
            }

            @Test
            @DisplayName("Shouldn't update the existing todo item")
            void shouldNotUpdateExistingTodoItem() {
                catchThrowable(() -> service.update(newInformation));

                var existing = repository.findById(TodoItems.WriteBlogPost.ID).get();
                assertSoftly((softAssertions) -> {
                    softAssertions.assertThat(existing.getId()).as("id").isEqualByComparingTo(TodoItems.WriteBlogPost.ID);
                    softAssertions.assertThat(existing.getTitle()).as("title").isEqualTo(TodoItems.WriteBlogPost.TITLE);
                });
            }
        }

        @Nested
        @DisplayName("When the updated todo item is found")
        class WhenUpdatedTodoItemIsFound {

            @BeforeEach
            void createUpdatedTodoItem() {
                newInformation = new TodoItemDTO();
                newInformation.setId(TodoItems.WriteBlogPost.ID);
                newInformation.setTitle(TodoItems.NEW_TITLE);
            }

            @Test
            @DisplayName("Should return the information of the updated todo item")
            void shouldReturnInformationOfUpdatedTodoItem() {
                var returned = service.update(newInformation);

                assertSoftly((softAssertions) -> {
                    softAssertions.assertThat(returned.getId()).as("id").isEqualByComparingTo(TodoItems.WriteBlogPost.ID);
                    softAssertions.assertThat(returned.getTitle()).as("title").isEqualTo(TodoItems.NEW_TITLE);
                });
            }

            @Test
            @DisplayName("Should update the information of the found todo item")
            void shouldUpdateInformationOfFoundTodoItem() {
                service.update(newInformation);

                var updated = repository.findById(TodoItems.WriteBlogPost.ID).get();
                assertSoftly((softAssertions) -> {
                    softAssertions.assertThat(updated.getId()).as("id").isEqualByComparingTo(TodoItems.WriteBlogPost.ID);
                    softAssertions.assertThat(updated.getTitle()).as("title").isEqualTo(TodoItems.NEW_TITLE);
                });
            }
        }
    }
}
