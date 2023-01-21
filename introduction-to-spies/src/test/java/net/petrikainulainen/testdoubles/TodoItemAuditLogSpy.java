package net.petrikainulainen.testdoubles;

import java.util.ArrayList;
import java.util.List;

/**
 * This spy stores the arguments of every method invocation in a
 * {@link List}. Also, this spy provides methods which allow us
 * access the recorded information so that we can write assertions
 * for it.
 */
class TodoItemAuditLogSpy implements TodoItemAuditLog {

    private List<MethodInvocationArguments> arguments;

    TodoItemAuditLogSpy() {
        this.arguments = new ArrayList<>();
    }

    @Override
    public void logTodoItem(Long userId, AuditLogAction action, TodoItem todoItem) {
        var invocationArguments = new MethodInvocationArguments(userId, action, todoItem);
        arguments.add(invocationArguments);
    }

    /**
     * Returns the number recorded method invocations.
     */
    int getMethodInvocationCount() {
        return arguments.size();
    }

    /**
     * Returns the arguments of the specified method invocation.
     *
     * @param methodInvocationIndex The index of the method invocation.
     * @return  The
     */
    MethodInvocationArguments getArgumentsForMethodInvocation(int methodInvocationIndex) {
        if (methodInvocationIndex > arguments.size() - 1) {
            throw new NoArgumentsFoundException(
                    "No arguments found for method invocation with index: %d. This spy has recorded %d method invocations",
                    methodInvocationIndex,
                    arguments.size()
            );
        }
        return arguments.get(methodInvocationIndex);
    }
}
