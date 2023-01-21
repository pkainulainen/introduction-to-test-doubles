package net.petrikainulainen.testdoubles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

/**
 * This test class demonstrates how you can use spies
 * in your test classes.
 */
class TodoItemServiceTest {

    private TodoItemAuditLogSpy auditLog;
    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    void configureSystemUnderTest() {
        auditLog = new TodoItemAuditLogSpy();
        repository = new TodoItemRepositoryFake();
        service = new TodoItemService(auditLog, repository);
    }

    @Nested
    @DisplayName("Create a new todo item")
    class Create {

        private final Long EXPECTED_ID = 1L;
        private final String TITLE = "Write a new blog post";
        private final Long USER_ID = 5L;

        private CreateTodoItem input;

        @BeforeEach
        void createInput() {
            input = new CreateTodoItem();
            input.setTitle(TITLE);
        }

        @Test
        void shouldWriteOneEntryToAuditLog() {
            service.create(USER_ID, input);

            var methodInvocationCount = auditLog.getMethodInvocationCount();
            assertThat(methodInvocationCount).isEqualByComparingTo(1);
        }

        @Test
        @DisplayName("Should write a new audit log entry with the correct user id")
        void shouldWriteNewAuditLogEntryWithCorrectUserId() {
            service.create(USER_ID, input);

            var methodInvocationArguments = auditLog.getArgumentsForMethodInvocation(0);
            assertThat(methodInvocationArguments.getUserId()).isEqualByComparingTo(USER_ID);
        }

        @Test
        @DisplayName("Should write a new audit log entry with the correct action")
        void shouldWriteNewAuditLogEntryWithCorrectAction() {
            service.create(USER_ID, input);

            var methodInvocationArguments = auditLog.getArgumentsForMethodInvocation(0);
            assertThat(methodInvocationArguments.getAction()).isEqualByComparingTo(AuditLogAction.CREATE);
        }

        @Test
        @DisplayName("Should write a new audit log entry with the correct todo item")
        void shouldWriteNewAuditLogEntryWithCorrectTodoItem() {
            service.create(USER_ID, input);

            var methodInvocationArguments = auditLog.getArgumentsForMethodInvocation(0);
            assertSoftly((softAssertions -> {
                var todoItem = methodInvocationArguments.getTodoItem();

                softAssertions.assertThat(todoItem.getId())
                        .as("id")
                        .isEqualByComparingTo(EXPECTED_ID);
                softAssertions.assertThat(todoItem.getTitle())
                        .as("title")
                        .isEqualTo(TITLE);
            }));
        }

        @Test
        @DisplayName("Should return a todo item with the correct information")
        void shouldReturnTodoItemWithCorrectInformation() {
            var returned = service.create(USER_ID, input);

            assertSoftly((softAssertions -> {
                softAssertions.assertThat(returned.getId())
                        .as("id")
                        .isEqualByComparingTo(EXPECTED_ID);
                softAssertions.assertThat(returned.getTitle())
                        .as("title")
                        .isEqualTo(TITLE);
            }));
        }
    }
}
